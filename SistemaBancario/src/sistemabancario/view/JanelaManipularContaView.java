package sistemabancario.view;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sistemabancario.controller.ManipularController;

public class JanelaManipularContaView extends javax.swing.JFrame {

    public JanelaManipularContaView() {
        initComponents();
    }

    public void setController(ManipularController controller) {

        this.bVoltar.addActionListener(e -> controller.visibilidade());
        this.bPesquisar.addActionListener(e -> controller.buscarCliente());
        this.bCarregar.addActionListener((ActionEvent e) -> {
            
            if("".equals(this.labelResultado.getText())){
                this.mostrarMensagem("Selecione primeiro um cliente...");
            }
            else{
                int x = controller.verificarCliente();
                if (x == 0) {
                    int indice = ComboBoxOperacao.getSelectedIndex();
                    if(indice == 2 || indice == 3){
                        bConfirmar.setVisible(false);
                        campoValor.setVisible(true);
                        campoValor.setEditable(false);               
                        controller.movimentarConta(indice);
                        lOperacao.setVisible(true);
                    }
                    else{
                        lOperacao.setVisible(true);
                        if(indice == 0)
                            lOperacao.setText("SAQUE: ");
                        if(indice == 1)
                            lOperacao.setText("DEPÓSITO: ");
                        campoValor.setText("");
                        campoValor.setEditable(true);
                        campoValor.setVisible(true);
                        bConfirmar.setVisible(true);
                    }
                }
                else {
                    if(bConfirmar.isVisible())
                        bConfirmar.setVisible(false);
                    if(campoValor.isVisible())
                        campoValor.setVisible(false);
                    if(lOperacao.isVisible())
                        lOperacao.setVisible(false);
                }
            }
        });
        this.bConfirmar.addActionListener((ActionEvent e) -> {
            campoValor.setEditable(true);
            int indice = ComboBoxOperacao.getSelectedIndex();
            if(bConfirmar.isVisible())
                bConfirmar.setVisible(false);
            if(campoValor.isVisible())
                campoValor.setVisible(false);
            if(lOperacao.isVisible())
                lOperacao.setVisible(false);
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
    
    public JTextField getCampoValor() {
        return campoValor;
    }

    public JButton getbConfirmar() {
        return bConfirmar;
    }

    public JLabel getlOperacao() {
        return lOperacao;
    }
    
    public void resultadoCpf(String texto) {
        labelResultado.setText(texto);
    }

    public void mostrarMensagem(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem + "\n", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void apresentaErro(String erro) {
        JOptionPane.showMessageDialog(this, erro + "\n", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void desabilitaMenu(ManipularController aThis) {
        this.dispose();
    }

    public void mostrarSaldo(double saldo) {
        lOperacao.setVisible(true);
        campoValor.setVisible(true);
        lOperacao.setText("SALDO: ");
        campoValor.setEditable(false);
        String s = String.format("%.2f", saldo);
        campoValor.setText("R$ " + s);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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
        lOperacao = new javax.swing.JLabel();
        bConfirmar = new javax.swing.JButton();
        labelCliente = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        bConfirmar.setText("Confirmar");

        labelCliente.setText("Cliente:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bConfirmar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelOperacao)
                                                .addGap(18, 18, 18)
                                                .addComponent(ComboBoxOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelCPF)
                                                .addGap(24, 24, 24)
                                                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(bCarregar))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(bPesquisar))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelCliente)
                                        .addGap(3, 3, 3)
                                        .addComponent(labelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bVoltar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCPF)
                            .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPesquisar))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBoxOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOperacao)
                            .addComponent(bCarregar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bConfirmar)
                        .addGap(22, 22, 22)))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lOperacao;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelOperacao;
    private javax.swing.JLabel labelResultado;
    // End of variables declaration//GEN-END:variables
}
