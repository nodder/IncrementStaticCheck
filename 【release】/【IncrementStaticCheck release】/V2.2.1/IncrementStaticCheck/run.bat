@echo off
title Code Defect Incremental Check

if DEFINED JAVA_HOME goto AFTER_DEFINED
    
    set JAVA_HOME=

:AFTER_DEFINED

set JAVA="%JAVA_HOME%\bin\java"
set DIRNAME=.
set MAIN_HOME=%DIRNAME%
set MAIN_LIB=%MAIN_HOME%\lib
set classpath=%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%2\lib\tools.jar;%MAIN_LIB%\auto-localcheck.jar;%MAIN_LIB%\jdom.jar;%MAIN_LIB%\log4j.jar;%MAIN_LIB%\spring-core-3.1.2.RELEASE.jar;

rem set JAVA_OPTS=-classic -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8790,server=y,suspend=y %JAVA_OPTS%

%JAVA% %JAVA_OPTS% cdd.zte.checktools.localchecktool.Start %1 %2 %3
