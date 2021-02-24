package sistemabancario.view;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sistemabancario.controller.ClienteContaController;

public class JanelaManipularContaView extends javax.swing.JFrame {

    public JanelaManipularContaView() {
        initComponents();
    }

    public void setController(ClienteContaController controller) {

        this.bVoltar.addActionListener(e -> controller.visibilidade());
        this.bPesquisar.addActionListener(e -> controller.buscarCliente());
        this.bCarregar.addActionListener((ActionEvent e) -> {
            int indice = ComboBoxOperacao.getSelectedIndex();
            if(indice == 2 || indice == 3){
                campoValor.setEditable(false);
                
                controller.movimentarConta(indice);
            }
            else{
                campoValor.setText("");
                campoValor.setEditable(true);
                campoValor.setVisible(true);
                bConfirmar.setVisible(true);
            }
        });
        this.bConfirmar.addActionListener((ActionEvent e) -> {
            campoValor.setEditable(true);
            int indice = ComboBoxOperacao.getSelectedIndex();
            controller.movimentarConta(indice);
        });
    }

    public void initView() {
        java.awt.EventQueue.invokeLater(() -> this.setVisible(true));
        campoValor.setVisible(false);
        bConfirmar.setVisible(false);

    }

    public String getCPF() {
        String cpf = campoCPF.getText();
        return cpf;
    }

    public void MostraMensagem(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem + "\n", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public double getCampoValor() {
        return Double.parseDouble(campoValor.getText());
    }

    public void resultadoCpf(String texto) {
        labelResultado.setText(texto);
    }

    public void apresentaErro(String erro) {
        JOptionPane.showMessageDialog(null, erro + "\n", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void desabilitaMenu(ClienteContaController aThis) {
        this.dispose();
    }

    public void mostrarSaldo(double saldo) {
        lOperacao.setText("SALDO");
        campoValor.setEditable(false);
        campoValor.setText(Double.toString(saldo));
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
        bCarregar = new javax.swing.JButton();
        labelResultado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        campoValor = new javax.swing.JTextField();
        bConfirmar = new javax.swing.JButton();
        lOperacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bVoltar.setText("Voltar");

        bPesquisar.setText("Pesquisar");

        labelCPF.setText("CPF");

        labelOperacao.setText("Operação");

        ComboBoxOperacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saque", "Depósito", "Ver Saldo", "Remunera" }));

        bCarregar.setText("Carregar Dados");

        campoValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bConfirmar.setText("Confirmar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bConfirmar)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(bCarregar))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(bPesquisar))))
                                    .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bVoltar)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(bCarregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bConfirmar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 96, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bVoltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoValorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxOperacao;
    private javax.swing.JButton bCarregar;
    private javax.swing.JButton bConfirmar;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JButton bVoltar;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JTextField campoValor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lOperacao;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelOperacao;
    private javax.swing.JLabel labelResultado;
    // End of variables declaration//GEN-END:variables
}
