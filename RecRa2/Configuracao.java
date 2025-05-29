package RecRa2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuracao {
    private String ambiente;
    private double temperatura;
    private double temperaturaMin;
    private double temperaturaMax;

    public void set_dados(String ambiente, double temperatura, double temperaturaMin, double temperaturaMax) {
        this.ambiente = ambiente;
        this.temperatura = temperatura;
        this.temperaturaMin = temperaturaMin;
        this.temperaturaMax = temperaturaMax;
    }

    public void salvar(String nome_arquivo) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nome_arquivo))) {
            writer.write(ambiente + "," + temperatura + "," + temperaturaMin + "," + temperaturaMax);
        }
    }

    public static Configuracao abrir(String nome_arquivo) throws IOException {
        Configuracao configuracao = new Configuracao();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(nome_arquivo))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                configuracao.set_dados(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
            }
        }
        return configuracao;
    }

    // Métodos getter para acessar os dados
    public String getAmbiente() {
        return ambiente;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getTemperaturaMin() {
        return temperaturaMin;
    }

    public double getTemperaturaMax() {
        return temperaturaMax;
    }
}
