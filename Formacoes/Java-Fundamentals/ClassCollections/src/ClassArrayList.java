import java.util.ArrayList;

public class ClassArrayList {

    public void ex1(){
        ArrayList<String> alunos = new ArrayList<>();
        alunos.add("Ana");
        alunos.add("Bruno");

        System.out.println(alunos.get(0));

    }

    public void ex2(){
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            numeros.add(i);
        }

        numeros.removeIf(n -> n % 2 == 0); // remove pares
        System.out.println(numeros);

    }
}
