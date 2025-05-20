@echo off
setlocal

REM Verifica se a pasta bin existe
if exist bin (
    REM Remove arquivos .class dentro da pasta bin e subpastas
    del /s /q bin\*.class >nul 2>&1
    echo Arquivos .class na pasta 'bin' removidos com sucesso.
) else (
    echo Pasta 'bin' nao encontrada. Nada para limpar.
)

endlocal
