package RecRa2;
import javax.swing.*;

public class CasaAutomatizada {
    public static void main(String[] args) {
        // Iniciar a interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CasaAutomatizadaGUI().setVisible(true);
            }
        });
    }
}
