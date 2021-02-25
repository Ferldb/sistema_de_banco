package sistemabancario.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sistemabancario.controller.ContaController;
import sistemabancario.model.Cliente;
import sistemabancario.model.Conta;
import sistemabancario.model.ContaCorrente;
import sistemabancario.model.ContaInvestimento;

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
        painelContaCorrente = new sistemabancario.view.PainelContaCorrente();
        painelContaInvestimento = new sistemabancario.view.PainelContaInvestimento();
        bTipoConta = new javax.swing.JButton();
        labelResultado = new javax.swing.JLabel();
        bVoltar = new javax.swing.JButton();
        labelCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ComboBoxTipoConta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conta Corrente", "Conta Investimento" }));
        ComboBoxTipoConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxTipoContaActionPerformed(evt);
            }
        });

        labelCPF.setText("CPF");

        bPesquisar.setText("Pesquisar");
        bPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPesquisarActionPerformed(evt);
            }
        });

        labelTipoConta.setText("Tipo de Conta");

        javax.swing.GroupLayout painelContaLayout = new javax.swing.GroupLayout(painelConta);
        painelConta.setLayout(painelContaLayout);
        painelContaLayout.setHorizontalGroup(
            painelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelContaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(painelContaCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelContaInvestimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelContaLayout.setVerticalGroup(
            painelContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelContaCorrente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(painelContaInvestimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bTipoConta.setText("Carregar Dados");

        bVoltar.setText("Voltar");

        labelCliente.setText("Cliente:");

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCliente)
                                    .addComponent(labelCPF))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(bPesquisar))
                                    .addComponent(labelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelConta, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bVoltar)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoConta)
                    .addComponent(bTipoConta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(bVoltar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxTipoContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxTipoContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxTipoContaActionPerformed

    private void bPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bPesquisarActionPerformed

    public void setController(ContaController controller) {
        this.bVoltar.addActionListener(e -> controller.visibilidade());
        this.bPesquisar.addActionListener(e -> controller.buscarCliente());
        this.bTipoConta.addActionListener((ActionEvent e) -> {
            
            if("".equals(this.labelResultado.getText())){
                this.mostrarMensagem("Selecione primeiro um cliente...");
            }
            else{
                int x = controller.verificarCliente();
                if (x == 0) {
                    int indice = ComboBoxTipoConta.getSelectedIndex();
      
                    if (indice == 0){ //conta corrente
                        painelContaCorrente.setVisible(true);
                        painelContaInvestimento.setVisible(false);
                        painelContaCorrente.setController(controller);
                    }
                    else{           //conta investimento
                        painelContaCorrente.setVisible(false);
                        painelContaInvestimento.setVisible(true);
                        painelContaInvestimento.setController(controller);
                    }
                    painelConta.repaint();  //recarrega a página
                }
                else {
                    if(painelContaCorrente.isVisible())
                        painelContaCorrente.setVisible(false);
                    else if(painelContaInvestimento.isVisible())
                        painelContaInvestimento.setVisible(false);
                }
            }
        });
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxTipoConta;
    private javax.swing.JButton bPesquisar;
    private javax.swing.JButton bTipoConta;
    private javax.swing.JButton bVoltar;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelResultado;
    private javax.swing.JLabel labelTipoConta;
    private javax.swing.JPanel painelConta;
    private sistemabancario.view.PainelContaCorrente painelContaCorrente;
    private sistemabancario.view.PainelContaInvestimento painelContaInvestimento;
    // End of variables declaration//GEN-END:variables

    public void initView() {
        java.awt.EventQueue.invokeLater(() ->  this.setVisible(true));
         painelContaCorrente.setVisible(false); painelContaInvestimento.setVisible(false);
    }
    
    public String getCPF(){
        return campoCPF.getText();
    }

    public String getLabelResultado() {
        return labelResultado.getText();
    }

    public PainelContaCorrente getPainelContaCorrente() {
        return painelContaCorrente;
    }

    public PainelContaInvestimento getPainelContaInvestimento() {
        return painelContaInvestimento;
    }
    
    public void apresentaErro(String erro) {
        JOptionPane.showMessageDialog(this, erro + "\n", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem + "\n", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void resultadoCpf(String texto) {
        labelResultado.setText(texto);
    }

    public void desabilitaMenu(ContaController aThis) {
        this.dispose();
    }
    
    public ContaCorrente getContaCorrente(){
        Double l = 0.0;
        String limite = "";
        ContaCorrente conta = null;
        try{
            limite = painelContaCorrente.getCampoLimite().getText();
            l = Double.parseDouble(limite);
            if(l < 0)
                throw new RuntimeException();
            conta = new ContaCorrente(l, -1, null, 0);
        }catch(Exception e){
            if ("".equals(limite))
                throw new RuntimeException("\nCampo obrigatorio LIMITE em branco...");
            else
                throw new RuntimeException("\nValor de LIMITE inválido...");
        }
        return conta;
    }
    
    public ContaInvestimento getContaInvestimento(){
        Double m = 0.1, d = 0.1;
        String montante = "", deposito = "";
        ContaInvestimento conta = null;
        try{
            montante = painelContaInvestimento.getCampoMontanteMinimo().getText();
            m = Double.parseDouble(montante);
            if(m <= 0)
                throw new RuntimeException();
        }catch(Exception e){
            if ("".equals(montante))
                throw new RuntimeException("\nCampo obrigatorio MONTANTE MÍNIMO em branco...");
            else if(m <= 0)
                throw new RuntimeException("\nMONTANTE MÍNIMO deve ser maior que zero...");
            else
                throw new RuntimeException("\nValor de MONTANTE MÍNIMO inválido...");
        }
        
        try{
            deposito = painelContaInvestimento.getCampoDepositoMinimo().getText();
            d = Double.parseDouble(deposito);
            if(d <= 0)
                throw new RuntimeException();
            conta = new ContaInvestimento(-1, null, 0, m, d);
        }catch(Exception e){
            if ("".equals(deposito))
                throw new RuntimeException("\nCampo obrigatorio DEPÓSITO MÍNIMO em branco...");
            else if(d <= 0)
                throw new RuntimeException("\nDEPÓSITO MÍNIMO deve ser maior que zero...");
            else
                throw new RuntimeException("\nValor de DEPÓSITO MÍNIMO inválido...");
        }
        
        
        return conta;
    }
    
    public Double getDepositoCC(){
        Double d = 0.0;
        String deposito = "";
        try {
            deposito = painelContaCorrente.getCampoDepositoInicial().getText();
            d = Double.parseDouble(deposito);
            if(d < 0)
                throw new RuntimeException();
        }
        catch(Exception e){
            if ("".equals(deposito))
                throw new RuntimeException("\nCampo obrigatorio DEPÓSITO INICIAL em branco...");
            else if(d < 0)
                throw new RuntimeException("\nDEPÓSITO INICIAL não pode ser negativo...");
            else
                throw new RuntimeException("\nValor de DEPÓSITO INICIAL inválido...");
        }
        return d;
    }
    
    public Double getDepositoCI(){
        Double d = 0.0;
        String deposito = "";
        try {
            deposito = painelContaInvestimento.getCampoDepositoInicial().getText();
            d = Double.parseDouble(deposito);
            if(d < 0)
                throw new RuntimeException();
        }
        catch(Exception e){
            if ("".equals(deposito))
                throw new RuntimeException("\nCampo obrigatorio DEPÓSITO INICIAL em branco...");
            else if(d < 0)
                throw new RuntimeException("\nDEPÓSITO INICIAL não pode ser negativo...");
            else
                throw new RuntimeException("\nValor de DEPÓSITO INICIAL inválido...");
        }
        return d;
    }
    
    public void limparFormulario(int tipo){
        if (tipo == 1) painelContaCorrente.limparFormulario();
        else painelContaInvestimento.limparFormulario();
        campoCPF.setText("");
        labelResultado.setText("");
        painelContaCorrente.setVisible(false);
        painelContaInvestimento.setVisible(false);
    }
}
