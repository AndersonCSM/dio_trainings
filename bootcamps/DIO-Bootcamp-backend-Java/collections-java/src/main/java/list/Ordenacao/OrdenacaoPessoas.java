package main.java.list.Ordenacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenacaoPessoas {
    private List<Pessoa> pessoaList;

    public OrdenacaoPessoas() {
        this.pessoaList = new ArrayList<>();
    }

    public void adicionarPessoa(String nome, int idade, double altura){
        pessoaList.add(new Pessoa(nome, altura, idade));
    }

    public List<Pessoa> ordenarIdade(){
        List<Pessoa> pessoasIdade = new ArrayList<>(pessoaList);
        Collections.sort(pessoasIdade);
        return pessoasIdade;
    }

    public List<Pessoa> ordenarAltura(){
        List<Pessoa> pessoasAltura= new ArrayList<>(pessoaList);
        Collections.sort(pessoasAltura, new ComparatorAltura());

        return pessoasAltura;
    }

    public static void main(String[] args){
        OrdenacaoPessoas galera = new OrdenacaoPessoas();

        galera.adicionarPessoa("Alana", 22, 1.75);
        galera.adicionarPessoa("Valeria", 20, 1.70);
        galera.adicionarPessoa("Carlos", 25, 1.71);
        galera.adicionarPessoa("Ezequiel", 23, 1.63);
        galera.adicionarPessoa("Gabriel", 29, 1.83);

        System.out.println(galera.ordenarIdade());
        System.out.println(galera.ordenarAltura());
    }
}
