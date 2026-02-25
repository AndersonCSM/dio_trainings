package br.com.dio.persistence.entity;

import lombok.Data;


@Data
public class ContactEntity {
    /* Entidade contato do funcionario */
    private long id;

    private String description;

    private String type;

    private EmployeeEntity employee;
}
