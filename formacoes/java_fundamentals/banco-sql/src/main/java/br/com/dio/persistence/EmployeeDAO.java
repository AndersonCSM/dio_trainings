package br.com.dio.persistence;

import br.com.dio.persistence.entity.EmployeeEntity;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

/**
 * DAO (Data Access Object) para gerenciar operações de funcionários no banco de dados.
 * O padrão DAO (Data Access Object) em Java separa a lógica de negócios da persistência de dados,
 * utilizando interfaces para abstrair operações CRUD.
 * 
 * Para uma implementação segura, veja {@link EmployeeParamDAO} que usa PreparedStatement corretamente.
 */
public class EmployeeDAO {
    
    /**
     * Insere um novo funcionário no banco de dados usando PreparedStatement.
     * 
     * @param entity O funcionário a ser inserido (sem ID, que será gerado pelo banco)
     */
    public void insert(final EmployeeEntity entity){
        // Try-with-resources: fecha automaticamente a conexão e o statement ao final
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement(
                    "INSERT INTO employees (name, salary, birthday) values (?, ?, ?);"
                )
        ){
            // Substitui os placeholders (?) pelos valores reais
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            
            // Converte OffsetDateTime para Timestamp (formato que o banco entende)
            statement.setTimestamp(3, 
                java.sql.Timestamp.valueOf(
                    entity.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime()
                )
            );
            
            // Executa o INSERT no banco de dados
            statement.executeUpdate();
            
            System.out.println("INSERTED");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Atualiza os dados de um funcionário existente.
     * VULNERÁVEL A SQL INJECTION - usa concatenação de strings!
     * Se entity.getName() contiver aspas ou caracteres especiais, pode quebrar a query.
     * 
     * @param entity O funcionário com os dados atualizados (deve ter ID preenchido)
     */
    public void update(final EmployeeEntity entity){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()  // Statement simples (sem proteção)
        ){
            // ATENÇÃO: Concatenação direta = SQL Injection vulnerability
            var sql = "UPDATE employees set " +
                    "name     = '" + entity.getName() + "'," +
                    "salary   = " + entity.getSalary().toString() + "," +
                    "birthday = '" + formatOffsetDateTime(entity.getBirthday()) + "'" +
                    "WHERE id = " + entity.getId();
            statement.executeUpdate(sql);
            
            // getUpdateCount() retorna quantas linhas foram modificadas (normalmente 1)
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Remove um funcionário do banco de dados pelo ID.
     * Usa concatenação de strings (aceitável aqui pois ID é long, não string).
     * 
     * @param id O ID do funcionário a ser removido
     */
    public void delete(final long id){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            // Como id é long (número), não há risco de SQL injection aqui
            var sql = "DELETE FROM employees WHERE id = " + id;
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Busca todos os funcionários do banco de dados ordenados por nome.
     * 
     * @return Lista com todos os funcionários encontrados (lista vazia se não houver registros)
     */
    public List<EmployeeEntity> findAll(){
        // Cria uma lista vazia para armazenar os resultados
        List<EmployeeEntity> entities = new ArrayList<>();
        
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            // Executa a query SELECT no banco
            statement.executeQuery("SELECT * FROM employees ORDER BY name");
            
            // ResultSet é como uma "tabela em memória" com os resultados
            var resultSet = statement.getResultSet();
            
            // Percorre cada linha do resultado
            while (resultSet.next()){
                var entity = new EmployeeEntity();
                
                // Extrai os valores de cada coluna pelo nome
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                
                // Converte Timestamp do banco para OffsetDateTime do Java
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                
                entities.add(entity);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entities;
    }

    /**
     * Busca um funcionário específico pelo ID.
     * 
     * @param id O ID do funcionário a ser buscado
     * @return O funcionário encontrado (ou objeto vazio se não existir)
     */
    public EmployeeEntity findById(final long id){
        
        var entity = new EmployeeEntity();
        
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            // Como id é numérico, não há risco de SQL injection aqui
            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            
            var resultSet = statement.getResultSet();
            
            // if (ao invés de while) porque esperamos apenas 1 resultado
            if (resultSet.next()){
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                
                // Converte o Timestamp do banco para OffsetDateTime
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entity;
    }

    /**
     * Converte OffsetDateTime para String no formato aceito pelo MySQL.
     * Usado apenas nos métodos que fazem concatenação de strings no SQL.
     * 
     * @param dateTime A data/hora a ser formatada
     * @return String no formato "yyyy-MM-dd HH:mm:ss" em UTC
     */
    private String formatOffsetDateTime(final OffsetDateTime dateTime){
        // Converte para UTC para garantir consistência
        var utcDatetime = dateTime.withOffsetSameInstant(UTC);
        
        // Formata no padrão que o MySQL espera
        return utcDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}