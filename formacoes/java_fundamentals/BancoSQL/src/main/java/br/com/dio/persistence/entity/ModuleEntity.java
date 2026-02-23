package br.com.dio.persistence.entity;

import lombok.Data;

import java.util.List;

@Data
public class ModuleEntity {
    /* Entidade modulo */
    private long id;

    private String name;

    private List<EmployeeEntity> employees;
}
