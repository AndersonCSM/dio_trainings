package main.java.map.OperacoesBasicas;

import java.util.HashMap;
import java.util.Map;

public class AgendaContatos {
    private Map<String, Integer> agendaContato;

    public AgendaContatos() {
        this.agendaContato = new HashMap<>();
    }

    public void addContato(String nome, Integer telefone){
        agendaContato.put(nome, telefone);
    }

    public void removerContato(String nome){
        if (!agendaContato.isEmpty()){
            agendaContato.remove(nome);
        }
    }

    public void exibirContato(){
        System.out.println(agendaContato);
    }

    public Integer pesquisarNome(String nome){
        Integer numero = null;
        if (!agendaContato.isEmpty()) {
            numero = agendaContato.get(nome);
        }
        return numero;
    }

    public static void main(String[] arfs){
        AgendaContatos agenda = new AgendaContatos();

        agenda.addContato("Carlos", 256922);
        agenda.addContato("Carlos", 293012);
        agenda.addContato("Carlos Crasto", 211112);
        agenda.addContato("Caramelin", 595012);
        agenda.addContato("KON", 293323);
        agenda.addContato("Juiz", 999012);

        agenda.exibirContato();

        agenda.removerContato("KON");
        System.out.println(agenda.pesquisarNome("Carlos"));
    }
}
