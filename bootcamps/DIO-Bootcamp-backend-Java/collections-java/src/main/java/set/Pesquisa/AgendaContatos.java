package main.java.set.Pesquisa;

import java.util.HashSet;
import java.util.Set;

public class AgendaContatos {
    private Set<Contato> contatos;

    public AgendaContatos() {
        this.contatos = new HashSet<>();
    }

    public void addContato(String nome, int numero){
        contatos.add(new Contato(nome, numero));
    }

    public void exibirContato(){
        System.out.println(contatos);
    }

    public Set<Contato> pesquisarContato(String nome){
        Set<Contato> contatosNome = new HashSet<>();
        for(Contato c: contatos){
            if (c.getNome().startsWith(nome)) {
                contatosNome.add(c);
            }
        }
        return contatosNome;
    }

    public Contato updateContato(String nome, int numero){
        Contato contatoAtualizado = null;
        for(Contato c: contatos){
            if (c.getNome().equalsIgnoreCase(nome)) {
                c.setNumero(numero);
                contatoAtualizado = c;
                break;
            }
        }
        return contatoAtualizado;
    }

    public static void main(String[] args){
        AgendaContatos agenda = new AgendaContatos();

        agenda.addContato("Ana", 11234059);
        agenda.addContato("Ana", 114059);
        agenda.addContato("Ana Cariana", 1059);
        agenda.addContato("Ana Molenga", 1059);
        agenda.addContato("Joao", 1121239);
        agenda.addContato("Carlos", 11554059);

        agenda.exibirContato();

        System.out.println(agenda.pesquisarContato("Ana"));

        System.out.println(agenda.updateContato("Ana Cariana", 23902));

        agenda.exibirContato();

    }
}
