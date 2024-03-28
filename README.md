# golden-raspberry-awards-api
API RESTful para consulta e manutenção da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.
A API foi criada visando atender ao nível de maturidade 2 de Richardson.

## Requisitos
Para execução do projeto, é necessário instalação do Oracle Open JDK 22.
O passo-a-passo abaixo foi feito com base no Intellij Community Edition.

## Configurações
- Por padrão a aplicação está configurada com o servlet.contextPath=/api para alterá-lo abra o arquivo application.properties e altere o valor da propriedade.
```sh
    # Context
    server.servlet.contextPath=/api
```
- Para alterar as configurações do banco de dados com URL, usuário, senha e url do console, e ativar/desativar o console, abra o arquivo application.properties. A opções do banco H2 e as propriedades do datasource são exibidas como abaixo:
```sh
    # H2
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2
    
    # Datasource
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.username=sa
    spring.datasource.password=
    spring.datasource.driver-class-name=org.h2.Driver
    spring.jpa.hibernate.ddl-auto=update
```

## Para executar o projeto
Para executar o projeto, nenhuma instalação externa é necessária. Ao ser iniciada, a aplicação cria o banco de dados e o popula com os dados do arquivo movielist.csv, que se encontra em *src/main/resources/*.
1. Clone o repositório o faça download;
2. Se está usando uma ferramenta externa a IDE, importe o projeto como projeto Maven existente;
3. Execute o comando Maven abaixo:
```sh
        $ mvn install -Dmaven.test.skip=true
```
4. Para iniciar a aplicação clique no projeto com o botão direito do mouse, vá até a opção *Run As* e selecione Spring Boot App.

## EndPoints
Para ver a lista de chamadas REST disponíveis, seus parametros, códigos de resposta HTTP, e tipo de retorno, inicie a aplicação e acesse: http://localhost:8080/api/swagger-ui.html/

## Testes
Para executar os testes abra a classe AppTest.java, clique em Run -> Run As -> JUnit Test. Isso fará com que todos os testes de integração implementados sejam executados.
