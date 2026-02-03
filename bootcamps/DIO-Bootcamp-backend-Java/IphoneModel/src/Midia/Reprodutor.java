package Midia;

public class Reprodutor implements ReprodutorMusical {
    public String nome = "Reprodutor 5000";
    public String descricao = "Reprodutor de música nativo do iphone versão 5000";

    public void pausar() {
        System.out.println("Música pausada");
    }

    public void selecionarMusica(String musica) {
        System.out.println("Música selecionada: "+ musica);
    }

    public void tocar() {
        System.out.println("Música voltou a tocar");
    }

}
