@echo off
setlocal enabledelayedexpansion

set "str=%~1"
set "rev="

if not defined str (
    echo(
    exit /b
)

for /l %%i in (0,1,1000) do (
    set "char=!str:~%%i,1!"
    if not defined char goto done
    set "rev=!char!!rev!"
)

:done
echo(!rev!