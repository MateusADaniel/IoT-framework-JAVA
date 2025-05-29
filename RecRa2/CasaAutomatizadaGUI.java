package RecRa2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CasaAutomatizadaGUI extends JFrame {
    private JTextField ambienteField;
    private JTextField temperaturaField;
    private JTextField temperaturaMaxField;
    private JTextField temperaturaMinField;
    private JTextArea messageArea;
    private GerenciadorAmbiente gerenciador;
    CriarConfiguracao criarConfiguracao = new CriarConfiguracao();
    Configuracao configuracao;

    public CasaAutomatizadaGUI() {
        setTitle("Casa Automatizada");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gerenciador = new GerenciadorAmbiente();

        // Layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Configuração da fonte e cor
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(60, 63, 65);

        // Linha 1: Nome do Ambiente (label) e campo de texto
        JLabel ambienteLabel = new JLabel("Nome do Ambiente:");
        ambienteLabel.setFont(labelFont);
        ambienteLabel.setForeground(labelColor);
        gbc.gridx = 0; // coluna
        gbc.gridy = 0; // linha
        gbc.gridwidth = 2; // quantas colunas ocupa
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(ambienteLabel, gbc);

        ambienteField = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(ambienteField, gbc);

        // Linha 2: Temperatura Inicial (label) e campo de texto
        JLabel temperaturaLabel = new JLabel("Temperatura Inicial:");
        temperaturaLabel.setFont(labelFont);
        temperaturaLabel.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        add(temperaturaLabel, gbc);

        temperaturaField = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(temperaturaField, gbc);

        // Linha 3: Temperatura Maxima ar
        JLabel temperaturaMaxima = new JLabel("Temperatura Max/Min Alvo:");
        temperaturaMaxima.setFont(labelFont);
        temperaturaMaxima.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        add(temperaturaMaxima, gbc);

        temperaturaMaxField = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Ocupa uma coluna
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(temperaturaMaxField, gbc);

        temperaturaMinField = new JTextField();
        gbc.gridx = 3; // define a posição inicial na coluna
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Ocupa uma coluna
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(temperaturaMinField, gbc);

        // Linha 4: Botões
        JButton addButton = new JButton("Adicionar Par IoT");
        addButton.setFont(labelFont);
        addButton.setBackground(new Color(51, 153, 255));
        addButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addButton, gbc);

        JButton monitorButton = new JButton("Monitorar Ambiente");
        monitorButton.setFont(labelFont);
        monitorButton.setBackground(new Color(100, 204, 102));
        monitorButton.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(monitorButton, gbc);

        JButton salvarButton = new JButton("Salvar Pares");
        salvarButton.setFont(labelFont);
        salvarButton.setBackground(new Color(210, 104, 102));
        salvarButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(salvarButton, gbc);

        JButton carregarButton = new JButton("Carregar pares");
        carregarButton.setFont(labelFont);
        carregarButton.setBackground(new Color(110, 104, 202));
        carregarButton.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(carregarButton, gbc);

        // Área de mensagens (linha 5, ocupa toda a largura)
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding interno
        messageArea.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JScrollPane(messageArea), gbc);

        // Ações dos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarParIoT();
            }
        });

        monitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monitorarAmbiente();
            }
        });

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPares();
            }
        });

        carregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    carregarPares();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    messageArea.append("Erro ao carregar pares: " + ex.getMessage() + "\n");
                }
            }
        });

        // Adicionar barra de menu
        setJMenuBar(createMenuBar());
    }

    private void adicionarParIoT() {
        String ambiente = ambienteField.getText();
        String temperaturaStr = temperaturaField.getText();
        String temperaturaMaxStr = temperaturaMaxField.getText();
        String temperaturaMinStr = temperaturaMinField.getText();
        
        try {
            double temperatura = Double.parseDouble(temperaturaStr);
            double temperaturaMax = Double.parseDouble(temperaturaMaxStr);
            double temperaturaMin = Double.parseDouble(temperaturaMinStr);

            Par_IoT par = new Par_IoT(ambiente, new TermometroNextcon(temperatura), new ControleArCondicionadoAgratto(temperaturaMin, temperaturaMax, 10, 5));
            gerenciador.incluir(par);
            messageArea.append("Par IoT adicionado: " + ambiente + "\n");

        } catch (NumberFormatException e) {
            messageArea.append("Erro: Temperatura inválida\n");
        } catch (CasaAutomatizadaException e) {
            messageArea.append("Erro ao adicionar par IoT: " + e.getMessage() + "\n");
        }
    }

    private void monitorarAmbiente() {
        try {
            gerenciador.monitorar_ambiente();
            messageArea.append("Monitoramento concluído com sucesso\n");
        } catch (CasaAutomatizadaCriticalException e) {
            messageArea.append("Erro crítico durante a monitorização: " + e.getMessage() + "\n");
        }
    }

    public void carregarPares() throws IOException {
        configuracao = Configuracao.abrir("Configuracao.txt");
        try {
            Par_IoT par1 = new Par_IoT(configuracao.getAmbiente(), new TermometroNextcon(configuracao.getTemperatura()), new ControleArCondicionadoAgratto(configuracao.getTemperaturaMin(), configuracao.getTemperaturaMax(), 10, 5));
            gerenciador.incluir(par1);
            messageArea.append("Par " + configuracao.getAmbiente() + " carregado com sucesso\n");

            // Atualizar os campos da interface
            ambienteField.setText(configuracao.getAmbiente());
            temperaturaField.setText(String.valueOf(configuracao.getTemperatura()));
            temperaturaMaxField.setText(String.valueOf(configuracao.getTemperaturaMax()));
            temperaturaMinField.setText(String.valueOf(configuracao.getTemperaturaMin()));

        } catch (CasaAutomatizadaException e) {
            messageArea.append("Erro ao adicionar par IoT: " + e.getMessage() + "\n");
        }
    }

    public void salvarPares() {
        String ambiente = ambienteField.getText();
        String temperaturaStr = temperaturaField.getText();
        String temperaturaMaxStr = temperaturaMaxField.getText();
        String temperaturaMinStr = temperaturaMinField.getText();

        try {
            double temperatura = Double.parseDouble(temperaturaStr);
            double temperaturaMax = Double.parseDouble(temperaturaMaxStr);
            double temperaturaMin = Double.parseDouble(temperaturaMinStr);

            configuracao = new Configuracao();
            configuracao.set_dados(ambiente, temperatura, temperaturaMin, temperaturaMax);
            configuracao.salvar("Configuracao.txt");
            messageArea.append("Configuração salva com sucesso\n");
        } catch (NumberFormatException e) {
            messageArea.append("Erro: Temperatura inválida\n");
        } catch (IOException e) {
            messageArea.append("Erro ao salvar configuração: " + e.getMessage() + "\n");
        }
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem exitMenuItem = new JMenuItem("Sair");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CasaAutomatizadaGUI().setVisible(true);
            }
        });
    }
}
