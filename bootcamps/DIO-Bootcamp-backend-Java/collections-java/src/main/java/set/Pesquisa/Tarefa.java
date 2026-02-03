package main.java.set.Pesquisa;

public class Tarefa {
    private String descricao;
    private boolean check;

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.check = false;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isCheck() {
        return check;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "descricao='" + descricao + '\'' +
                ", check=" + check +
                "}\n";
    }


}
