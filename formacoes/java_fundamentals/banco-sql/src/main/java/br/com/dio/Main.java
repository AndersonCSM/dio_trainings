package br.com.dio;

import java.util.Locale;

import br.com.dio.persistence.ContactDAO;
import br.com.dio.persistence.EmployeeAuditDAO;
import br.com.dio.persistence.EmployeeParamDAO;
import br.com.dio.persistence.ModuleDAO;
import br.com.dio.persistence.entity.EmployeeEntity;
import net.datafaker.Faker;

public class Main {

    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
    private final static ContactDAO contactDAO = new ContactDAO();
    private final static ModuleDAO moduleDAO = new ModuleDAO();
    private final static Faker faker = new Faker(Locale.of("pt", "BR"));

    public static void main(String[] args) {
        // Flyway não será utilizado neste projeto
        // As migrações de banco foram executadas manualmente via DBeaver
        // Se futuro, usar variáveis de ambiente ou gradle.properties para proteger credenciais
        /*var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/jdbc-sample", "root", "root")
                .load();
        flyway.migrate();  // Executa scripts SQL de migração
        */

        
        var insert = new EmployeeEntity();
        
        /*
        insert.setName("Miguel");
        insert.setSalary(new BigDecimal("2800"));
        insert.setBirthday(OffsetDateTime.now().minusYears(18));

        System.out.println(insert);
        employeeDAO.insert(insert);
        System.out.println(insert);
        */

        //employeeDAO.findAll().forEach(System.out::println);

        //System.out.println(employeeDAO.findById(1));
        
        /*
        var update = new EmployeeEntity();
        update.setId(insert.getId());
        update.setName("Gabriel");
        update.setSalary(new BigDecimal("5500"));
        update.setBirthday(OffsetDateTime.now().minusYears(36).minusDays(10));
        employeeDAO.update(update);

        System.out.println(employeeDAO.findById(insert.getId()));
        */

        //employeeDAO.delete(insert.getId());

        //employeeAuditDAO.findAll().forEach(System.out::println);

        /*
        var entities = Stream.generate(() -> {
            var employee = new EmployeeEntity();
            employee.setName(faker.name().fullName());
            employee.setSalary(new BigDecimal(faker.number().digits(4)));
            employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.MIN, UTC));
            return employee;
        }).limit(10000).toList();

        employeeDAO.insert(entities);
        */

        /* 
        var employee = new EmployeeEntity();
        employee.setName("João");
        employee.setSalary(new BigDecimal("3200"));
        employee.setBirthday(OffsetDateTime.now().minusYears(25));
        System.out.println(employee);
        employeeDAO.insert(employee);
        System.out.println(employee);

        var contact = new ContactEntity();
        contact.setDescription("miguel@miguel.com");
        contact.setType("e-mail");
        contact.setEmployee(employee);
        contactDAO.insert(contact);
        */

        //System.out.println(employeeDAO.findById(1));
        /*
        var employee = new EmployeeEntity();
        employee.setName("João");
        employee.setSalary(new BigDecimal("3200"));
        employee.setBirthday(OffsetDateTime.now().minusYears(25));
        System.out.println(employee);
        employeeDAO.insert(employee);
        System.out.println(employee);

        var contact1 = new ContactEntity();
        contact1.setDescription("miguel@miguel.com");
        contact1.setType("e-mail");
        contact1.setEmployee(employee);
        contactDAO.insert(contact1);

        var contact2 = new ContactEntity();
        contact2.setDescription("33963365002");
        contact2.setType("celular");
        contact2.setEmployee(employee);
        contactDAO.insert(contact2);
        */

        //System.out.println(employeeDAO.findById(1));
        //employeeDAO.findAll().forEach(System.out::println);

        /*
        // Primeiro: buscar módulos reais que existem no banco
        var modules = moduleDAO.findAllModules();
        var moduleIds = modules.stream().map(ModuleEntity::getId).toList();
        
        // Se não há módulos no banco, criar alguns antes
        if (moduleIds.isEmpty()) {
            System.out.println("AVISO: Nenhum módulo encontrado. Insira módulos na tabela 'modules' primeiro.");
            System.out.println("Exemplo: INSERT INTO modules (name) VALUES ('Java'), ('Spring'), ('SQL');");
        } else {
            // Agora criar funcionários com módulos reais
            var entities = Stream.generate(() -> {
                var employee = new EmployeeEntity();
                employee.setName(faker.name().fullName());
                employee.setSalary(new BigDecimal(faker.number().digits(4)));
                employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.MIN, UTC));
                employee.setModules(new ArrayList<>());
                
                // Usar apenas módulos que realmente existem
                var moduleAmount = Math.min(faker.number().numberBetween(1, 4), moduleIds.size());
                for (int i = 0; i < moduleAmount; i++) {
                    var module = new ModuleEntity();
                    module.setId(moduleIds.get(i));
                    employee.getModules().add(module);
                }
                return employee;
            }).limit(3).toList();
            
            System.out.printf("Inserindo 3 funcionários com acesso a %d módulos...%n", moduleIds.size());
            entities.forEach(employeeDAO::insert);
            System.out.println("Funcionários inseridos com sucesso!");
        }
        */

        //moduleDAO.findAll().forEach(System.out::println);
    }

}