-- Tabela de funcionários
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    birthday TIMESTAMP NOT NULL
);

-- Tabela de módulos de treinamento
CREATE TABLE modules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabela de contatos dos funcionários (1:N com employees)
CREATE TABLE contacts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    employee_id BIGINT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- Tabela de relacionamento N:N entre funcionários e módulos
CREATE TABLE accesses (
    employee_id BIGINT NOT NULL,
    module_id BIGINT NOT NULL,
    PRIMARY KEY (employee_id, module_id),
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    FOREIGN KEY (module_id) REFERENCES modules(id) ON DELETE CASCADE
);

show tables;