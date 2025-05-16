@echo off
setlocal

echo Limpando a pasta 'bin'...

REM Verifica se a pasta bin existe
if exist bin (
    rd /s /q bin
    echo Pasta 'bin' removida com sucesso.
) else (
    echo Nenhuma pasta 'bin' encontrada.
)

pause
endlocal
