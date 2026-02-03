package main.java.map.Pesquisa;

import java.util.HashMap;
import java.util.Map;

public class ContagemPalavras {
    private Map<String, Integer> palavras;

    public ContagemPalavras() {
        this.palavras = new HashMap<>();
    }

    public void addPalavra(String palavra, int contagem){
        palavras.put(palavra, contagem);
    }

    public void removerPalavra(String palavra){
        if (palavras.get(palavra) != null){
            palavras.remove(palavra);
        }
    }

    public int exibirContagem(){
        int contagem = 0;
        for (Integer c: palavras.values()){
            contagem += c;
        }
        return contagem;
    }

    public String maisFrequente(){
        String frequente = null;
        int maiorContagem = 0;
        for (Map.Entry<String, Integer> entry: palavras.entrySet()){
            if (entry.getValue() > maiorContagem){
                frequente = entry.getKey();
                maiorContagem = entry.getValue();
            }
        }
        return frequente;
    }

    public static void main(String[] args) {
    ContagemPalavras contagemLinguagens = new ContagemPalavras();

    // Adiciona linguagens e suas contagens
    contagemLinguagens.addPalavra("Java", 2);
    contagemLinguagens.addPalavra("Python", 8);
    contagemLinguagens.addPalavra("JavaScript", 1);
    contagemLinguagens.addPalavra("C#", 6);

    // Exibe a contagem total de linguagens
    System.out.println("Existem " + contagemLinguagens.exibirContagem() + " palavras.");

    // Encontra e exibe a linguagem mais frequente
    String linguagemMaisFrequente = contagemLinguagens.maisFrequente();
    System.out.println("A linguagem mais frequente é: " + linguagemMaisFrequente);
  }
}
