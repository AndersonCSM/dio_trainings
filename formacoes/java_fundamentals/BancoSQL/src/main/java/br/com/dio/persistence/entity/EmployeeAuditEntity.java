package br.com.dio.persistence.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


public record EmployeeAuditEntity (
        /* Registra alterações em funcionarios (valores antigos e novos) */
    long employeeId,
    String name,
    String oldName,
    BigDecimal salary,
    BigDecimal oldSalary,
    OffsetDateTime birthday,
    OffsetDateTime oldBirthday,
    OperationEnum operation
            )
    {

    }