@echo off

reg query HKEY_CLASSES_ROOT\Directory\shell\mysvn > nul 2>nul

IF %ERRORLEVEL% == 0 (
    reg delete HKEY_CLASSES_ROOT\Directory\shell\mysvn /f >nul
    if %ERRORLEVEL% == 0 (
        echo 撤销文件夹右键关联菜单成功！ 
        goto End
    )
  
    echo 卸载失败！
    goto End
)

echo 尚未注册，无须卸载！

:End
pause