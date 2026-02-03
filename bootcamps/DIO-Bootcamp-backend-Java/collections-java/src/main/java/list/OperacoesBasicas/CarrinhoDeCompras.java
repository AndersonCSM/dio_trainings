package main.java.list.OperacoesBasicas;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    private List<Item> itensList;

    public CarrinhoDeCompras(){
        this.itensList = new ArrayList<>();
    }

    public void adicionarItem(String nome, double preco, int quantidade){
        itensList.add(new Item(nome, preco, quantidade));
    }

    public void removerItem(String nome){
        // remover elemento pela descrição
        // usando o for each para remover os elementos repetidos dentro da lista
        List<Item> itensRemover = new ArrayList<>();
        for (Item i: itensList) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                itensRemover.add(i);
            }
        }
        itensList.removeAll(itensRemover);
    }

    public double calcularValorTotal(){
        double total = 0;
        for (Item i: itensList){
            total += i.getPreco() * i.getQuantidade();
        }
        return total;
    }

    public int obterTotalItens(){return itensList.size();}

    public void exibirItens(){
        System.out.println(itensList);
    }

    public static void main(String[] args){
        CarrinhoDeCompras lista = new CarrinhoDeCompras();

        lista.adicionarItem("pirulito pop", 2.00, 2);
        lista.adicionarItem("caderno", 18.00, 1);
        lista.adicionarItem("caneta bic preta", 2.00, 5);
        lista.adicionarItem("água", 2.00, 2);
        lista.adicionarItem("salgadinho", 3.00, 1);

        lista.removerItem("pirulito pop");

        System.out.println("Total do valor de compra: R$ "+lista.calcularValorTotal());

        lista.exibirItens();
    }
}
