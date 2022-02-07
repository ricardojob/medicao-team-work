# Componente Prova de Conceito de Autenticação

## Sobre

O projeto foi desenvolvido com SpringBoot e Mysql
A forma de autenticação utilizada é apenas uma prova de conceito.
## Instalação
Antes de tudo, execute <code>mvn clean install -DskipTests</code> para fazer o build do projeto.
<br>
<br>
Para utilizar o componente, execute o trecho <code>docker-compose up --build</code>, que inicializará dois contâiners docker, um que proverá um servidor mysql e outro que proverá a aplicação spring.


## Utilização

O projeto pode ser executado a partir da classe <code>AuthModuleApplication</code> e estará disponível no endereço <code>localhost:8090/</code>
<br>
<br>
A princípio, utilize a rota <code>/register</code>, passando <code>username</code>, <code>password</code> e <code>userType (TEACHER, STUDENT)</code> para criar um acesso.
<br>
<br>
Para logar, a rota <code>/login</code> pode ser utilizada passando os parâmetros <code>username</code> e <code>password</code>.<br> <br>
Ela retornará um userId, que poderá ser utilizado na rota <code>/isTeacher</code>, que recebe como parâmetro o <code>userId</code>, e retorna um booleano indicando se o usuário logado é professor ou não.
<br>
Dessa forma, o presente componente provê as funcionalidades de login, registro e verificação do tipo de usuário.
