# Sistema de Gestão de Estoque — Java Swing + MySQL + MVC

## Requisitos
- JDK 17 ou superior
- IntelliJ IDEA
- MySQL Server

## Configuração
1. Abra a pasta do projeto no IntelliJ IDEA como projeto Maven.
2. Execute o arquivo `database/schema.sql` no MySQL Workbench ou cliente MySQL.
3. Abra `src/main/java/br/com/sistemaestoque/util/Conexao.java` e ajuste usuário/senha do MySQL.
4. Aguarde o Maven baixar o MySQL Connector/J.
5. Execute `br.com.sistemaestoque.Main`.

O programa cria um usuário de demonstração `admin` com senha `admin123` ao inicializar o banco, caso ainda não exista.
A tela de login é demonstrativa; em um sistema real, senhas devem ser armazenadas com hash seguro, não em texto puro.

## Estrutura
- `model`: entidades
- `dao`: operações SQL/JDBC
- `controller`: coordenação entre telas e dados
- `view`: telas Swing
- `util`: conexão com o banco
- `database/schema.sql`: cinco tabelas relacionadas

## Funcionalidades
- Login
- Menu principal em abas
- Cadastro/listagem/exclusão de produtos
- Cadastro/listagem de categorias e fornecedores
- Entrada e saída de estoque com validação de saldo
- Relatório de estoque atual

Observação: este é um projeto didático inicial. Para simplificar a interface, categorias e fornecedores são cadastrados em formulários pequenos; produtos são associados a eles pelos IDs.
