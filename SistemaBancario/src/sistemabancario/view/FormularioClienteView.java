package sistemabancario.view;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import sistemabancario.controller.ClienteController;
import sistemabancario.model.Cliente;

public class FormularioClienteView extends javax.swing.JPanel {

    public FormularioClienteView() {
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

        labelNome = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        labelSobrenome = new javax.swing.JLabel();
        campoSobrenome = new javax.swing.JTextField();
        labelRG = new javax.swing.JLabel();
        campoRG = new javax.swing.JTextField();
        labelCPF = new javax.swing.JLabel();
        campoCPF = new javax.swing.JTextField();
        labelEndereco = new javax.swing.JLabel();
        campoEndereco = new javax.swing.JTextField();
        labelSalario = new javax.swing.JLabel();
        campoSalario = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(591, 236));

        labelNome.setText("Nome");

        labelSobrenome.setText("Sobrenome");

        labelRG.setText("RG");

        labelCPF.setText("CPF");

        labelEndereco.setText("Endereco");

        labelSalario.setText("Salario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSobrenome)
                    .addComponent(labelNome)
                    .addComponent(labelRG)
                    .addComponent(labelCPF)
                    .addComponent(labelEndereco)
                    .addComponent(labelSalario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(campoEndereco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(campoCPF, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoRG, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoSobrenome, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoSalario)
                    .addComponent(campoNome, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSobrenome)
                    .addComponent(campoSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRG)
                    .addComponent(campoRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCPF)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEndereco)
                    .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSalario)
                    .addComponent(campoSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoCPF;
    private javax.swing.JTextField campoEndereco;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoRG;
    private javax.swing.JTextField campoSalario;
    private javax.swing.JTextField campoSobrenome;
    private javax.swing.JLabel labelCPF;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelRG;
    private javax.swing.JLabel labelSalario;
    private javax.swing.JLabel labelSobrenome;
    // End of variables declaration//GEN-END:variables
    
    public JTextField getCampoNome() {
        return campoNome;
    }
    
    public JTextField getCampoSobrenome() {
        return campoSobrenome;
    }
    
    public JTextField getCampoRG() {
        return campoRG;
    }
    
    public JTextField getCampoCPF() {
        return campoCPF;
    }
    
    public JTextField getCampoEndereco() {
        return campoEndereco;
    }
    
    public JTextField getCampoSalario() {
        return campoSalario;
    }
    
    public void setCampoNome(){
        campoNome.setText("");
    }
    
    //seta dados do cliente nos campos do formulário
    void setCliente(Cliente cliente) {
        campoNome.setText(cliente.getNome());
        campoSobrenome.setText(cliente.getSobrenome());
        campoRG.setText(cliente.getRg());
        campoCPF.setText(cliente.getCpf());
        campoCPF.setEditable(false);
        campoEndereco.setText(cliente.getEndereco());
        campoSalario.setText(Double.toString(cliente.getSalario()));
    }
    
    //limpa formulário
    void limparFormulario() {
        campoNome.setText("");
        campoSobrenome.setText("");
        campoRG.setText("");
        campoCPF.setText("");
        campoCPF.setEditable(true);
        campoEndereco.setText("");
        campoSalario.setText("");
    }
}
