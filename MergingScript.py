# dikkat ye hai, it is still slow 
# i have 203 videos ,,,,,,,,,, i have to combine them all 
# and total merged length of the video is 100 hours 
# and it is taking 100 hours to merge the video ..................and audio about 1 to 2 hours 
# there should be a better way to do this !!!!!!!!!!!!!!!!!!!
import os
import re
from moviepy.editor import VideoFileClip, concatenate_videoclips
from concurrent.futures import ThreadPoolExecutor, as_completed
from tqdm import tqdm
import json

video_directory = "D:/Smart-Youtube-Playlist-Downloader-main"

progress_file = os.path.join(video_directory, "progress.json")

def process_video(file_path):
    try:
        clip = VideoFileClip(file_path)
        if clip.duration > 0:
            return (file_path, clip, None)
        else:
            raise ValueError("Clip has zero duration")
    except Exception as e:
        return (file_path, None, e)

if os.path.exists(progress_file):
    with open(progress_file, 'r') as f:
        progress_data = json.load(f)
    processed_files = progress_data['processed_files']
    failed_files = progress_data['failed_files']
else:
    processed_files = []
    failed_files = []


def extract_prefix(filename):
    match = re.match(r'(\d+)', filename)
    return int(match.group(1)) if match else float('inf')


video_files = sorted([f for f in os.listdir(video_directory) if f.endswith('.mp4')],
                     key=extract_prefix)





video_files = [f for f in video_files if f not in processed_files and f not in failed_files]



successful_clips = []


with ThreadPoolExecutor() as executor:
    futures = {executor.submit(process_video, os.path.join(video_directory, file)): file for file in video_files}
    
  
    with tqdm(total=len(video_files)) as pbar:
        for future in as_completed(futures):
            file = futures[future]
            file_path, clip, error = future.result()
            if clip:
                successful_clips.append((file_path, clip))
                processed_files.append(file)
            else:
                failed_files.append(file)
          
            pbar.update(1)
            if error:
                print(f"Error processing {file_path}: {error}")
            else:
                print(f"Successfully processed {file_path}")


            with open(progress_file, 'w') as f:
                json.dump({'processed_files': processed_files, 'failed_files': failed_files}, f)


timestamp_file = os.path.join(video_directory, "timestamps.txt")
current_time = 0
with open(timestamp_file, 'w') as f:
    for file_path, clip in successful_clips:
        f.write(f"{current_time} - {file_path}\n")
        current_time += clip.duration


if successful_clips:
    final_clip_successful = concatenate_videoclips([clip for _, clip in successful_clips])
    final_clip_successful.write_videofile("combined_successful.mp4", codec="libx264")

if failed_files:
    print("Failed files:")
    for file in failed_files:
        print(file)


if len(failed_files) < len(video_files):
    remaining_files = [f for f in video_files if f not in failed_files]
    remaining_clips = []
    
    for file in remaining_files:
        try:
            clip = VideoFileClip(os.path.join(video_directory, file))
            if clip.duration > 0:
                remaining_clips.append(clip)
        except Exception as e:
            print(f"Error processing {file}: {e}")
    
    if remaining_clips:
        final_clip_remaining = concatenate_videoclips(remaining_clips)
        final_clip_remaining.write_videofile("combined_remaining.mp4", codec="libx264")


if os.path.exists(progress_file):
    os.remove(progress_file)
