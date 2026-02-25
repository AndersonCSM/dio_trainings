-- Tabela de relacionamento N:N entre funcionários e módulos
CREATE TABLE accesses (
    employee_id BIGINT NOT NULL,
    module_id BIGINT NOT NULL,
    PRIMARY KEY (employee_id, module_id),
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    FOREIGN KEY (module_id) REFERENCES modules(id) ON DELETE CASCADE
);

-- Tabela de auditoria para registrar alterações em employees
CREATE TABLE employee_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    name VARCHAR(255),
    old_name VARCHAR(255),
    salary DECIMAL(10, 2),
    old_salary DECIMAL(10, 2),
    birthday TIMESTAMP,
    old_birthday TIMESTAMP NULL,
    operation VARCHAR(10) NOT NULL, -- 'INSERT', 'UPDATE', 'DELETE'
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- VIEW para consultar a auditoria (usada pelo EmployeeAuditDAO)
CREATE VIEW view_employee_audit AS
SELECT 
    employee_id,
    name,
    old_name,
    salary,
    old_salary,
    birthday,
    old_birthday,
    operation,
    changed_at
FROM employee_audit
ORDER BY changed_at DESC;