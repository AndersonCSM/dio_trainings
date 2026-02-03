// Classe ContaBancaria.java
public class ContaBancaria {
    private String titular;
    private double saldo;
    
    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void sacar(double valor) {
        saldo -= valor;
        System.out.println("Saque realizado com sucesso!");
    }
}

// Subclasses das contas
class ContaCorrente extends ContaBancaria {
    public ContaCorrente(String titular, double saldo) {
        super(titular, saldo);
    }
}

class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(String titular, double saldo) {
        super(titular, saldo);
    }
}

// Aspecto para verificação de saldo
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class VerificacaoSaldoAspect {
    @Around("execution(* ContaBancaria.sacar(..)) && args(valor)")
    public void verificarSaldo(ProceedingJoinPoint joinPoint, double valor) throws Throwable {
        ContaBancaria conta = (ContaBancaria) joinPoint.getTarget();
        if (conta.getSaldo() < valor) {
            System.out.println("Erro: Saldo insuficiente!");
        } else {
            joinPoint.proceed();
        }
    }
}

// Classe Main.java para testar
public class Main {
    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaCorrente("João", 500);
        ContaBancaria conta2 = new ContaPoupanca("Maria", 200);
        
        conta1.sacar(100); // Deve permitir o saque
        conta2.sacar(300); // Deve exibir erro de saldo insuficiente
    }
}
