package br.com.dio.persistence;

import br.com.dio.persistence.entity.EmployeeAuditEntity;
import br.com.dio.persistence.entity.OperationEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;

/**
 * DAO para consultar o histórico de auditoria de funcionários.
 * Lê dados de uma VIEW que consolida informações de uma tabela de auditoria.
 * A auditoria é geralmente populada por TRIGGERS no banco de dados.
 */
public class EmployeeAuditDAO {

    /**
     * Busca todo o histórico de alterações (INSERT/UPDATE/DELETE) em funcionários.
     * 
     * @return Lista com todos os registros de auditoria
     */
    public List<EmployeeAuditEntity> findAll(){
        
        List<EmployeeAuditEntity> entities = new ArrayList<>();
        
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            // Consulta a VIEW de auditoria (criada no banco)
            statement.executeQuery("SELECT * FROM view_employee_audit");
            
            var resultSet = statement.getResultSet();
            
            // Percorre cada registro de auditoria
            while (resultSet.next()){
                // Usa record (imutável) ao invés de entity traditional
                entities.add(new EmployeeAuditEntity(
                        resultSet.getLong("employee_id"),
                        resultSet.getString("name"),
                        resultSet.getString("old_name"),
                        resultSet.getBigDecimal("salary"),
                        resultSet.getBigDecimal("old_salary"),
                        getDateTimeOrNull(resultSet, "birthday"),       // Valor atual
                        getDateTimeOrNull(resultSet, "old_birthday"),   // Valor anterior
                        OperationEnum.getByDbOperation(resultSet.getString("operation"))
                ));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entities;
    }

    /**
     * Método auxiliar para converter Timestamp em OffsetDateTime, tratando valores NULL.
     * Necessário porque campos de auditoria podem ser nulos.
     * 
     * @param resultSet O ResultSet da query
     * @param field O nome do campo a ser extraído
     * @return OffsetDateTime ou null se o campo for nulo no banco
     * @throws SQLException Se houver erro ao acessar o campo
     */
    public OffsetDateTime getDateTimeOrNull(final ResultSet resultSet, final String field) throws SQLException {
        return isNull(resultSet.getTimestamp(field)) ? null :
                OffsetDateTime.ofInstant(
                        resultSet.getTimestamp(field).toInstant(),
                        UTC);
    }

}