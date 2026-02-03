package Telefonia;

public class TelefoneNativo implements Telefone{
    public String nome = "Serviço de telefonia nativo";
    public String descricao = "Serviço nativo de telefonia";
    private int numero;

    public void atender(boolean check) {
        if (check == true){
            System.out.println("Atendendo telefone");
        }
    }


    public void iniciarCorreioVoz() {
        System.out.println("Iniciando correio de voz");
    }


    public void ligar(int numero) {
        System.out.println("Ligando para: "+numero);
    }


}
