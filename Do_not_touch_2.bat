@echo off
setlocal
set "solUrl=https://github.com/ullaskunder3/Solution-to-graphics.h/archive/refs/heads/main.zip"
set "templateUrl=https://github.com/ullaskunder3/graphics.h-project-template/archive/refs/heads/main.zip"
set "destinationFolder=%CD%"
set "name=omdemo.zip"
set "name1=omdemeo1.zip"

powershell -Command "(New-Object System.Net.WebClient).DownloadFile('%templateUrl%', '%name1%')"
powershell -Command "(New-Object System.Net.WebClient).DownloadFile('%solUrl%', '%name%')"

powershell -noprofile -command "Expand-Archive -Path .\%name% -DestinationPath ""%destinationFolder%"""
powershell -noprofile -command "Expand-Archive -Path .\%name1% -DestinationPath ""%destinationFolder%"""

del "%name%"
del "%name1%"

copy "%cd%\Solution-to-graphics.h-main\graphics.h" "C:\MinGW\include"
copy "%cd%\Solution-to-graphics.h-main\winbgim.h" "C:\MinGW\include"
copy "%cd%\Solution-to-graphics.h-main\libbgi.a" "C:\MinGW\lib"

RMDIR /S /Q "Solution-to-graphics.h-main"
ren graphics.h-project-template-main graphics-vs-code

start  code "%cd%\graphics-vs-code" && exit
endlocal