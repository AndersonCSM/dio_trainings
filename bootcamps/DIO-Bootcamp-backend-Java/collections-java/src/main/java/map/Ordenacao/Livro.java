package main.java.map.Ordenacao;

import java.util.Comparator;
import java.util.Map;

public class Livro{
    private String titulo;
    private String autor;
    private double preco;

    public Livro(String titulo, String autor, double preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", preco=" + preco +
                "}"+"\n";
    }

}

class ComparatorPreco implements Comparator<Map.Entry<String, Livro>> {
  @Override
  public int compare(Map.Entry<String, Livro> l1, Map.Entry<String, Livro> l2) {
    return Double.compare(l1.getValue().getPreco(), l2.getValue().getPreco());
  }
}

class ComparatorAutor implements Comparator<Map.Entry<String, Livro>> {
  @Override
  public int compare(Map.Entry<String, Livro> l1, Map.Entry<String, Livro> l2) {
    return l1.getValue().getAutor().compareToIgnoreCase(l2.getValue().getAutor());
  }
}
