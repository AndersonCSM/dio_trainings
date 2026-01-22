public class ClassString {
    public void ex1(){
        // JEITO ERRADO (Lento em loops grandes)
        String texto = "";
        for (int i = 0; i < 1000; i++) {
            texto += i; // Cria 1000 objetos String descartáveis!
        }

        // JEITO CORRETO (Eficiente)
        StringBuilder construtor = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            construtor.append(i); // Modifica o mesmo objeto internamente
        }
        String resultadoFinal = construtor.toString();
    }

    public void ex2(){
        // Dado sujo vindo do usuário
        String entrada = "  Joao.Silva@Gmail.com ; 1234-5678  ";

        System.out.println("Original: [" + entrada + "]");

        // 1. trim(): Remove espaços do início e fim
        String semEspaco = entrada.trim();

        // 2. split(): Quebra a string com base em um delimitador
        String[] partes = semEspaco.split(";");
        String email = partes[0].trim();
        String telefone = partes[1].trim();

        // 3. toLowerCase() / toUpperCase(): Padronização
        email = email.toLowerCase();

        // 4. replace(): Substituição
        // Vamos remover o traço do telefone
        telefone = telefone.replace("-", "");

        // 5. substring(): Pegar pedaços
        // Pegar apenas o domínio do email (depois do @)
        int indexArroba = email.indexOf("@");
        String dominio = email.substring(indexArroba + 1);

        // 6. contains(), startsWith(), endsWith(): Verificação
        if (email.endsWith(".com")) {
            System.out.println("Email comercial internacional.");
        }

        System.out.println("Email Limpo: " + email);
        System.out.println("Telefone Limpo: " + telefone);
        System.out.println("Domínio: " + dominio);

        // --- BÔNUS: StringBuilder ---
        // Construindo uma query SQL dinâmica (Exemplo clássico)
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM clientes WHERE 1=1");

        boolean filtroNome = true;
        if(filtroNome) {
            sql.append(" AND nome = 'Joao'"); // Só concatena se necessário
        }

        System.out.println("Query final: " + sql.toString());
    }
}
