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
    
    //inicializa e seta elementos da janela conta
    private void initController(){
        this.contaView.setController(this);
        this.contaView.initView();
    }
 
    //procura cliente por CPF e exibe nome na lacuna
    public void buscarCliente(){
        try{
            String cpf = contaView.getCPF();    //pega cpf digitado no campo de busca
            if("".equals(cpf)){                 //mostra mensagem se campo busca está vazio
                String s = "";
                contaView.resultadoCpf(s);
                contaView.mostrarMensagem("Digite um CPF para busca...");
            }
            else {
                Cliente cliente = clienteDao.getCliente(cpf);                   //pega cliente na base de dados e instancia objeto
                String nome = cliente.getNome() + " " + cliente.getSobrenome(); //define string com nome do cliente resultante
                if (nome != null){                                              
                    contaView.resultadoCpf(nome);                               //exibe nome do cliente na janela
                    //define visibilidade do painel
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
            String cpf = contaView.getCPF();                //pega cpf inserido no campo de busca
            Cliente cliente = clienteDao.getCliente(cpf);   //busca se cliente na base de dados
            contaDao.procuraCliente(cliente.getId());       //busca se cliente possui conta vinculada
            return 0;                                       
        }
        catch(Exception e){
            contaView.apresentaErro("Não é possível criar conta! " + e.getMessage());
        }
        return 1;
    }
    
    //cria conta - tipo = 1 conta corrente  -  tipo = 2 conta investimento
    public void criarConta(int tipo) {
        try{
            String cpf = contaView.getCPF();                        //pega cpf digitado no campo de busca
            Cliente cliente = clienteDao.getCliente(cpf);           //pega cliente na base de dados

            int x = contaDao.procuraCliente(cliente.getId());        //procura cliente na tabela de contas
            if (x == 0){ //se cliente ainda não pussui conta
                if (tipo == 1){ //conta corrente
                    ContaCorrente cc = contaView.getContaCorrente();    //pega os dados inseridos
                    cc.setDono(cliente);                                //seta id do cliente
                    Double d = contaView.getDepositoCC();               //pega saldo inicial
                    cc.setSaldo(cc.getSaldo() + d);                     //seta saldo
                    contaDao.inserirCC(cc);                             //insere conta corrente na base de dados
                    contaView.limparFormulario(tipo);                   //limpa formulario
                    contaView.mostrarMensagem("CONTA CORRENTE criada com sucesso!\nCliente: " + cliente.getNome() + " " + cliente.getSobrenome() + "\nNº CC: " + cc.getNumero());

                }
                else if (tipo == 2){ //conta investimento
                    ContaInvestimento ci = contaView.getContaInvestimento();    //pega os dados inseridos
                    ci.setDono(cliente);                                        //seta id cliente
                    Double d = contaView.getDepositoCI();                       //pega deposito inicial
                    //verifica se deposito inicial atende às condições estabelecidas
                    if(d < ci.getMontanteMin())
                        throw new RuntimeException("\nDEPÓSITO INICIAL não pode ser menor do que o MONTANTE MÍNIMO da conta...");
                    else if(d < ci.getDepositoMin())
                        throw new RuntimeException("\nDEPÓSITO INICIAL não pode ser menor do que o DEPÓSITO MÍNIMO da conta...");
                    else {
                        ci.setSaldo(ci.getSaldo() + d);                         //seta saldo
                        contaDao.inserirCI(ci);                                 //insere conta investimento na base de dados
                        contaView.limparFormulario(tipo);                       //limpa formulário
                        contaView.mostrarMensagem("CONTA INVESTIMENTO criada com sucesso!\nCliente: " + cliente.getNome() + " " + cliente.getSobrenome() + "\nNº CI: " + ci.getNumero());
                    }
                }
            }
        } catch(Exception e){
            contaView.apresentaErro("Não foi possível criar conta! " + e.getMessage());
        }
    }
    
    //troca visibilidade e retorna ao menu
    public void visibilidade() {
        this.contaView.desabilitaConta(this);       //destroi instancia
        JanelaMenuView view = new JanelaMenuView(); //cria view menu
        new MenuController(view);                   //instancia novo menu controller
    }
}