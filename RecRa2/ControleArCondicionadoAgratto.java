package RecRa2;

public class ControleArCondicionadoAgratto extends ControleArCondicionado{

    public ControleArCondicionadoAgratto(double temperaturaMinimaAlvo, double temperaturaMaximaAlvo, int potenciaMaximaMotor, int potenciaAtualMotor) {
        super(temperaturaMinimaAlvo, temperaturaMaximaAlvo, potenciaMaximaMotor, potenciaAtualMotor);
    }

    public void aplicar(double temperatura) {
        if (temperatura < temperaturaMinimaAlvo && potenciaAtualMotor > 0) {
            potenciaAtualMotor--;
            
        } else if (temperatura > temperaturaMaximaAlvo && potenciaAtualMotor < potenciaMaximaMotor) {
            potenciaAtualMotor++;
        }
        System.out.println("Potencia = " + potenciaAtualMotor);

    }
}
