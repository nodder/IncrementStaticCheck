@echo off

set searchPath=%1
set target=%2

for /r %searchPath% %%i in (%target%) do if exist %%i echo %%i