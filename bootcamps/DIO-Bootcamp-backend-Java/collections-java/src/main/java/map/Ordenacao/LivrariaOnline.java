package main.java.map.Ordenacao;

import java.util.*;

public class LivrariaOnline {
    public Map<String, Livro> livros;

    public LivrariaOnline() {
        this.livros = new HashMap<>();
    }

    public void addLivro(String link, Livro l1){
        livros.put(link, l1);
    }

    public void removerUmLivro(String titulo){
        if (!livros.isEmpty()){
            for (Map.Entry<String, Livro> entry: livros.entrySet()){
                if (entry.getValue().getTitulo().equalsIgnoreCase(titulo)){
                    livros.remove(entry.getKey());
                    break;
                }
            }
        }
    }

    public void removerLivros(String titulo) {
    List<String> chavesRemover = new ArrayList<>();
    for (Map.Entry<String, Livro> entry : livros.entrySet()) {
      if (entry.getValue().getTitulo().equalsIgnoreCase(titulo)) {
        chavesRemover.add(entry.getKey());
      }
    }
    for (String chave : chavesRemover) {
      livros.remove(chave);
    }
  }

  public Map<String, Livro> livrosOrdenadosPreco(){
        List<Map.Entry<String, Livro>> livrosOrdenados = new ArrayList<>(livros.entrySet());

        livrosOrdenados.sort(new ComparatorPreco());

        Map<String, Livro> livrosFinal = new LinkedHashMap<>();

        for (Map.Entry<String, Livro> entry: livrosOrdenados){
            livrosFinal.put(entry.getKey(), entry.getValue());
        }


        return livrosFinal;
  }

  public Map<String, Livro> livrosOrdenadosAutor() {
    List<Map.Entry<String, Livro>> livrosOrdenados = new ArrayList<>(livros.entrySet());

    livrosOrdenados.sort(new ComparatorAutor());

    Map<String, Livro> livrosFinal = new LinkedHashMap<>();

    for (Map.Entry<String, Livro> entry : livrosOrdenados) {
      livrosFinal.put(entry.getKey(), entry.getValue());
    }

    return livrosFinal;
  }

  public Map<String, Livro> pesquisarPorAutor(String autor) {
    Map<String, Livro> livrosPorAutor = new LinkedHashMap<>();

    for (Map.Entry<String, Livro> entry : livros.entrySet()) {
      Livro livro = entry.getValue();
      if (livro.getAutor().equals(autor)) {
        livrosPorAutor.put(entry.getKey(), livro);
      }
    }

    return livrosPorAutor;
  }

  public List<Livro> obterLivroMaisCaro() {
    List<Livro> livrosMaisCaros = new ArrayList<>();
    double precoMaisAlto = Double.MIN_VALUE;
    Livro l1 = null;

    if (!livros.isEmpty()) {
      for (Map.Entry<String, Livro> entry: livros.entrySet()) {
        if (entry.getValue().getPreco() > precoMaisAlto){
          precoMaisAlto = entry.getValue().getPreco();
          l1 = entry.getValue();
        }
      }
      livrosMaisCaros.add(l1);
    } else {
      throw new NoSuchElementException("A livraria está vazia!");
    }
    return livrosMaisCaros;
  }

  public List<Livro> obterLivroMaisBarato() {
    List<Livro> livrosMaisBarato = new ArrayList<>();
    double precoMaisBarato = Double.MAX_VALUE;
    Livro l1 = null;

    if (!livros.isEmpty()) {
      for (Map.Entry<String, Livro> entry: livros.entrySet()) {
        if (entry.getValue().getPreco() < precoMaisBarato){
          precoMaisBarato = entry.getValue().getPreco();
          l1 = entry.getValue();

        }
      }
      livrosMaisBarato.add(l1);
    } else {
      throw new NoSuchElementException("A livraria está vazia!");
    }
    return livrosMaisBarato;
  }

  public static void main(String[] args) {
    LivrariaOnline livrariaOnline = new LivrariaOnline();
    // Adiciona os livros à livraria online
    livrariaOnline.addLivro("https://amzn.to/3EclT8Z", new Livro("1984", "George Orwell", 50d));
    livrariaOnline.addLivro("https://amzn.to/47Umiun", new Livro("A Revolução dos Bichos", "George Orwell", 7.05d));
    livrariaOnline.addLivro("https://amzn.to/3L1FFI6", new Livro("Caixa de Pássaros - Bird Box: Não Abra os Olhos", "Josh Malerman", 19.99d));
    livrariaOnline.addLivro("https://amzn.to/3OYb9jk", new Livro("Malorie", "Josh Malerman", 5d));
    livrariaOnline.addLivro("https://amzn.to/45HQE1L", new Livro("E Não Sobrou Nenhum", "Agatha Christie", 50d));
    livrariaOnline.addLivro("https://amzn.to/45u86q4", new Livro("Assassinato no Expresso do Oriente", "Agatha Christie", 5d));

    // Exibe todos os livros ordenados por preço
    System.out.println("Livros ordenados por preço: \n" + livrariaOnline.livrosOrdenadosPreco());

    //Exibe todos os livros ordenados por autor
    System.out.println("Livros ordenados por autor: \n" + livrariaOnline.livrosOrdenadosAutor());

    // Pesquisa livros por autor
    String autorPesquisa = "Josh Malerman";
    livrariaOnline.pesquisarPorAutor(autorPesquisa);

    // Obtém e exibe o livro mais caro
    System.out.println("Livro mais caro: " + livrariaOnline.obterLivroMaisCaro());

    // Obtém e exibe o livro mais barato
    System.out.println("Livro mais barato: " + livrariaOnline.obterLivroMaisBarato());

    // Remover um livro pelo título
    livrariaOnline.removerUmLivro("1984");
    System.out.println(livrariaOnline.livros);

  }
}
