@echo off
cls

set DRIVE_LETTER=%1:

::0 if nothing is passed in the command line (default of player state)
set COMMAND_LINE_PARAM=%2
if "%2"=="" set COMMAND_LINE_PARAM=0

set PATH=%DRIVE_LETTER%\Java\bin;%DRIVE_LETTER%\Java\ant-1.9.6\bin;c:\Windows

call ant run -Ddrive-letter=%DRIVE_LETTER% -Dargs=%COMMAND_LINE_PARAM%