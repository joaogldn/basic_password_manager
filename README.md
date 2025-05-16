# Gerenciador de Senhas Seguras

Este projeto em Java permite que os usuários armazenem e gerenciem senhas de forma segura, com recursos como:

- Cadastro de credenciais com criptografia bcrypt
- Autenticação de dois fatores (2FA)
- Geração de senhas fortes aleatórias
- Verificação de vazamento de senhas via API externa

## Requisitos

- Java JDK (11 ou superior) instalado e configurado no PATH
- Sistema operacional Windows (para rodar os scripts `.bat`)
- Internet para verificação de vazamento (opcional)

## Como executar

### Via PowerShell

1. Abra o PowerShell na pasta do projeto.
2. Compile os arquivos Java:
````
javac -cp "lib/*" -d bin src\*.java
````
3.Execute a aplicação
````
  java -cp "lib/*;bin" Principal
````
4. Siga as instruções da aplicação (ex: digite o código 2FA).
5. Caso queria limpar os arquivos compilados, execute no PowerShell:
````
Get-ChildItem -Path src -Filter *.class -Recurse | Remove-Item
````

### Via script .bat
1. Dê dois cliques no arquivo executar.bat na raiz do projeto.
2. O script compilará e executará automaticamente.
3. Siga as instruções da aplicação.
4. Caso queira limpar os arquivos compilados, execute limpar.bat.

### Observações
Se não tiver o JDK instalado, baixe em: https://www.oracle.com/java/technologies/downloads/

Certifique-se que os comandos java e javac funcionam no terminal (teste com java -version e javac -version).


