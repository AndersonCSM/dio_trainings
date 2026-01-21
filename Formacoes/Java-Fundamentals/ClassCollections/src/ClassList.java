import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassList {

    public void ex1(){
        List<String> cidades = new ArrayList<>();
        cidades.add("Natal");
        cidades.add("Mossoró");

    }

    public void ex2(){
        List<String> nomes = new ArrayList<>();
        nomes.add("Carlos");
        nomes.add("Ana");
        nomes.add("Bruno");

        Collections.sort(nomes);
        System.out.println(nomes);

    }
}
