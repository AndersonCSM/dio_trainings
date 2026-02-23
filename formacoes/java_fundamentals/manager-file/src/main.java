import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;
import java.io.IOException;

/**
 * Classe principal que demonstra o uso do sistema de persistência de arquivos.
 * Exemplo de uso da implementação IOFilePersistence para gerenciar dados de usuários em CSV.
 * 
 * @author Anderson
 * @version 1.0
 * @since 2026
 */
public class main {

    public static void main(String[] args) throws IOException {

        // Inicializa o gerenciador de persistência para o arquivo user.csv
        FilePersistence persistence = new IOFilePersistence("user.csv");
        
        // Escreve três registros de usuários no formato: nome;email;data_nascimento
        System.out.println(persistence.write("Anderson;exemple@exemple.com;15/01/1995"));
        System.out.println(persistence.write("Carlos;exemple@exemple.com;18/04/1945"));
        System.out.println(persistence.write("Kraven;exemple@exemple.com;23/10/1965"));

        // Exibe todo o conteúdo do arquivo
        System.out.println(persistence.findAll());

    }
}
