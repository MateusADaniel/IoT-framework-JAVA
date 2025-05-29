package RecRa2;
import java.util.Random;

public class TermometroNextcon extends Termometro {
    private Random random;

    public TermometroNextcon(double temperaturaInicial) {
        super(temperaturaInicial);
        this.random = new Random();
    }

    public double ler_dado() {
        double variacao = -2 + (2 - (-2)) * random.nextDouble();
        temperaturaInicial += variacao;
        return temperaturaInicial;
    }
}
