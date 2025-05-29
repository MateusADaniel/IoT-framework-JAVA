package RecRa2;

import java.io.*;

public class CriarConfiguracao {
    public void Start(){
        Configuracao configuracao = new Configuracao();

        try {
            configuracao.salvar("Configuracao.ser");
            System.out.println("Configuracao criada e salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Excecao de I/O");
            e.printStackTrace();
        }
    }

}