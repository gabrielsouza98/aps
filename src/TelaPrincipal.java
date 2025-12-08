import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaPrincipal extends JFrame {

    private JTextField campoNomeCliente;
    private JComboBox<String> campoTipoPeca;
    private JComboBox<String> campoServico;
    private DefaultListModel<String> listaPecasModel;
    private JList<String> listaPecasUI;
    private JButton botaoAdicionar, botaoCalcular;

    public TelaPrincipal() {

        setTitle("Lavanderia Automatizada - Sistema");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10));

        painel.add(new JLabel("Nome do Cliente:"));
        campoNomeCliente = new JTextField();
        painel.add(campoNomeCliente);

        painel.add(new JLabel("Tipo da Peça:"));
        campoTipoPeca = new JComboBox<>(new String[] {
            "Camisa", "Calça", "Toalha", "Roupa Delicada"
        });
        painel.add(campoTipoPeca);

        painel.add(new JLabel("Serviço:"));
        campoServico = new JComboBox<>(new String[] {
            "Lavar", "Secar", "Passar", "Pacote Completo"
        });
        painel.add(campoServico);

        add(painel, BorderLayout.NORTH);

        listaPecasModel = new DefaultListModel<>();
        listaPecasUI = new JList<>(listaPecasModel);
        add(new JScrollPane(listaPecasUI), BorderLayout.CENTER);

        botaoAdicionar = new JButton("Adicionar Peça");
        botaoCalcular = new JButton("Calcular Total");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoCalcular);

        add(painelBotoes, BorderLayout.SOUTH);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tipo = campoTipoPeca.getSelectedItem().toString();
                String servico = campoServico.getSelectedItem().toString();

                listaPecasModel.addElement(tipo + " - Serviço: " + servico);
            }
        });

        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int quantidade = listaPecasModel.size();
                double total = 0;

               
                for (int i = 0; i < quantidade; i++) {
                    String item = listaPecasModel.get(i);

                    if (item.contains("Pacote Completo"))
                        total += 20;
                    else
                        total += 10;
                }

                JOptionPane.showMessageDialog(null,
                    "Cliente: " + campoNomeCliente.getText() +
                    "\nTotal de peças: " + quantidade +
                    "\nValor total: R$ " + total);
            }
        });
    }
}
