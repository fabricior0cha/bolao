
# Bolão do Brasileirão
Projeto de back-end para construção de um sistema de bolão, onde o usuário pode participar de um bolão que tem os jogos do Brasileirão, palpitando no resultado do jogo acumula pontos e ganha o bolão.




## Requisitos

- [JDK 21](https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.zip)
- [Eclipse IDE para Java EE ](https://eclipseide.org/)
- [Apache Tomcat 10.1.31](https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.31/bin/apache-tomcat-10.1.31-windows-x64.zip)



## Guias de instalação

- [Configurar a variável de ambiente da JDK 21](https://confluence.atlassian.com/confbr1/configurando-a-variavel-java_home-no-windows-933709538.html)
 
## Como rodar localmente
Depois de importar o projeto em sua Eclipse IDE como um projeto maven siga esse passo a passo para rodar a aplicação no servidor Tomcat.

  1. Clique com o botão esquerdo no projeto e selecione a opção 'Run as' -> 'Run on server'.
  2. Selecione 'Manually define a new server':
      - Selecione a pasta do Apache e selecione Tomcat v10.1 server.
      - Avance para a próxima etapa.
  3. Informe o caminho que a pasta do Tomcat 10.1.31 foi descompactada.
  4. Irá aparecer um pasta Servers e o servidor irá iniciar.
