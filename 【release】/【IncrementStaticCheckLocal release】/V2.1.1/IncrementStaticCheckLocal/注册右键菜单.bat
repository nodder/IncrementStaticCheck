@echo off

set mainDir=%~dp0

reg add HKEY_CLASSES_ROOT\Directory\shell\mysvn /ve /d "���ش��뾲̬���" /f >nul

reg add HKEY_CLASSES_ROOT\Directory\shell\mysvn\command /ve /d "%mainDir%run.bat /p %%1" /f >nul

if %errorlevel% == 0 echo �����ļ����Ҽ��˵��ɹ��� 

pause