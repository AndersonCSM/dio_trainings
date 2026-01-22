import java.util.Optional;

public class ClassOptional {
    // Simulando um método que busca no banco de dados
    public Optional<String> buscarUsuario(int id) {
        if (id == 1) return Optional.of("João");
        return Optional.empty(); // Retorno explícito de vazio
    }

    public void ex1(){
        // Uso correto
        Optional<String> resultado = buscarUsuario(99);

        // "Se existir, imprima. Se não, use 'Desconhecido'"
        System.out.println(resultado.orElse("Usuário Desconhecido"));

        // "Se existir, execute este bloco lambda"
        resultado.ifPresent(nome -> System.out.println("Encontrado: " + nome));
    }
}
