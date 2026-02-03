package main.java.set.OperacoesBasicas;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ConjuntoPalavras {
    public Set<String> palavras;

    public ConjuntoPalavras() {
        this.palavras = new HashSet<>();
    }
    public void addPalavra(String palavra){
        palavras.add(palavra.toLowerCase());
    }
    public void removerPalavra(String palavra){
        Set<String> palavrasRemovida = palavras;

        if (!palavras.isEmpty()){
            if (palavras.contains(palavra.toLowerCase())) {
                palavrasRemovida.remove(palavra.toLowerCase());
            }
        }
        else {
            System.out.println("Conjunto vazio");
        }
        palavras = palavrasRemovida;

    }
    public void verificarPalavra(String palavra){
        if (!palavras.isEmpty()) {
            if (palavras.contains(palavra.toLowerCase())) {
                System.out.println("Está presente");
            } else {
                System.out.println("Não está presente");
            }
        }
        else {
            System.out.println("Conjunto vazio");
        }
    }

    public void exibirPalavrasUnicas(){
        System.out.println(palavras);
    }

    public static void main(String[] args){
        ConjuntoPalavras alfabeto = new ConjuntoPalavras();

        alfabeto.addPalavra("a");
        alfabeto.addPalavra("A");
        alfabeto.addPalavra("B");
        alfabeto.addPalavra("c");
        alfabeto.addPalavra("d");
        alfabeto.addPalavra("HGF");

        alfabeto.exibirPalavrasUnicas();
        alfabeto.removerPalavra("hgf");
        alfabeto.verificarPalavra("b");
        alfabeto.exibirPalavrasUnicas();
    }
}
