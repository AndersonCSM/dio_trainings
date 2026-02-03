package main.java.list.Pesquisa;

import java.util.ArrayList;
import java.util.List;

public class CatalogoLivros {
    private List<Livro> livroList;

    public CatalogoLivros(){
    this.livroList = new ArrayList<>();
    }

    public void adicionarLivro(String titulo, String autor, int ano){
        livroList.add(new Livro(titulo, autor, ano));
    }

    public List<Livro> pesquisarAutor(String autor){
        List<Livro> livrosAutor = new ArrayList<>();

        if (!livroList.isEmpty()){
            for(Livro l: livroList){
                if (l.getAutor().equalsIgnoreCase(autor)) {
                    livrosAutor.add(l);
                }
            }
        }

        return livrosAutor;
    }

    public List<Livro> pesquisarIntervaloAno(int inicio, int fim){
        List<Livro> livrosIntervalo = new ArrayList<>();

        if (!livroList.isEmpty()){
            for(Livro l: livroList){
                if (inicio <=l.getAnoPublicacao() && l.getAnoPublicacao() <= fim) {
                    livrosIntervalo.add(l);
                }
            }
        }

        return livrosIntervalo;
    }

    public Livro pesquisarTitulo(String titulo){
        Livro livroTitulo = null;
        if (!livroList.isEmpty()){
            for(Livro l: livroList){
                if (l.getTitulo().equalsIgnoreCase(titulo)) {
                    livroTitulo = l;
                    break;
                }
            }
        }

        return livroTitulo;
    }

    public static void main(String[] args){
        CatalogoLivros livros = new CatalogoLivros();

        livros.adicionarLivro("Livro 1", "Autor 1", 2020);
        livros.adicionarLivro("Livro 1", "Autor 2", 2021);
        livros.adicionarLivro("Livro 2", "Autor 2", 2021);
        livros.adicionarLivro("Livro 3", "Autor 3", 2023);
        livros.adicionarLivro("Livro 4", "Autor 4", 2024);
        livros.adicionarLivro("Livro 5", "Autor 5", 2025);
        livros.adicionarLivro("Livro 6", "Autor 6", 2026);
        livros.adicionarLivro("Livro 7", "Autor 6", 1992);

        System.out.println(livros.pesquisarAutor("Autor 2"));
        System.out.println(livros.pesquisarIntervaloAno(2022, 2026));
        System.out.println(livros.pesquisarTitulo("Livro 1"));
    }


}
