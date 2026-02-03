package main.java.map.Pesquisa;

import java.util.HashMap;
import java.util.Map;

public class EstoqueProdutos {
    private Map<Long, Produto> estoque;

    public EstoqueProdutos() {
        this.estoque = new HashMap<>();
    }

    public void addProduto(long cod, String nome, double preco, int quantidade){
        estoque.put(cod, new Produto(nome, preco, quantidade));
    }

    public void exibirProdutos(){
        System.out.println(estoque);
    }

    public double calcularEstoque(){
        double total = 0;
        if (!estoque.isEmpty()){
            for (Produto p: estoque.values()){
                total += p.getPreco() * p.getQuantidade();
            }
        }
        return total;
    }

    public Produto produtoCaro(){
        Produto maisCaro = null;
        double maiorPreco = Double.MIN_VALUE;
        if (!estoque.isEmpty()){
            for (Produto p: estoque.values()){
                if (p.getPreco() > maiorPreco){
                    maiorPreco = p.getPreco();
                    maisCaro = p;
                }
            }
        }
        return maisCaro;
    }

    public static void main(String[] args){
        EstoqueProdutos estoqueT = new EstoqueProdutos();

        estoqueT.addProduto(1L, "Produto 1", 2.50, 2);
        estoqueT.addProduto(2L, "Produto 2", 1.00, 2);
        estoqueT.addProduto(3L, "Produto 3", 2.00, 2);
        estoqueT.addProduto(4L, "Produto 4", 4.00, 1);

        estoqueT.exibirProdutos();

        System.out.println(estoqueT.calcularEstoque());
        System.out.println(estoqueT.produtoCaro());

    }
}
