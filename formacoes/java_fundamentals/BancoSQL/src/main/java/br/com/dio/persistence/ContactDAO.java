package br.com.dio.persistence;

import br.com.dio.persistence.entity.ContactEntity;
import br.com.dio.persistence.entity.EmployeeEntity;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gerenciar contatos de funcionários (e-mail, telefone, etc).
 * Usa PreparedStatement para segurança contra SQL Injection.
 */
public class ContactDAO {

    /**
     * Insere um novo contato no banco de dados e recupera o ID gerado.
     * 
     * @param entity O contato a ser inserido (deve ter o employee associado)
     */
    public void insert(final ContactEntity entity){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(
                        "INSERT INTO contacts (description, type, employee_id) values (?, ?, ?);",
                        Statement.RETURN_GENERATED_KEYS  // Permite recuperar o ID gerado
                )
        ){
            // Preenche os 3 parâmetros da query de forma segura
            statement.setString(1, entity.getDescription());
            statement.setString(2, entity.getType());
            statement.setLong(3, entity.getEmployee().getId());
            statement.executeUpdate();
            
            // Esse código recupera o ID gerado automaticamente pelo banco de dados após um INSERT. 
            // Só funciona pois existe o Statement.RETURN_GENERATED_KEYS ao criar o PreparedStatement:
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {  // move o cursor para o primeiro registro
                    entity.setId(generatedKeys.getLong(1));  // obtém o valor do ID gerado
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Busca todos os contatos de um funcionário específico.
     * 
     * @param employeeId O ID do funcionário
     * @return Lista com todos os contatos do funcionário (vazia se não houver)
     */
    public List<ContactEntity> findByEmployeeId(final long employeeId){
        
        List<ContactEntity> entities = new ArrayList<>();
        
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(
                        "SELECT * FROM contacts WHERE employee_id = ?"
                )
        ){
            // Substitui o ? pelo ID do funcionário
            statement.setLong(1, employeeId);
            statement.executeQuery();
            
            var resultSet = statement.getResultSet();
            
            // Percorre todos os contatos encontrados
            while (resultSet.next()){
                var entity = new ContactEntity();
                /* cria uma entidade temporário somente para salvar o resultado da busca*/
                entity.setId(resultSet.getLong("id"));
                entity.setDescription(resultSet.getString("description"));
                entity.setType(resultSet.getString("type"));
                
                // Cria um objeto Employee parcial apenas com o ID
                entity.setEmployee(new EmployeeEntity());
                entity.getEmployee().setId(resultSet.getLong("employee_id"));
                
                entities.add(entity); // adiciona a entidade à lista de resultados
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entities;
    }

}