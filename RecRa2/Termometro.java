package RecRa2;

public abstract class Termometro implements Sensor {
    protected double temperaturaInicial;

    public Termometro(double temperaturaInicial) {
        this.temperaturaInicial = temperaturaInicial;
    }

}
