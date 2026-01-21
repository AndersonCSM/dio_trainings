public class Aluno {

    private String nome;
    private int matricula;

    public Aluno(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Aluno)) return false;

        Aluno outro = (Aluno) obj;
        return this.matricula == outro.matricula;
    }

    // hashCode
    @Override
    public int hashCode() {
        return Integer.hashCode(matricula);
    }

    // toString
    @Override
    public String toString() {
        return nome + " (" + matricula + ")";
    }
}
