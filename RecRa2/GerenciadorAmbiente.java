package RecRa2;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class GerenciadorAmbiente {
    private ArrayList<Par_IoT> pares_IoT = new ArrayList<Par_IoT>();

    public void incluir(Par_IoT par) throws CasaAutomatizadaException{ 
        if (par == null) {
            throw new CasaAutomatizadaException("Par IoT inválido.");
        }
        pares_IoT.add(par); 
    }

    public void monitorar_ambiente() throws CasaAutomatizadaCriticalException{

        while (true) {
            System.out.println("\n[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "]:");
            for (Par_IoT par: pares_IoT)
            {
                if (Math.random() < 0.9) { // 3% de chance de erro
                    throw new CasaAutomatizadaCriticalException("Erro crítico ao monitorar o ambiente: " + par.ID);
                }
    
                double dado = par.sensor.ler_dado();
                //int valor = par.atuador.aplicar(dado);
                System.out.print(String.format("(%20s) Dado lido: %.2f ==>", par.ID, dado));
                par.atuador.aplicar(dado);
            }
            try { Thread.sleep(1000); } catch (Exception e) {}
        }
    }
}
