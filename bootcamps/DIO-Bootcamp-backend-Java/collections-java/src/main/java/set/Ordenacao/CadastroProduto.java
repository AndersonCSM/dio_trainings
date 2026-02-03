package main.java.set.Ordenacao;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CadastroProduto {
    private Set<Produto> produtos;

    public CadastroProduto() {
        this.produtos = new HashSet<>();
    }

    public void addProduto(long cod, String nome, double preco, int quantidade){
        produtos.add(new Produto(nome,preco, cod, quantidade));
    }

    public Set<Produto> exibirProdutosNome(){
        return new TreeSet<>(produtos);
    }

    public Set<Produto> exibirProdutoPreco(){
        Set<Produto> produtosPreco =  new TreeSet<>(new ComparatorPorPreco());
        produtosPreco.addAll(produtos);
        return produtosPreco;
    }

    public static void main(String[] args){
        CadastroProduto carrinho = new CadastroProduto();

        carrinho.addProduto(23L, "Produto 4", 15.0, 1);
        carrinho.addProduto(1L, "Produto 2", 2.50, 1);
        carrinho.addProduto(2L, "Produto 1", 23.0, 1);
        carrinho.addProduto(2L, "Produto 3", 20.0, 1);

        System.out.println(carrinho.exibirProdutosNome());

        System.out.println(carrinho.exibirProdutoPreco());

    }
}
