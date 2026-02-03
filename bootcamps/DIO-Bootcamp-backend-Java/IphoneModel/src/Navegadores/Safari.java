package Navegadores;

public class Safari implements NavegadorInternet {
    public String nome = "Safari";
    public String descricao = "Navegador padrão do Iphone";
    private String MAC = "endereco da máquina";
    private String ip = "endereço de ip";
    private String dns = "representação do serviçõ de DNS";

    public void adicionarNovaAba() {
        System.out.println("Nova guia aberta!");
    }

    public void atualizarPagina() {
        System.out.println("Página atualizada");
    }

    public void exibirPagina(String url) {
        System.out.println("Exibindo a página: <"+ url+">");
    }

}
