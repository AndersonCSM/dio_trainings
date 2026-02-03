package main.java.list.Ordenacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenacaoNumeros {
    private List<Integer> numeros = new ArrayList<>();

    public OrdenacaoNumeros() {
        this.numeros = new ArrayList<>();
    }

    public void adicionarNumero(int numero){
        numeros.add(numero);
    }

    public List<Integer>  ordenarAscendente(){
        List<Integer> numerosAsc = new ArrayList<>(numeros);
        if (!numeros.isEmpty()){
            Collections.sort(numerosAsc);
            return numerosAsc;
        }
        else {
            throw  new RuntimeException("Lista vazia!");
        }

    }

    public List<Integer>  ordenarDescendente(){
        List<Integer> numerosDesc = new ArrayList<>(numeros);
        if (!numeros.isEmpty()){
            numerosDesc.sort(Collections.reverseOrder());
            return numerosDesc;
        }
        else {
            throw  new RuntimeException("Lista vazia!");
        }
    }

    public void exibirNumeros(){
        System.out.println(this.numeros);
    }

    public static void main(String[] args){
        // Criando uma instância da classe OrdenacaoNumeros
        OrdenacaoNumeros numeros = new OrdenacaoNumeros();

        // Adicionando números à lista
        numeros.adicionarNumero(2);
        numeros.adicionarNumero(5);
        numeros.adicionarNumero(4);
        numeros.adicionarNumero(1);
        numeros.adicionarNumero(99);

        // Exibindo a lista de números adicionados
        numeros.exibirNumeros();

        // Ordenando e exibindo em ordem ascendente
        System.out.println(numeros.ordenarAscendente());

        // Exibindo a lista
        //numeros.exibirNumeros();

        // Ordenando e exibindo em ordem descendente
        System.out.println(numeros.ordenarDescendente());

        // Exibindo a lista
        //numeros.exibirNumeros();
    }
}
