public class ClassArray {

    public void exe1(){
        int[] notas = {7, 8, 9};
        System.out.println(notas[1]); // 8

    }

    public void exe2(){
        int[] notas = {7, 8, 9, 10};
        int soma = 0;
        for (int nota : notas){
            soma += nota;
        }

        double media = (double) soma / notas.length;
        System.out.println("Média: "+media);
    }
}
