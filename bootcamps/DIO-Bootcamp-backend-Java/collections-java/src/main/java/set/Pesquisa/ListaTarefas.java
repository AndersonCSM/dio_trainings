package main.java.set.Pesquisa;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.Set;

public class ListaTarefas {
    private Set<Tarefa> tarefas;

    public ListaTarefas() {
        this.tarefas = new HashSet<>();
    }

    public void addTarefa(String descricao){
        tarefas.add(new Tarefa(descricao));
    }

    public void removerTarefa(String descricao){
        Tarefa tarefaRemover = null;
        if (!tarefas.isEmpty()){
            for (Tarefa t: tarefas){
                if (t.getDescricao().equalsIgnoreCase(descricao)){
                    tarefaRemover = t;
                    break;
                }
            }
            tarefas.remove(tarefaRemover);
        }
    }

    public void exibirTarefas(){
        System.out.println(tarefas);
    }

    public int contarTarefas(){
        return tarefas.size();
    }

    public Set<Tarefa> getConcluidas(){
        Set<Tarefa> tarefasConcluidas = new HashSet<>();
        if (!tarefas.isEmpty()){
            for (Tarefa t: tarefas){
                if (t.isCheck()){
                    tarefasConcluidas.add(t);
                }
            }
        }
        return tarefasConcluidas;
    }

    public Set<Tarefa> getPendentes(){
        Set<Tarefa> tarefasConcluidas = new HashSet<>();
        if (!tarefas.isEmpty()){
            for (Tarefa t: tarefas){
                if (!t.isCheck()){
                    tarefasConcluidas.add(t);
                }
            }
        }
        return tarefasConcluidas;
    }

    public void marcarConcluida(String descricao){
        if (!tarefas.isEmpty()){
            for (Tarefa t: tarefas){
                if (t.getDescricao().equalsIgnoreCase(descricao)){
                    t.setCheck(true);
                }
            }
        }
    }

    public void marcarPendente(String descricao){
        if (!tarefas.isEmpty()){
            for (Tarefa t: tarefas){
                if (t.getDescricao().equalsIgnoreCase(descricao)){
                    t.setCheck(false);
                }
            }
        }
    }

    public void limparTarefas(){
        tarefas.clear();
    }

      public static void main(String[] args) {
    // Criando uma instância da classe ListaTarefas
    ListaTarefas listaTarefas = new ListaTarefas();

    // Adicionando tarefas à lista
    listaTarefas.addTarefa("Estudar Java");
    listaTarefas.addTarefa("Fazer exercícios físicos");
    listaTarefas.addTarefa("Organizar a mesa de trabalho");
    listaTarefas.addTarefa("Ler livro");
    listaTarefas.addTarefa("Preparar apresentação");

    // Exibindo as tarefas na lista
    listaTarefas.exibirTarefas();

    // Removendo uma tarefa
    listaTarefas.removerTarefa("Fazer exercícios físicos");
    listaTarefas.exibirTarefas();

    // Contando o número de tarefas na lista
    System.out.println("Total de tarefas na lista: " + listaTarefas.contarTarefas());

    // Obtendo tarefas pendentes
    System.out.println(listaTarefas.getPendentes());

    // Marcando tarefas como concluídas
    listaTarefas.marcarConcluida("Ler livro");
    listaTarefas.marcarPendente("Estudar Java");

    // Obtendo tarefas concluídas
    System.out.println(listaTarefas.getConcluidas());

    // Marcando tarefas como pendentes
    listaTarefas.marcarPendente("Estudar Java");
    listaTarefas.exibirTarefas();

    // Limpando a lista de tarefas
    listaTarefas.limparTarefas();
    listaTarefas.exibirTarefas();
  }

}
