package sistemabancario.view;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import sistemabancario.controller.ClienteContaController;

public class JanelaManipularContaView extends javax.swing.JFrame {

    public JanelaManipularContaView() {
        initComponents();
    }

    public void setController(ClienteContaController controller) {
         this.bVoltar.addActionListener( e -> controller.visibilidade());
         this.bPesquisar.addActionListener(e -> controller.buscarCliente());
         this.bCarregarOperacao.addActionListener((ActionEvent e) -> {
         int indice = ComboBoxOperacao.getSelectedIndex();
         painelContaCliente1.setVisible(true);
         painelContaCliente1.setLabels(indice);
        });
    }

    public void initView() {
        java.awt.EventQueue.invokeLater(() ->  this.setVisible(true));
        painelContaCliente1.setVisible(false);
    }
    
    public String getCPF(){
        String cpf = campoCPF.getText();
        return cpf;
    }
    
    public void resultadoCpf(String texto) {
      labelResultado.setText(texto);
    }
    
    public void apresentaErro(String erro) {
        JOptionPane.showMessageDialog(null,erro + "\n", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public void desabilitaMenu(ClienteContaController aThis) {
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bVoltar = new javax.swing.JButton();
        bPesquisar = new javax.swing.JButton();
        campoCPF = new javax.swing.JTextField();
        labelCPF = new javax.swing.JLabel();
        labelOperacao = new javax.swing.JLabel();
        ComboBoxOperacao = new javax.swing.JComboBox<>();
        bCarregarOperacao = new javax.swing.JButton();
        labelResultado = new javax.swing.JLabel();
        painelContaCliente1 = new sistemabancario.view.PainelContaCliente();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bVoltar.setText("Voltar");

        bPesquisar.setText("Pesquisar");

        labelCPF.setText("CPF");

        labelOperacao.setText("Operação");

        ComboBoxOperacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saque", "Depósito", "Ver Saldo", "Remunera" }));

        bCarregarOperacao.setText("Carregar Dados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bVoltar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelOperacao)
                                        .addGap(18, 18, 18)
                                        .addComponent(ComboBoxOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelCPF)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(bCarregarOperacao))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(bPesquisar))))
                            .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelContaCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCPF)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOperacao)
                    .addComponent(bCarregarOperacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(painelContaCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bVoltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxOperacao;
    private javax.swing.JButton bCarregarOperacao;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JButton bVoltar;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelOperacao;
    private javax.swing.JLabel labelResultado;
    private sistemabancario.view.PainelContaCliente painelContaCliente1;
    // End of variables declaration//GEN-END:variables
}
