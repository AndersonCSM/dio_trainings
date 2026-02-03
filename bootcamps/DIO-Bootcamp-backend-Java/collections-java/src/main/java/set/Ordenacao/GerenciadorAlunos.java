package main.java.set.Ordenacao;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class GerenciadorAlunos {
    private Set<Aluno> alunos;

    public GerenciadorAlunos() {
        this.alunos = new HashSet<>();
    }

    public void addAluno(String nome, long matricula, double media){
        alunos.add(new Aluno(nome, matricula, media));
    }

    public void removerAluno(long matricula){
        Aluno alunoRemover = null;
        if (!alunos.isEmpty()) {
            for (Aluno a: alunos){
                if (a.getMatricula() == matricula){
                    alunoRemover = a;
                    break;
                }
            }
            alunos.remove(alunoRemover);
        }
    }

    public void exibirAlunos(){
        System.out.println(alunos);
    }

    public void exibirPorNome(){
        Set<Aluno> ordenados =  new TreeSet<>(alunos);
        System.out.println(ordenados);
    }

    public void exibirPorNota(){
        Set<Aluno> ordenados = new TreeSet<>(new ComparatorPorMedia());
        ordenados.addAll(alunos);
        System.out.println(ordenados);
    }

    public static void main(String[] args) {
    // Criando uma instância do GerenciadorAlunos
    GerenciadorAlunos gerenciadorAlunos = new GerenciadorAlunos();

    // Adicionando alunos ao gerenciador
    gerenciadorAlunos.addAluno("João", 123456L, 7.5);
    gerenciadorAlunos.addAluno("Maria", 123457L, 9.0);
    gerenciadorAlunos.addAluno("Carlos", 123458L, 5.0);
    gerenciadorAlunos.addAluno("Ana", 123459L, 6.8);

    // Exibindo todos os alunos no gerenciador
    System.out.println("Alunos no gerenciador:");
    gerenciadorAlunos.exibirAlunos();

    // Removendo um aluno com matrícula inválida e outro pelo número de matrícula
    gerenciadorAlunos.removerAluno(000L);
    gerenciadorAlunos.removerAluno(123457L);
    gerenciadorAlunos.exibirAlunos();

    System.out.println("Por nome");
    // Exibindo alunos ordenados por nome
    gerenciadorAlunos.exibirPorNome();

    System.out.println("Por nota");
    // Exibindo alunos ordenados por nota
    gerenciadorAlunos.exibirPorNota();
  }
}
