@echo off

reg query HKEY_CLASSES_ROOT\Directory\shell\mysvn > nul 2>nul

IF %ERRORLEVEL% == 0 (
    reg delete HKEY_CLASSES_ROOT\Directory\shell\mysvn /f >nul
    if %ERRORLEVEL% == 0 (
        echo �����ļ����Ҽ������˵��ɹ��� 
        goto End
    )
  
    echo ж��ʧ�ܣ�
    goto End
)

echo ��δע�ᣬ����ж�أ�

:End
pause