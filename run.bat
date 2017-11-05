@echo off
title 入库检查工具

if DEFINED JAVA_HOME goto AFTER_DEFINED
    
    set JAVA_HOME=

:AFTER_DEFINED

set JAVA="%JAVA_HOME%\bin\java"

set programeDir=%~dp0
set dirToCheck=%2

%~d0
cd %~dp0

set MAIN_HOME=%~dp0
set MAIN_LIB=%MAIN_HOME%\lib
set THIRDPARTY_LIB=%MAIN_HOME%\thirdPartyLib
set classpath=%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%2\lib\tools.jar;%MAIN_LIB%\cdd-svncommit-tool.jar;%THIRDPARTY_LIB%\log4j.jar;%THIRDPARTY_LIB%\jdom.jar;%THIRDPARTY_LIB%\spring-core-3.1.2.RELEASE.jar;

rem set JAVA_OPTS=-classic -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8789,server=y,suspend=y %JAVA_OPTS%

%JAVA% %JAVA_OPTS% -Xmx256m cdd.zte.checktools.committool.Start %programeDir% %dirToCheck%

pause