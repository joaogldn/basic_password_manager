@echo off
setlocal

REM Verifica se a pasta bin existe
if not exist bin (
    echo Erro: Pasta "bin" nao encontrada. Compile o projeto primeiro.
    pause
    exit /b 1
)

REM Roda a aplicação a partir da pasta bin
java -cp bin Principal
if errorlevel 1 (
    echo Erro: Falha ao executar a aplicacao.
    pause
    exit /b 1
)

endlocal
