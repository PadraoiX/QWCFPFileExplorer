# Diário de Bordo do Projeto QWCFP File Explorer
## Descrição do projeto
Interface WEB de usuários, que permita entre outros, navegar e gerir objetos persistidos no QWCFP.  Este módulo depende dum servidor de aplicação J2EE e faz uso de JSF 2.0 + PrimeFAces 5.2 + Web Service SOAP 1.1 + QWCFPWebService, acessando o um dos repositórios QWCFP na rede, para operar conforme a tela de exemplo: 
![Exemplo](https://github.com/PadraoiX/QWCFPFileExplorer/blob/master/ExemploTelaProjeto.png)

## Histórico de atualizações
### 2020-05-06 - Criação do projeto
###### 1 - Criado com base num fork do projeto ***DragonZone-WebExplore***, mantido em **https://github.com/abcbaby/DragonZone-WebExplorer** ;
###### 2 - Alterados os nomes dos pacotes java e estruturas de diretórios do projeto para manter o padrão PIX;
###### 3 - Alteradas as informações de manifesto do projto POM.XML para manter o padrão PIX;
###### 4 - Adicionada esta primeira versão alterada para o GitHub PIX.
###### 5 - O build não funcionou pelo Eclipse. Só na mão, com:
set JAVA_HOME=D:\Bin\Java\jdk1.8.0_45
D:\Bin\maven-3.6.3\bin\mvn.cmd package

O Porquê continua um mistério. Entretanto, eu ainda acredito que a T.I. é uma ciência exata.

A resposta foi atualize o eclipse inteiro, isto é, jogue fora a versão usada, baixe a mais nova e seja feliz.

###### 6 - Alterações nas configurações do Eclipse para usar JDK no lugar de JRE para este projeto.

