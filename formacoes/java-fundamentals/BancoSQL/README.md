# BancoSQL - Projeto de Banco de Dados

Projeto Java para gerenciamento e persistência de dados com JDBC.

## Pré-requisitos

- Java 11 ou superior
- Gradle 7+ (incluído via wrapper)
- MySQL ou PostgreSQL

## Configuração

### 1. Configurar Banco de Dados

Copie o arquivo de exemplo e configure suas credenciais:

```bash
cp db.properties.example db.properties
```

Edite o arquivo `db.properties` com suas configurações:

```properties
db.host=localhost
db.port=3306
db.name=seu_banco
db.username=seu_usuario
db.password=sua_senha
```

### 2. Compilar o Projeto

Windows:
```bash
gradlew.bat build
```

Linux/Mac:
```bash
./gradlew build
```

### 3. Executar a Aplicação

Windows:
```bash
gradlew.bat run
```

Linux/Mac:
```bash
./gradlew run
```

## Estrutura do Projeto

```
BancoSQL/
├── src/main/java/br/com/dio/
│   ├── Main.java
│   └── persistence/
│       ├── *DAO.java          # Classes de acesso a dados
│       └── entity/            # Entidades do banco
├── build.gradle               # Configuração do Gradle
├── db.properties.example      # Exemplo de configuração
└── README.md
```

## Tecnologias

- Java
- JDBC
- Gradle
- MySQL/PostgreSQL
