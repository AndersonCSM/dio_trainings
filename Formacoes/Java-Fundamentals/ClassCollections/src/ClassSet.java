import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassSet {

    public void ex1(){
        Set<String> cores = new HashSet<>();
        cores.add("Azul");
        cores.add("Azul"); // ignorado

    }

    public void ex2(){
        Set<Aluno> alunos = new HashSet<>();

        alunos.add(new Aluno("Ana", 1));
        alunos.add(new Aluno("Bruno", 2));
        alunos.add(new Aluno("Ana", 1)); // duplicado lógico

        System.out.println(alunos);


    }

    public void ex3(){
        Set<Aluno> alunos = new HashSet<>();

        Aluno a1 = new Aluno("Carlos", 10);
        Aluno a2 = new Aluno("Daniela", 20);

        alunos.add(a1);
        alunos.add(a2);

        Aluno busca = new Aluno("Carlos", 10);

        System.out.println(alunos.contains(busca)); // true
        alunos.remove(busca);

        System.out.println(alunos);

    }
}
