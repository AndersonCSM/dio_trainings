package main.java.map.OperacoesBasicas;

import java.util.HashMap;
import java.util.Map;

public class Dicionario {
    private Map<String, String> dicionario;

    public Dicionario() {
        this.dicionario = new HashMap<>();
    }

    public void addPalavra(String palavra, String descricao){
        dicionario.put(palavra, descricao);
    }

    public void removerPalavra(String palavra){
        if (!dicionario.isEmpty()){
            dicionario.remove(palavra);
        }
    }

    public void exibirPalavras(){
        System.out.println(dicionario);
    }

    public void pesquisarPalavra(String palavra){
        System.out.println(dicionario.get(palavra));
    }
    
    public static  void main(String[] args){
        Dicionario dicio = new Dicionario();

        dicio.addPalavra("Eu", "Uma pessoa");
        dicio.addPalavra("Abacaxi", "Fruta");
        dicio.addPalavra("Casa", "Abrigo");
        dicio.addPalavra("Carro", "Veiculo");
        dicio.exibirPalavras();
        dicio.removerPalavra("Eu");
        dicio.exibirPalavras();
        dicio.pesquisarPalavra("Carro");

    }
    
}
