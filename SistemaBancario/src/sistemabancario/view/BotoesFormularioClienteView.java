package sistemabancario.view;

import sistemabancario.controller.ClienteController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Franshinsca
 */
public class BotoesFormularioClienteView extends javax.swing.JPanel {

    /**
     * Creates new form BotoesFormularioClienteView
     */
    public BotoesFormularioClienteView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bInserir = new javax.swing.JButton();
        bSalvar = new javax.swing.JButton();

        bInserir.setText("Inserir Cliente");

        bSalvar.setText("Salvar Alterações");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(bInserir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bSalvar)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bInserir)
                    .addComponent(bSalvar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bInserir;
    private javax.swing.JButton bSalvar;
    // End of variables declaration//GEN-END:variables

    void setController(ClienteController controller) {
        this.bInserir.addActionListener(e -> controller.inserirCliente());
        this.bSalvar.addActionListener(e -> controller.atualizarCliente());
    }
    
    void initBotoes(int i){
        if (i == 0){
            bInserir.setVisible(true);
            bSalvar.setVisible(false);
        }
        else{
            bInserir.setVisible(false);
            bSalvar.setVisible(true);
        }
    }
}