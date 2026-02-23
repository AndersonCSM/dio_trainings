package br.com.dio.persistence;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import static java.time.ZoneOffset.UTC;
import java.util.ArrayList;
import java.util.List;

import br.com.dio.persistence.entity.EmployeeEntity;
import br.com.dio.persistence.entity.ModuleEntity;

/**
 * DAO para gerenciar módulos de treinamento e seus funcionários associados.
 * Demonstra como lidar com relacionamento N:N usando JOINs em JDBC.
 */
public class ModuleDAO {

    /**
     * Busca apenas os IDs e nomes dos módulos sem filtrar por funcionários.
     * Útil para validar quais módulos existem antes de associar a funcionários.
     * 
     * @return Lista com todos os módulos (sem funcionários)
     */
    public List<ModuleEntity> findAllModules() {
        List<ModuleEntity> entities = new ArrayList<>();
        var sql = "SELECT id, name FROM modules ORDER BY id";
        
        try(
            var connection = ConnectionUtil.getConnection();
            var statement = connection.prepareStatement(sql)
        ){
            var resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ModuleEntity module = new ModuleEntity();
                module.setId(resultSet.getLong("id"));
                module.setName(resultSet.getString("name"));
                entities.add(module);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entities;
    }

    /**
     * Busca todos os módulos com seus respectivos funcionários.
     * Usa INNER JOIN para relacionar modules ↔ accesses ↔ employees.
     * 
     * @return Lista com todos os módulos e seus funcionários
     */
    public List<ModuleEntity> findAll(){
        
        List<ModuleEntity> entities = new ArrayList<>();
        
        // Query com 2 INNER JOINs: busca módulos + relacionamentos + funcionários
        var sql = "select m.id module_id,\n" +
                "       m.name module_name,\n" +
                "       e.id employee_id ,\n" +
                "       e.name employee_name,\n" +
                "       e.salary employee_salary,\n" +
                "       e.birthday employee_birthday\n" +
                "  from modules m\n" +
                " inner join accesses a \n" +
                "    on a.module_id = m.id \n" +
                " INNER JOIN  employees e \n" +
                "    on e.id = a.employee_id\n" +
                " ORDER BY m.id";
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(sql)
        ){
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            
            // Controla se há próxima linha
            var hasNext = resultSet.next();
            
            // Loop principal: um módulo por iteração
            while (hasNext){
                ModuleEntity module = new ModuleEntity();
                
                // Preenche os dados do módulo (apenas uma vez por módulo)
                module.setId(resultSet.getLong("module_id"));
                module.setName(resultSet.getString("module_name"));
                module.setEmployees(new ArrayList<>());

                // Loop interno: agrupa todos os funcionários do MESMO módulo
                do{
                    var employee = new EmployeeEntity();
                    employee.setId(resultSet.getLong("employee_id"));
                    employee.setName(resultSet.getString("employee_name"));
                    employee.setSalary(resultSet.getBigDecimal("employee_salary"));

                    // Converte Timestamp para OffsetDateTime
                    var birthdayInstant = resultSet.getTimestamp("employee_birthday").toInstant();
                    employee.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                    
                    module.getEmployees().add(employee);
                    
                    hasNext = resultSet.next();
                
                // Continua enquanto for o MESMO módulo (mesm ID)
                }while ((hasNext) && (module.getId() == resultSet.getLong("module_id")));
                
                entities.add(module); // adiciona o módulo à lista de resultados
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entities;
    }

}