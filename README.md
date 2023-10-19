# Conector-SqlServer
Este é um projeto Java que fornece uma conexão com dois bancos de dados SQL Server. O projeto utiliza o Spring Boot para criar uma aplicação Java com funcionalidades web.

## Pré-requisitos
- Java 1.8 ou superior
- Maven
- Um ambiente de desenvolvimento Java (por exemplo, Eclipse ou IntelliJ IDEA)

## Configuração
Para executar o projeto, você deve configurar as seguintes dependências no seu ambiente:

- Spring Boot
- Spring Data JDBC
- Spring Web
- Spring Boot DevTools
- Microsoft SQL Server JDBC Driver
- Spring Boot Starter Test
- Swagger para documentação da API
- Commons IO

Certifique-se de que todas essas dependências estejam configuradas no seu ambiente antes de executar o projeto.

### Variáveis de Ambiente

Este projeto utiliza variáveis de ambiente para configurar a conexão com o banco de dados SQL Server. Você deve configurar as seguintes variáveis de ambiente no seu ambiente antes de executar o projeto:

- `PRI_HOSTNAME`: Hostname do primeiro banco de dados SQL Server.
- `PRI_PORTA`: Porta do primeiro banco de dados SQL Server.
- `PRI_DATABASE`: Nome do primeiro banco de dados SQL Server.
- `PRI_USERNAME`: Nome de usuário do primeiro banco de dados SQL Server.
- `PRI_SENHA`: Senha do primeiro banco de dados SQL Server.

- `SEC_HOSTNAME`: Hostname do segundo banco de dados SQL Server.
- `SEC_PORTA`: Porta do segundo banco de dados SQL Server.
- `SEC_DATABASE`: Nome do segundo banco de dados SQL Server.
- `SEC_USERNAME`: Nome de usuário do segundo banco de dados SQL Server.
- `SEC_SENHA`: Senha do segundo banco de dados SQL Server.

### Configuração do Spring

O projeto utiliza o Spring Boot e as seguintes configurações para a conexão com o banco de dados SQL Server:

- Configuração para o primeiro banco de dados:

```properties
spring.datasource.primary-sqlserver.jdbc-url=jdbc:sqlserver://${PRI_HOSTNAME}:${PRI_PORTA};databaseName=${PRI_DATABASE};encrypt=true;trustServerCertificate=true
spring.datasource.primary-sqlserver.username=${PRI_USERNAME}
spring.datasource.primary-sqlserver.password=${PRI_SENHA}
spring.datasource.primary-sqlserver.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```
- Configuração para o segundo banco de dados:
```properties
spring.datasource.secondary-sqlserver.jdbc-url=jdbc:sqlserver://${SEC_HOSTNAME}:${SEC_PORTA};databaseName=${SEC_DATABASE};encrypt=true;trustServerCertificate=true
spring.datasource.secondary-sqlserver.username=${SEC_USERNAME}
spring.datasource.secondary-sqlserver.password=${SEC_SENHA}
spring.datasource.secondary-sqlserver.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

## Como Executar
Você pode executar o projeto localmente usando a seguinte abordagem:

1. Clone o repositório.

```bash
git clone https://github.com/pc3168/conector-sqlserver.git
```

2. Navegue até o diretório do projeto.

```bash
cd conector-sqlserver
```
3. Execute o projeto com o seguinte comando:

```bash
mvn spring-boot:run
```

O projeto estará disponível em ```http://localhost:9090```.

## Documentação da API
Este projeto utiliza o Swagger para fornecer documentação da API. Após iniciar o projeto, você pode acessar a documentação da API em ```http://localhost:9090/swagger-ui.html```.

## Contribuição
Sinta-se à vontade para contribuir com este projeto abrindo novas questões, sugerindo melhorias ou enviando solicitações de pull.

## Autor
Paulo César

## Controller

O controlador é uma parte importante deste projeto e lida com solicitações de API relacionadas à construção e execução de consultas em dois bancos de dados diferentes. Ele fornece várias maneiras de enviar consultas, incluindo o uso de parâmetros da URL, upload de arquivos e JSON no corpo da solicitação.

### Métodos do Controlador

#### `POST /query/`

Este endpoint permite enviar uma consulta como um arquivo anexado a uma solicitação. Ele aceita os seguintes parâmetros:

- `choose`: Um valor que indica qual banco de dados deve ser usado (FIRST ou SECOND).
- `file`: Um arquivo contendo a consulta.

Este método é útil quando você deseja enviar uma consulta que está armazenada em um arquivo. O controlador extrai o conteúdo do arquivo e executa a consulta no banco de dados especificado.

#### `POST /query/body`

Neste endpoint, você pode enviar uma consulta no corpo da solicitação em formato JSON. Ele aceita o seguinte parâmetro:

- `modelBody`: Um objeto JSON que inclui as seguintes propriedades:
  - `choose`: Um valor que indica qual banco de dados deve ser usado (FIRST ou SECOND).
  - `query`: A consulta que deseja executar.

Use este método quando desejar enviar a consulta diretamente no corpo da solicitação, em vez de usar um arquivo anexado.

#### `GET /query/url`

Este endpoint permite enviar uma consulta como um parâmetro na URL. Ele aceita os seguintes parâmetros:

- `choose`: Um valor que indica qual banco de dados deve ser usado (FIRST ou SECOND).
- `query`: A consulta que deseja executar.

Você pode usar este método quando desejar incluir a consulta diretamente na URL da solicitação.

### Exemplos de Uso

Aqui estão exemplos de como usar os métodos do controlador:

- Para enviar uma consulta a partir de um arquivo usando `POST /query/`:

```json
{
  "choose": "FIRST",
  "file": "@/caminho/do/arquivo.sql;type=text/plain"  
} 
```
```curl
curl -X POST "http://localhost:9090/query/?choose=FIRST" -H "accept: application/json" -H "Content-Type: multipart/form-data" -F "file=@arquivo.sql;type=text/plain"
```


- Para enviar uma consulta no corpo da solicitação usando `POST /query/body`:
```json
{
    "choose": "FIRST",
    "query": "SELECT * FROM tabela"
}
```
```curl
curl -X POST "http://localhost:9090/query/body" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"choose\": \"FIRST\", \"query\": \"SELECT * FROM tabela \"}"
```

- Para enviar uma consulta na URL usando `GET /query/url:`
```sql
GET /query/url?choose=FIRST&query=SELECT * FROM tabela
```
```curl
curl -X GET "http://localhost:9090/query/url?choose=FIRST&query=SELECT%20*%20FROM%20tabela" -H "accept: application/json"
```