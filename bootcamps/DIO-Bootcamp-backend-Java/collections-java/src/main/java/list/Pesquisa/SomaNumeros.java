package main.java.list.Pesquisa;

import java.util.ArrayList;
import java.util.List;

public class SomaNumeros {
    private List<Integer> numeros = new ArrayList<>();

    public SomaNumeros() {
        this.numeros = new ArrayList<>();
    }

    public void adicionarNumero(int numero){
        numeros.add(numero);
    }

    public double calcularSoma(){
        double soma = 0;
        if (!numeros.isEmpty()){
            for (Integer n: numeros){
                soma += n;
            }
        }

        return soma;
    }

    public int encontrarMaiorNumero(){
        int maiorNumero = Integer.MIN_VALUE;
        if (!numeros.isEmpty()){
            for (Integer n: numeros){
                if (n > maiorNumero){
                    maiorNumero = n;
                }
            }
            return maiorNumero;
        }
        else {
            throw new RuntimeException("A lista está vazia!");
        }
    }

        public int encontrarMenorNumero(){
        int menorNumero = Integer.MAX_VALUE;
        if (!numeros.isEmpty()){
            for (Integer n: numeros){
                if (n < menorNumero){
                    menorNumero = n;
                }
            }
            return menorNumero;
        }
        else {
            throw new RuntimeException("A lista está vazia!");
        }
    }

    public void exibirNumeros(){
        if (!numeros.isEmpty()){
            System.out.println(numeros);
        }
        else {
            System.out.println("Lista vazia");
        }
    }

    public static void main(String[] args){
        SomaNumeros teste = new SomaNumeros();

        teste.adicionarNumero(11);
        teste.adicionarNumero(10);
        teste.adicionarNumero(-2);
        for (int i=1; i < 10; i++){
            teste.adicionarNumero(i);
        }

        teste.exibirNumeros();
        System.out.println(teste.encontrarMaiorNumero());
        System.out.println(teste.encontrarMenorNumero());
        System.out.println(teste.calcularSoma());


    }
}
