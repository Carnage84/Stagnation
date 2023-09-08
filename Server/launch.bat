@echo off
title singleplayer

:start
cls
echo 1. Launch
echo 2. Exit
echo:
set /p action=Pick number:
if /i "%action%"=="1" goto run
if /i "%action%"=="2" goto exit
set /p action=""
goto start

:exit
taskkill /f /im Java*
exit

:run
cd "%server%"
start /b java -jar server.jar
ping localhost -n 10 > nul
echo:
goto start
