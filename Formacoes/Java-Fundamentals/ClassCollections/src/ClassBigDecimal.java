import java.math.BigDecimal;

public class ClassBigDecimal {
    public void ex1(){

        BigDecimal preco = new BigDecimal("10.50");
        BigDecimal quantidade = new BigDecimal("3");
        BigDecimal total = preco.multiply(quantidade); // 31.50 exato
    }
}
