@echo off

set mainDir=%~dp0

reg add HKEY_CLASSES_ROOT\Directory\shell\mysvn /ve /d "本地代码静态检查" /f >nul

reg add HKEY_CLASSES_ROOT\Directory\shell\mysvn\command /ve /d "%mainDir%run.bat /p %%1" /f >nul

if %errorlevel% == 0 echo 关联文件夹右键菜单成功！ 

pause