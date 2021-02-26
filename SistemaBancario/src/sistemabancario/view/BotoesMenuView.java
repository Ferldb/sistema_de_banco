package sistemabancario.view;

import sistemabancario.controller.ManipularController;
import sistemabancario.controller.ClienteController;
import sistemabancario.controller.ContaController;
import sistemabancario.controller.MenuController;

public class BotoesMenuView extends javax.swing.JPanel {

    public BotoesMenuView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bClientes = new javax.swing.JButton();
        bConta = new javax.swing.JButton();
        bMovimentar = new javax.swing.JButton();

        bClientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bClientes.setText("Clientes");
        bClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClientesActionPerformed(evt);
            }
        });

        bConta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bConta.setText("Criar Conta");
        bConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bContaActionPerformed(evt);
            }
        });

        bMovimentar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bMovimentar.setText("Movimentar Conta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bMovimentar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bConta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(bClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bConta)
                .addGap(18, 18, 18)
                .addComponent(bMovimentar)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bClientesActionPerformed

    private void bContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bContaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClientes;
    private javax.swing.JButton bConta;
    private javax.swing.JButton bMovimentar;
    // End of variables declaration//GEN-END:variables
    
    //seta botoes e suas ações - instancia novo controller de acordo com cada botão e troca visibilidade
    public void setController(MenuController controller) {
      
        this.bClientes.addActionListener(e -> {
            controller.visibilidade();
            new ClienteController();
                });
        this.bConta.addActionListener(e -> {
            controller.visibilidade();
            new ContaController();
                });
        this.bMovimentar.addActionListener(e -> {
            controller.visibilidade();
            new ManipularController();
                });
    }
}
