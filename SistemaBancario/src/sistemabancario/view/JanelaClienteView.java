package sistemabancario.view;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;
import sistemabancario.controller.ClienteController;
import sistemabancario.controller.MenuController;
import sistemabancario.model.Cliente;

public class JanelaClienteView extends javax.swing.JFrame {

    private ClienteTableModel modeloTabelaCliente = new ClienteTableModel();

    public JanelaClienteView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabelaClienteView = new sistemabancario.view.TabelaClienteView();
        botoesClienteView = new sistemabancario.view.BotoesClienteView();
        botoesFormularioClienteView = new sistemabancario.view.BotoesFormularioClienteView();
        formularioClienteView = new sistemabancario.view.FormularioClienteView();
        bVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(500, 600));
        setSize(new java.awt.Dimension(100, 500));

        bVoltar.setText("Voltar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabelaClienteView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 116, Short.MAX_VALUE)
                        .addComponent(formularioClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(botoesClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bVoltar)
                        .addGap(170, 170, 170)
                        .addComponent(botoesFormularioClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabelaClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botoesClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formularioClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botoesFormularioClienteView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bVoltar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   //seta botoes da janela com ações e seta tabela de clientes
    public void setController(ClienteController controller) {
        this.bVoltar.addActionListener( e -> controller.visibilidade());    
        botoesClienteView.setController(controller);
        botoesFormularioClienteView.setController(controller);
        tabelaClienteView.getTabelaCliente();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bVoltar;
    private sistemabancario.view.BotoesClienteView botoesClienteView;
    private sistemabancario.view.BotoesFormularioClienteView botoesFormularioClienteView;
    private sistemabancario.view.FormularioClienteView formularioClienteView;
    private sistemabancario.view.TabelaClienteView tabelaClienteView;
    // End of variables declaration//GEN-END:variables
    
    //inicializa view
    public void initView() {
        tabelaClienteView.getTabelaCliente().setModel(modeloTabelaCliente); //seta modelo da tabela
        java.awt.EventQueue.invokeLater(() ->  this.setVisible(true));      //torna visível
        this.initBotoes(0);                                                 //inicializa botoes rodapé
    }
    
    //instancia novo cliente a partir do formulário preenchido
    public Cliente getCliente() {
        try {
            String nome = formularioClienteView.getCampoNome().getText();
            String sobrenome = formularioClienteView.getCampoSobrenome().getText();
            String rg = formularioClienteView.getCampoRG().getText();
            String cpf = formularioClienteView.getCampoCPF().getText();
            String endereco = formularioClienteView.getCampoEndereco().getText();
            String salario = formularioClienteView.getCampoSalario().getText();
            
            if ("".equals(nome) || "".equals(sobrenome) || "".equals(rg) || "".equals(cpf) || "".equals(endereco) || "".equals(salario)){
                throw new RuntimeException("\nCampo obrigatorio em branco...");
            }
            Double s;
            try{
                s = Double.parseDouble(salario);
            }catch(Exception e){
                throw new RuntimeException("\nValor de SALÁRIO inválido...");
            }

            Cliente cliente = new Cliente(-1, nome, sobrenome, rg, cpf, endereco, s);
            return cliente;
        }
        finally{
        }
    }
    
    //pega cpf digitado no campo de busca
    public String getCPF(){
        String cpf = botoesClienteView.getCampoCPF().getText();
        return cpf;
    }
    
    //pega conteúdo inserido no campo listar
    public String getCampoListar(){
        String listar = botoesClienteView.getCampoListar().getText();
        return listar;
    }
    
    //insere novo cliente na tabela
    public void inserirCliente(Cliente cliente) {
        modeloTabelaCliente.adicionaCliente(cliente);
    }
    
    //apresenta popuo com mensagem passada por parametro
    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem + "\n", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //exibe lista de clientes na tabela
    public void mostrarListaClientes(List<Cliente> lista) {
        modeloTabelaCliente.setListaClientes(lista);
    }
    
    //apresenta popup de erro com mensagem passada por parametro
    public void apresentaErro(String erro) {
        JOptionPane.showMessageDialog(this, erro + "\n", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    //preenche formulário com dados do cliente passado por parametro
    public void preencherFormulario(Cliente cliente) {
        formularioClienteView.setCliente(cliente);
    }
    
    //limpa formulário
    public void limparFormulario(){
        formularioClienteView.limparFormulario();
    }
    
    //define visibilidade dos botoes do formulário a partir de indice passado por parametro
    public void initBotoes(int i){
        botoesFormularioClienteView.initBotoes(i);
    }
    
    //destroi instancia
    public void desabilitaCliente(ClienteController controller) {
        this.dispose();
    }
    
    //exibe janela de confirmação de exclusão de cliente
    public int confirmacao(Cliente cliente) {
        return JOptionPane.showConfirmDialog(this, "ATENÇÃO!\nTodas as contas do cliente " + cliente.getNome() + " " + cliente.getSobrenome() + " também serão excluídas.\nDeseja prosseguir?", null, JOptionPane.YES_NO_OPTION);
    }
}
