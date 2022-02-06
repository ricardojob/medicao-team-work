# Componente Prova de Conceito de Autenticação

## Sobre

O projeto foi desenvolvido com SpringBoot e Mysql
A forma de autenticação utilizada é apenas uma prova de conceito.
##Instalação

Para utilizar o componente, rode o seguinte comando para instalar suas dependências:
<code>mvn install</code> <br><br>
Após isso, é necessário que tenha instalado uma instância do MYSQL. Configure no arquivo <code>persistance.xml</code>
os detalhes de conexão.<br>
Por fim, crie um banco de dados chamado <code>reuseproject</code>

##Utilização

O projeto pode ser executado a partir da classe <code>AuthModuleApplication</code>
<br>
A princípio, utilize a rota <code>/registger</code>, passando <code>username</code>, <code>password</code> e <code>userType (TEACHER, STUDENT)</code> para criar um acesso.
<br>
Para logar, a rota <code>/login</code> pode ser utilizada passando os parâmetros <code>username</code> e <code>password</code>.<br> 
Ela retornará um userId, que poderá ser utilizado na rota <code>/isTeacher</code>, que recebe como parâmetro o <code>userId</code>, e retorna um booleano indicando se o usuário logado é professor ou não.
<br>
Dessa forma, o presente componente provê as funcionalidades de login, registro e verificação do tipo de usuário.
