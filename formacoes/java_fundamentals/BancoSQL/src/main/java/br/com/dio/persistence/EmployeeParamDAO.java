package br.com.dio.persistence;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import static java.time.ZoneOffset.UTC;
import java.util.ArrayList;
import java.util.List;
import static java.util.TimeZone.LONG;

import br.com.dio.persistence.entity.ContactEntity;
import br.com.dio.persistence.entity.EmployeeEntity;
import br.com.dio.persistence.entity.ModuleEntity;

/**
 * DAO (Data Access Object) para gerenciar operações de funcionários usando PreparedStatement.
 * IMPLEMENTAÇÃO SEGURA - usa parâmetros (?) ao invés de concatenação de strings.
 * 
 * Funcionalidades avançadas:
 * - Batch inserts (inserir milhares de registros rapidamente)
 * - Transações com commit/rollback
 * - Recuperação de IDs gerados automaticamente
 * - Gerenciamento automático de relacionamentos (módulos/contatos)
 * - Suporte a stored procedures
 */
public class EmployeeParamDAO {

    // Instâncias dos DAOs auxiliares para gerenciar relacionamentos
    private final ContactDAO contactDAO = new ContactDAO();
    private final AccessDAO accessDAO = new AccessDAO();

    /**
     * Insere um novo funcionário no banco de dados e seus relacionamentos com módulos.
     * Após a inserção, recupera o ID gerado e atualiza o objeto.
     * 
     * @param entity O funcionário a ser inserido (com lista de módulos opcional)
     */
    public void insert(final EmployeeEntity entity){
        // Try-with-resources: fecha automaticamente recursos ao final
        try(
                var connection = ConnectionUtil.getConnection();
                // RETURN_GENERATED_KEYS permite recuperar o ID gerado pelo banco
                var statement = connection.prepareStatement(
                        "INSERT INTO employees (name, salary, birthday) values (?, ?, ?);",
                        Statement.RETURN_GENERATED_KEYS
                )
        ){
            // Substitui os placeholders (?) pelos valores reais de forma SEGURA
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            // Converte OffsetDateTime para Timestamp em UTC
            statement.setTimestamp(3,
                    Timestamp.valueOf(entity.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime())
            );
            
            // Executa o INSERT no banco de dados
            statement.executeUpdate();
            
            // Recupera o ID gerado automaticamente pelo banco
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }
            
            // Para cada módulo associado ao funcionário, cria o relacionamento na tabela accesses
            // Verifica se a lista de módulos não é nula antes de processar
            if (entity.getModules() != null) {
                entity.getModules().stream()
                        .map(ModuleEntity::getId)
                        .forEach(m -> accessDAO.insert(entity.getId(), m));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Insere um funcionário usando uma Stored Procedure do banco de dados.
     * Stored procedures permitem encapsular lógica complexa no próprio banco.
     * 
     * @param entity O funcionário a ser inserido
     */
    public void insertWithProcedure(final EmployeeEntity entity){
        try(
                var connection = ConnectionUtil.getConnection();
                // prepareCall() é usado para chamar procedures/functions do banco
                var statement = connection.prepareCall(
                        "call prc_insert_employee(?, ?, ?, ?);"
                )
        ){
            // registerOutParameter: define que o parâmetro 1 é de SAÍDA (retorna o ID)
            statement.registerOutParameter(1, LONG);
            // Parâmetros de entrada (2, 3, 4)
            statement.setString(2, entity.getName());
            statement.setBigDecimal(3, entity.getSalary());
            statement.setTimestamp(4,
                    Timestamp.valueOf(entity.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime())
            );
            
            // Executa a procedure
            statement.execute();
            
            // Recupera o ID devolvido pela procedure no parâmetro OUT
            entity.setId(statement.getLong(1));
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Insere múltiplos funcionários em batch (lote) usando transações.
     * MUITO mais eficiente do que inserir um por um (pode ser até 100x mais rápido).
     * 
     * Como funciona:
     * - Agrupa vários INSERTs e executa de uma vez (a cada 1000 registros)
     * - Usa transação: se der erro em qualquer registro, NENHUM é salvo (atomicidade)
     * - Ideal para importar grandes volumes de dados
     * 
     * @param entities Lista com os funcionários a serem inseridos
     */
    public void insert(final List<EmployeeEntity> entities){
        try(var connection = ConnectionUtil.getConnection()){
            var sql = "INSERT INTO employees (name, salary, birthday) values (?, ?, ?);";
            
            try(var statement = connection.prepareStatement(sql)){
                // Desliga commit automático = inicia uma TRANSAÇÃO
                connection.setAutoCommit(false);
                
                // Percorre todos os funcionários da lista
                for (int i = 0; i < entities.size(); i++) {
                    statement.setString(1, entities.get(i).getName());
                    statement.setBigDecimal(2, entities.get(i).getSalary());
                    
                    var timestamp = Timestamp.valueOf(entities.get(i).getBirthday().atZoneSimilarLocal(UTC)
                            .toLocalDateTime());
                    statement.setTimestamp(3, timestamp);
                    
                    // addBatch(): adiciona à fila, mas NÃO executa ainda
                    statement.addBatch();
                    
                    // A cada 1000 registros OU no último, executa o lote todo de uma vez
                    if (i % 1000 == 0 || i == entities.size() -1) statement.executeBatch();
                }
                
                // Se chegou aqui, tudo deu certo → salva tudo no banco
                connection.commit();

            }catch (SQLException ex){
                // Se der erro, CANCELA TUDO (volta ao estado antes do início da transação)
                connection.rollback();
                ex.printStackTrace();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Atualiza os dados de um funcionário existente no banco.
     * Usa PreparedStatement para proteção contra SQL Injection.
     * 
     * @param entity O funcionário com os dados atualizados (deve ter ID preenchido)
     */
    public void update(final EmployeeEntity entity){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(
                        "UPDATE employees set name = ?, salary = ?, birthday = ? WHERE id = ?"
                )
        ){
            // Preenche os 4 parâmetros da query
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3,
                    Timestamp.valueOf(entity.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime())
            );
            statement.setLong(4, entity.getId());  // WHERE id = ?
            
            statement.executeUpdate();
            
            // getUpdateCount() retorna quantas linhas foram modificadas
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
        
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Remove um funcionário do banco de dados pelo ID.
     * 
     * @param id O ID do funcionário a ser removido
     */
    public void delete(final long id){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?")
        ){
            statement.setLong(1, id);
            statement.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Busca todos os funcionários com seus contatos.
     * Para cada funcionário, faz uma query adicional para buscar os contatos.
     * 
     * Nota: Este método gera N+1 queries (1 para funcionários + N para contatos).
     * Para grandes volumes, considere usar JOIN (ver findById).
     * 
     * @return Lista com todos os funcionários e seus contatos
     */
    public List<EmployeeEntity> findAll(){
        // Cria lista vazia para armazenar os resultados
        List<EmployeeEntity> entities = new ArrayList<>();
        
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            // Busca todos os funcionários ordenados por nome
            statement.executeQuery("SELECT * FROM employees ORDER BY name");
            var resultSet = statement.getResultSet();
            
            // Percorre cada linha do resultado
            while (resultSet.next()){
                var entity = new EmployeeEntity();
                
                // Extrai os dados de cada coluna
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                
                // Converte Timestamp do banco para OffsetDateTime do Java
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                
                // Para cada funcionário, busca todos os seus contatos (query adicional)
                entity.setContacts(contactDAO.findByEmployeeId(resultSet.getLong("id")));
                
                entities.add(entity);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entities;
    }

    /**
     * Busca um funcionário específico com todos os seus contatos usando JOIN.
     * Esta abordagem é mais eficiente que fazer queries separadas (N+1).
     * 
     * LEFT JOIN garante que o funcionário seja retornado mesmo sem contatos.
     * ResultSet terá uma linha para cada contato (funcionário duplicado em cada linha).
     * 
     * @param id O ID do funcionário a ser buscado
     * @return O funcionário com seus contatos (ou objeto vazio se não existir)
     */
    public EmployeeEntity findById(final long id){
        var entity = new EmployeeEntity();
        
        // Query com JOIN: busca funcionário + todos os contatos em uma única query
        var sql = "SELECT e.id employee_id,\n" +
                "       e.name,\n" +
                "       e.salary,\n" +
                "       e.birthday,\n" +
                "       c.id contact_id,\n" +
                "       c.description,\n" +
                "       c.type\n" +
                "  FROM employees e\n" +
                " LEFT JOIN contacts c\n" +
                "   ON c.employee_id = e.id \n" +
                "WHERE e.id = ?";
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(sql)
        ){
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            
            // Se encontrou o funcionário
            if (resultSet.next()){
                // Preenche os dados do funcionário (só uma vez)
                entity.setId(resultSet.getLong("employee_id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                
                // Cria lista vazia para os contatos
                entity.setContacts(new ArrayList<>());
                
                // do-while porque a primeira linha já foi lida no if acima
                // Cada linha do ResultSet representa um contato diferente
                do {
                    var contact = new ContactEntity();
                    contact.setId(resultSet.getLong("contact_id"));
                    contact.setDescription(resultSet.getString("description"));
                    contact.setType(resultSet.getString("type"));
                    
                    entity.getContacts().add(contact);
                    
                } while (resultSet.next());  // Avança para o próximo contato
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entity;
    }

}