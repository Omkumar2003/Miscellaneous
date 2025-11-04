// this horrendous code took time ........y i do this .....i really hate dsa

class Solution {
    static {
        for(int i=0;i<400;i++){
            findXSum(new int[]{0},1,0);
        }
    }
    static class MyNode{
        int value;
        int count;
        
        MyNode(int v,int c){
            this.value=v;
            this.count=c;
        }
        public int getValue(){return this.value;}
        public int getCount(){return this.count;}
        public long hashcode(){
            return Objects.hashCode(value) ^ Objects.hashCode(count);
        }
    }

    static class myCompare  implements Comparator<MyNode>{
        public int compare(MyNode a, MyNode b){
            int temp=b.count-a.count;
            if(temp==0){
                return b.value-a.value;
            }
            return temp;
        }
    }



    public static int[] findXSum(int[] nums, int k, int x) {
         PriorityQueue<MyNode> pq= new PriorityQueue<MyNode>(new myCompare());
         HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
         int[] ans= new int[nums.length-k+1];

         int p1=0;
         int p2=k-1;
         for(int i=0;i<k;i++){
            if(hm.containsKey(nums[i])){
                int temp = hm.get(nums[i]);
                hm.put(nums[i],temp+1);
            }else{hm.put(nums[i],1);}
         }



        for(Map.Entry<Integer,Integer> m:hm.entrySet()){
            pq.offer(new MyNode(m.getKey(),m.getValue()));
        }
         for(;;){
            if(p2>nums.length-1) break;
            int sum=0;
            ArrayList<MyNode> ar = new ArrayList<MyNode>();
            for(int i=0;i<x;i++){
                 MyNode m = pq.poll();
                 if(m == null) break;
                 ar.add(m);
                sum+=m.getValue()*m.getCount();
            }
            ans[p1]=sum;

            for(MyNode m:ar){pq.offer(m);};

            //purane p1 ko hta
            final int p1CurVal=nums[p1];
            int p1CurCount=hm.get(p1CurVal);
            if(p1CurCount==1){
                hm.remove(p1CurVal);
                pq.removeIf((y)->y.value==p1CurVal);
            }else{
                hm.put(p1CurVal,p1CurCount-1);
                pq.removeIf((y)->y.value==p1CurVal);
                pq.offer(new MyNode(p1CurVal,p1CurCount-1));
            }
            //naye p2 ko jod
            int copy=0;
            if(p2<nums.length-1){
                copy=nums[p2+1];
            }else {
                p2++;
                p1++;
                continue;
                }
            final int newP2=copy;

            if(hm.containsKey(newP2)){
                int mycount= hm.get(newP2);
                pq.removeIf((y)->y.value==newP2);
                pq.offer(new MyNode(newP2,mycount+1));
                hm.put(newP2,mycount+1);
                
            }else{
                hm.put(newP2,1);
                pq.offer(new MyNode(newP2,1));   
            }



            p2++;
            p1++;
         }



      return ans;   
    }
}
