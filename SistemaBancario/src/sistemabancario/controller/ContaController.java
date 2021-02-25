package sistemabancario.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import sistemabancario.model.Cliente;
import sistemabancario.model.Conta;
import sistemabancario.model.ContaCorrente;
import sistemabancario.model.ContaInvestimento;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.view.JanelaContaView;
import sistemabancario.view.JanelaMenuView;
import sistemabancario.view.PainelContaCorrente;
import sistemabancario.view.PainelContaInvestimento;

public class ContaController {
    private JanelaContaView contaView;
    private ClienteDao clienteDao;
    private ContaDao contaDao;
    
    public ContaController(){
        this.contaView = new JanelaContaView();
        this.clienteDao = new ClienteDao(new ConnectionFactory());
        this.contaDao = new ContaDao(new ConnectionFactory());
        initController();
    }
    
    private void initController(){
        this.contaView.setController(this);
        this.contaView.initView();
    }
 
    //procura cliente por CPF e exibe nome na lacuna
    public void buscarCliente(){
        try{
            String cpf = contaView.getCPF();
            if("".equals(cpf)){
                String s = "";
                contaView.resultadoCpf(s);
                contaView.mostrarMensagem("Digite um CPF para busca...");
            }
            else {
                Cliente cliente = clienteDao.getCliente(cpf);
                String nome = cliente.getNome() + " " + cliente.getSobrenome();
                if (nome != null){
                    contaView.resultadoCpf(nome);
                    if(contaView.getPainelContaCorrente().isVisible())
                        contaView.getPainelContaCorrente().setVisible(false);
                    else if(contaView.getPainelContaInvestimento().isVisible())
                        contaView.getPainelContaInvestimento().setVisible(false);
                }
                else
                    throw new RuntimeException();
                contaView.repaint();
            }
        }
        catch(Exception e){
            String s = "";
            contaView.resultadoCpf(s);
            contaView.apresentaErro("Erro ao buscar cliente! " + e.getMessage());
        }
    }
    
    //verifica se o cliente buscado já possui conta vinculada ou não
    public int verificarCliente(){
        try{
            String cpf = contaView.getCPF();
            Cliente cliente = clienteDao.getCliente(cpf);
            contaDao.procuraCliente(cliente.getId());
            return 0;
        }
        catch(Exception e){
            contaView.apresentaErro("Não é possível criar conta! " + e.getMessage());
        }
        return 1;
    }

    public void visibilidade() {
        this.contaView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
    
    //tipo = 1 conta corrente  -  tipo = 2 conta investimento
    public void criarConta(int tipo) {
        try{
            String cpf = contaView.getCPF();
            Cliente cliente = clienteDao.getCliente(cpf);

            int x = contaDao.procuraCliente(cliente.getId());        //procura cliente na tabela de contas
            if (x == 0){
                //significa que cliente ainda não pussui conta
                if (tipo == 1){
                    ContaCorrente cc = contaView.getContaCorrente();   //pega os dados inseridos
                    cc.setDono(cliente);                            //seta id do cliente
                    Double d = contaView.getDepositoCC();              //pega saldo inicial
                    cc.setSaldo(cc.getSaldo() + d);                    //seta saldo
                    contaDao.inserirCC(cc);
                    contaView.limparFormulario(tipo);
                    contaView.mostrarMensagem("CONTA CORRENTE criada com sucesso!\nCliente: " + cliente.getNome() + " " + cliente.getSobrenome() + "\nNº CC: " + cc.getNumero());

                }
                else if (tipo == 2){
                    ContaInvestimento ci = contaView.getContaInvestimento(); //pega os dados inseridos
                    ci.setDono(cliente);                                  //seta id cliente
                    Double d = contaView.getDepositoCI();                    //pega saldo inicial
                    if(d < ci.getMontanteMin())
                        throw new RuntimeException("\nDEPÓSITO INICIAL não pode ser menor do que o MONTANTE MÍNIMO da conta...");
                    else if(d < ci.getDepositoMin())
                        throw new RuntimeException("\nDEPÓSITO INICIAL não pode ser menor do que o DEPÓSITO MÍNIMO da conta...");
                    else {
                        ci.setSaldo(ci.getSaldo() + d);                          //seta saldo
                        contaDao.inserirCI(ci);
                        contaView.limparFormulario(tipo);
                        contaView.mostrarMensagem("CONTA INVESTIMENTO criada com sucesso!\nCliente: " + cliente.getNome() + " " + cliente.getSobrenome() + "\nNº CI: " + ci.getNumero());
                    }
                }
            }
        } catch(Exception e){
            contaView.apresentaErro("Não foi possível criar conta! " + e.getMessage());
        }
    }
}