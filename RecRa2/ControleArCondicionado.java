package RecRa2;

public abstract class ControleArCondicionado implements Atuador{
    protected double temperaturaMinimaAlvo;
    protected double temperaturaMaximaAlvo;
    protected int potenciaMaximaMotor;
    protected int potenciaAtualMotor;

    public ControleArCondicionado(double temperaturaMinimaAlvo, double temperaturaMaximaAlvo, int potenciaMaximaMotor, int potenciaAtualMotor) {
        this.temperaturaMinimaAlvo = temperaturaMinimaAlvo;
        this.temperaturaMaximaAlvo = temperaturaMaximaAlvo;
        this.potenciaMaximaMotor = potenciaMaximaMotor;
        this.potenciaAtualMotor = potenciaAtualMotor;
    }

}
