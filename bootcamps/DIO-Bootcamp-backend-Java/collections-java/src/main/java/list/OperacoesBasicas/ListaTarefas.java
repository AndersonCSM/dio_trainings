package main.java.list.OperacoesBasicas;


import java.util.ArrayList;
import java.util.List;

public class ListaTarefas {
    private List<Tarefa> tarefasList;

    public ListaTarefas() {
        this.tarefasList = new ArrayList<>();
    }

    public void adicionarTarefa(String descricao){
        tarefasList.add(new Tarefa(descricao));
    }

    public void removerTarefa(String descricao){
        // remover elemento pela descrição
        // usando o for each para remover os elementos repetidos dentro da lista
        List<Tarefa> tarefasParaRemover = new ArrayList<>();
        for (Tarefa t: tarefasList) {
            if (t.getDescricao().equalsIgnoreCase(descricao)) {
                tarefasParaRemover.add(t);
            }
        }
        tarefasList.removeAll(tarefasParaRemover);
    }

    public int obterNumeroTotalTarefas(){
        return tarefasList.size();
    }

    public void obterDescricoesTarefas(){
        System.out.println(tarefasList);
    }

    public static void  main(String[] args) {
        ListaTarefas lista = new ListaTarefas();
        System.out.println("Número de tarefas da lista: "+lista.obterNumeroTotalTarefas());

        lista.adicionarTarefa("Tarefa 1");
        lista.adicionarTarefa("Tarefa 1");
        lista.adicionarTarefa("Tarefa 2");
        System.out.println("Número de tarefas da lista: "+lista.obterNumeroTotalTarefas());

        lista.removerTarefa("Tarefa 1");
        System.out.println("Número de tarefas da lista: "+lista.obterNumeroTotalTarefas());

        lista.obterDescricoesTarefas();
    }
}
