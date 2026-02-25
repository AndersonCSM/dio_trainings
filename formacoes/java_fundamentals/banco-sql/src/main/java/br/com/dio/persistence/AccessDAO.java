package br.com.dio.persistence;

import java.sql.SQLException;

/**
 * DAO para gerenciar a tabela de relacionamento entre funcionários e módulos.
 * Representa um relacionamento N:N (muitos-para-muitos).
 * Um funcionário pode ter acesso a vários módulos, e um módulo pode ter vários funcionários.
 */
public class AccessDAO {

    /**
     * Cria um relacionamento entre um funcionário e um módulo.
     * Insere um registro na tabela accesses (employee_id, module_id).
     * 
     * @param employeeId O ID do funcionário
     * @param moduleId O ID do módulo ao qual o funcionário terá acesso
     */
    public void insert(final long employeeId, final long moduleId){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(
                        "INSERT INTO accesses (employee_id, module_id) values (?, ?);"
                )
        ){
            // Preenche os 2 IDs (relacionamento)
            statement.setLong(1, employeeId);
            statement.setLong(2, moduleId);
            statement.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}