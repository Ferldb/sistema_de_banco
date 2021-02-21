package sistemabancario.view;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;
import sistemabancario.controller.ClienteController;
import sistemabancario.model.Cliente;

public class JanelaClienteView extends javax.swing.JFrame {
    
    private int linhaClicadaParaAtualizacao = -1;
    private ClienteTableModel modeloTabelaCliente = new ClienteTableModel();

    public JanelaClienteView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabelaClienteView = new sistemabancario.view.TabelaClienteView();
        botoesClienteView = new sistemabancario.view.BotoesClienteView();
        formularioClienteView = new sistemabancario.view.FormularioClienteView();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelaClienteView, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                    .addComponent(botoesClienteView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(formularioClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabelaClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botoesClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formularioClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    public void setController(ClienteController controller) {
        botoesClienteView.setController(controller);
        formularioClienteView.setController(controller);
        tabelaClienteView.getTabelaCliente().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Pega a linha clicada
                linhaClicadaParaAtualizacao = tabelaClienteView.getTabelaCliente().rowAtPoint(evt.getPoint());
                //Pega o contato da linha clicada
                Cliente cliente = modeloTabelaCliente.getCliente(linhaClicadaParaAtualizacao);
                //Seta os dados nos componentes
                formularioClienteView.setCliente(cliente);
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sistemabancario.view.BotoesClienteView botoesClienteView;
    private sistemabancario.view.FormularioClienteView formularioClienteView;
    private sistemabancario.view.TabelaClienteView tabelaClienteView;
    // End of variables declaration//GEN-END:variables

    public void initView() {
        tabelaClienteView.getTabelaCliente().setModel(modeloTabelaCliente);
        java.awt.EventQueue.invokeLater(() ->  this.setVisible(true));
    }

    public Cliente getCliente() {
        String nome = formularioClienteView.getCampoNome().getText();
        String sobrenome = formularioClienteView.getCampoSobrenome().getText();
        String rg = formularioClienteView.getCampoRG().getText();
        String cpf = formularioClienteView.getCampoCPF().getText();
        String endereco = formularioClienteView.getCampoEndereco().getText();
        String salario = formularioClienteView.getCampoSalario().getText();
        
        Double s = Double.parseDouble(salario);
        
        Cliente cliente = new Cliente(-1, nome, sobrenome, rg, cpf, endereco, s);
        return cliente;
    }
    
    public String getCPF(){
        String cpf = botoesClienteView.getCampoCPF().getText();
        return cpf;
    }

    public void inserirCliente(Cliente cliente) {
        modeloTabelaCliente.adicionaCliente(cliente);
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null,mensagem + "\n", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarListaContatos(List<Cliente> lista) {
        modeloTabelaCliente.setListaClientes(lista);
    }

    public void apresentaErro(String erro) {
        JOptionPane.showMessageDialog(null,erro + "\n", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void preencherFormulario(Cliente cliente) {
        formularioClienteView.setCliente(cliente);
    }

    public void atualizarCliente(Cliente cliente) {
        modeloTabelaCliente.fireTableRowsUpdated(linhaClicadaParaAtualizacao, linhaClicadaParaAtualizacao);
    }
}
