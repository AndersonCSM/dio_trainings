public class ClassWrapper {

    public void ex1(){
        // Autoboxing: o int 10 vira new Integer(10) automaticamente
        Integer idade = 10;

        // Unboxing: o Integer é convertido para int para a soma
        int anoQueVem = idade + 1;
    }

    public void ex2(){
        String idadeTexto = "25";
        String pesoTexto = "70.5";
        String ativoTexto = "true";

        // 1. Conversão (Parsing) - O uso mais comum
        int idade = Integer.parseInt(idadeTexto);
        double peso = Double.parseDouble(pesoTexto);
        boolean isAtivo = true;

        // 2. Constantes Úteis (Limites do sistema)
        System.out.println("Máximo Inteiro possível: " + Integer.MAX_VALUE);
        System.out.println("Mínimo Inteiro possível: " + Integer.MIN_VALUE);

        // 3. Métodos estáticos auxiliares
        int valor1 = 10;
        int valor2 = 20;

        // Retorna o maior entre os dois sem precisar de if/else
        int maximo = Integer.max(valor1, valor2);
        // Converte para binário (muito usado em redes/engenharia)
        String binario = Integer.toBinaryString(valor1);

        System.out.println("Maior: " + maximo);
        System.out.println("10 em binário: " + binario);

        // 4. Verificação de caractere (Character Wrapper)
        char letra = '9';
        if (Character.isDigit(letra)) {
            System.out.println("É um número!");
        }
    }
}
