import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class ClassMap {

    public void ex1(){
        // Mapeando Matrícula (String) -> Nome do Aluno (String)
        Map<String, String> diarioDeClasse = new HashMap<>();

        diarioDeClasse.put("2024001", "Ana Silva");
        diarioDeClasse.put("2024002", "Bruno Souza");
        diarioDeClasse.put("2024001", "Ana Maria"); // Sobrescreve "Ana Silva"

        System.out.println(diarioDeClasse.get("2024001")); // Saída: Ana Maria
    }

    public void ex2() {
        // 1. HashMap: O mais rápido, mas bagunçado (sem ordem garantida)
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(500, "Erro Interno");
        hashMap.put(404, "Não Encontrado");
        hashMap.put(200, "OK");
        hashMap.put(403, "Proibido");

        System.out.println("--- HashMap (Ordem aleatória/hash) ---");
        // A saída provavelmente não será a ordem de inserção, nem numérica
        hashMap.forEach((k, v) -> System.out.println(k + " : " + v));

        // 2. LinkedHashMap: Respeita a fila (Ordem de inserção)
        Map<Integer, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(500, "Erro Interno");
        linkedMap.put(404, "Não Encontrado");
        linkedMap.put(200, "OK");

        System.out.println("\n--- LinkedHashMap (Ordem de inserção) ---");
        // Garante a saída: 500, depois 404, depois 200
        linkedMap.forEach((k, v) -> System.out.println(k + " : " + v));

        // 3. TreeMap: O organizado (Ordem natural da chave)
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(500, "Erro Interno");
        treeMap.put(404, "Não Encontrado");
        treeMap.put(200, "OK");
        treeMap.put(403, "Proibido");

        System.out.println("\n--- TreeMap (Ordenado pela Chave) ---");
        // Garante a saída crescente: 200, 403, 404, 500
        treeMap.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
