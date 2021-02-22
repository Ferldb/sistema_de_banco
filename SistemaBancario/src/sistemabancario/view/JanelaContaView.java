package sistemabancario.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sistemabancario.controller.ContaController;

public class JanelaContaView extends javax.swing.JFrame {

    public JanelaContaView() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ComboBoxTipoConta = new javax.swing.JComboBox<>();
        campoCPF = new javax.swing.JTextField();
        labelCPF = new javax.swing.JLabel();
        bPesquisar = new javax.swing.JButton();
        labelTipoConta = new javax.swing.JLabel();
        painelConta = new javax.swing.JPanel();
        painelContaCorrente1 = new sistemabancario.view.PainelContaCorrente();
        painelContaInvestimento1 = new sistemabancario.view.PainelContaInvestimento();
        bTipoConta = new javax.swing.JButton();
        labelResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ComboBoxTipoConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conta Corrente", "Conta Investimento" }));
        ComboBoxTipoConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxTipoContaActionPerformed(evt);
            }
        });

        labelCPF.setText("CPF");

        bPesquisar.setText("Pesquisar");

        labelTipoConta.setText("Tipo de Conta");

        javax.swing.GroupLayout painelContaLayout = new javax.swing.GroupLayout(painelConta);
        painelConta.setLayout(painelContaLayout);
        painelContaLayout.setHorizontalGroup(
            painelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelContaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(painelContaCorrente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelContaInvestimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelContaLayout.setVerticalGroup(
            painelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelContaCorrente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(painelContaInvestimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bTipoConta.setText("Carregar Dados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTipoConta)
                                .addGap(18, 18, 18)
                                .addComponent(ComboBoxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(bTipoConta))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCPF)
                                .addGap(18, 18, 18)
                                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(bPesquisar))
                            .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelConta, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCPF)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoConta)
                    .addComponent(bTipoConta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxTipoContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxTipoContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxTipoContaActionPerformed

    public void setController(ContaController controller) {
         this.bPesquisar.addActionListener(e -> controller.buscarCliente());
         this.bTipoConta.addActionListener((ActionEvent e) -> {
         int indice = ComboBoxTipoConta.getSelectedIndex();
      
         if (indice==0){ painelContaCorrente1.setVisible(true); painelContaInvestimento1.setVisible(false);}
         else {painelContaCorrente1.setVisible(false); painelContaInvestimento1.setVisible(true);}
         painelConta.repaint();//Recarrega a página
        
         });
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxTipoConta;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JButton bTipoConta;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelResultado;
    private javax.swing.JLabel labelTipoConta;
    private javax.swing.JPanel painelConta;
    private sistemabancario.view.PainelContaCorrente painelContaCorrente1;
    private sistemabancario.view.PainelContaInvestimento painelContaInvestimento1;
    // End of variables declaration//GEN-END:variables

    public void initView() {
        java.awt.EventQueue.invokeLater(() ->  this.setVisible(true));
         painelContaCorrente1.setVisible(false); painelContaInvestimento1.setVisible(false);
    }
    
    public String getCPF(){
        String cpf = campoCPF.getText();
        return cpf;
    }
    
    public void apresentaErro(String erro) {
        JOptionPane.showMessageDialog(null,erro + "\n", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void resultadoCpf(String texto) {
      labelResultado.setText(texto);
    }
}
