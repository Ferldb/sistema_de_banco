package sistemabancario.view;

import javax.swing.JTextField;
import sistemabancario.controller.ClienteController;

public class BotoesClienteView extends javax.swing.JPanel {

    public BotoesClienteView() {
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

        bListarClientes = new javax.swing.JButton();
        bOrdenarClientes = new javax.swing.JButton();
        bAlterarCliente = new javax.swing.JButton();
        bExcluirCliente = new javax.swing.JButton();
        campoCPF = new javax.swing.JTextField();
        labelCPF = new javax.swing.JLabel();
        campoListar = new javax.swing.JTextField();
        comboboxLista = new javax.swing.JComboBox<>();
        comboboxOrdenar = new javax.swing.JComboBox<>();

        bListarClientes.setText("Listar Clientes");

        bOrdenarClientes.setText("Ordenar Clientes");
        bOrdenarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOrdenarClientesActionPerformed(evt);
            }
        });

        bAlterarCliente.setText("Atualizar Cliente");

        bExcluirCliente.setText("Excluir Cliente");

        campoCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCPFActionPerformed(evt);
            }
        });

        labelCPF.setText("CPF");

        campoListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoListarActionPerformed(evt);
            }
        });

        comboboxLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos os clientes", "Nome (ou parte)", "Sobrenome (ou parte)", "RG", "CPF" }));

        comboboxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Sobrenome", "Salario" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCPF))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(campoListar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bAlterarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboboxOrdenar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboboxLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bExcluirCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bOrdenarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(bListarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bAlterarCliente)
                            .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCPF)
                            .addComponent(bExcluirCliente)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campoListar, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboboxLista)
                                .addComponent(bListarClientes)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboboxOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bOrdenarClientes))
                        .addGap(61, 61, 61)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void campoCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCPFActionPerformed

    private void campoListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoListarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoListarActionPerformed

    private void bOrdenarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOrdenarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bOrdenarClientesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterarCliente;
    private javax.swing.JButton bExcluirCliente;
    private javax.swing.JButton bListarClientes;
    private javax.swing.JButton bOrdenarClientes;
    private javax.swing.JTextField campoCPF;
    private javax.swing.JTextField campoListar;
    private javax.swing.JComboBox<String> comboboxLista;
    private javax.swing.JComboBox<String> comboboxOrdenar;
    private javax.swing.JLabel labelCPF;
    // End of variables declaration//GEN-END:variables

    public void setController(ClienteController controller) {
        this.bAlterarCliente.addActionListener(e -> {
            controller.buscarCliente();
            campoCPF.setText("");
        });
        this.bExcluirCliente.addActionListener(e -> {
            controller.excluirCliente();
            campoCPF.setText("");
        });
        this.bListarClientes.addActionListener(e -> {
            int index = comboboxLista.getSelectedIndex();
            controller.listarFiltro(index);
            this.campoListar.setText("");
            });
        this.bOrdenarClientes.addActionListener(e -> {
            int ordenar = comboboxOrdenar.getSelectedIndex();
            controller.ordenarClientes(ordenar);
        });
    }
    
    public JTextField getCampoCPF() {
        return campoCPF;
    }
    
    public JTextField getCampoListar() {
        return campoListar;
    }
}
