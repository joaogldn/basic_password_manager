@echo off
setlocal

echo Compilando arquivos Java...
if not exist bin mkdir bin

javac -cp "lib/*" -d bin src\*.java

if %ERRORLEVEL% NEQ 0 (
    echo Houve um erro na compilação.
    pause
    exit /b
)

echo.
echo Iniciando a aplicação...
java -cp "lib/*;bin" Principal

echo.
pause
endlocal
