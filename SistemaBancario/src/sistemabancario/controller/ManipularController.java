package sistemabancario.controller;

import java.sql.SQLException;
import sistemabancario.model.Cliente;
import sistemabancario.model.Conta;
import sistemabancario.model.ContaCorrente;
import sistemabancario.model.ContaInvestimento;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.view.JanelaManipularContaView;
import sistemabancario.view.JanelaMenuView;

public class ManipularController {

    private JanelaManipularContaView manipularView;
    private ClienteDao modelDao;
    private ContaDao contaDao;

    public ManipularController() {
        this.manipularView = new JanelaManipularContaView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
        this.contaDao = new ContaDao(new ConnectionFactory());
        initController();
    }
    
    //inicializa elementos da view
    private void initController() {
        this.manipularView.setController(this);
        this.manipularView.initView();
    }

    //procura cliente por CPF e exibe nome na lacuna
    public void buscarCliente() {
        try {
            String cpf = manipularView.getCPF();    //pega cpf digitado no campo de busca
            if("".equals(cpf)){                     //se campo busca está vazio
                String s = "";
                manipularView.resultadoCpf(s);
                manipularView.mostrarMensagem("Digite um CPF para busca...");
            }
            else{
                Cliente cliente = modelDao.getCliente(cpf);                     //pega cliente na base de dados
                String nome = cliente.getNome() + " " + cliente.getSobrenome(); //gera string nome para exibir na janela
                if (nome != null){
                    manipularView.resultadoCpf(nome);
                    if(manipularView.getlOperacao().isVisible())
                        manipularView.getlOperacao().setVisible(false);
                    if(manipularView.getbConfirmar().isVisible())
                        manipularView.getbConfirmar().setVisible(false);
                    if(manipularView.getCampoValor().isVisible())
                        manipularView.getCampoValor().setVisible(false);
                }
                else
                    throw new RuntimeException();
                manipularView.repaint();
            }
        } catch (Exception e) {
            String s = "";
            manipularView.resultadoCpf(s);
            manipularView.apresentaErro("Erro ao buscar cliente! " + e.getMessage());
        }
    }
    
    //verifica se o cliente buscado já possui conta vinculada ou não
    public int verificarCliente(){
        try{
            String cpf = manipularView.getCPF();            //pega cpf digitado no campo de busca
            Cliente cliente = modelDao.getCliente(cpf);     //busca cliente na base de dados cliente
            contaDao.procuraCliente(2, cliente.getId());    //busca cliente na tabela de contas
            return 0;
        }
        catch(Exception e){
            manipularView.apresentaErro("Não foi possível carregar os dados! " + e.getMessage());
        }
        return 1;
    }
    
    //movimentação da conta
    public void movimentarConta(int index){
        try{
            String cpf = manipularView.getCPF();            //pega cpf digitado no campo de busca
            Cliente cliente = modelDao.getCliente(cpf);     //pega cliente na base de dados
            Conta conta = contaDao.buscaConta(cliente);     //pega conta do cliente selecionado
            double v = 0.0;
            String valor = "";
            boolean sucesso;
            
            switch (index) {    //index 0 - saque 1 - deposito 2 - saldo 3 - remunera
                case 0: //saque
                    try{
                        valor = manipularView.getCampoValor().getText();            //pega valor digitado
                        v = Double.parseDouble(valor);                              //transforma em double
                        try{
                            sucesso = conta.saca(v);                                //chama método saque da conta
                            if (sucesso == true) {                                  //se retorno do saque é true
                                contaDao.atualizaSaldo(conta, conta.getSaldo());    //atualiza saldo na base de dados
                                String s = String.format("%.2f", conta.getSaldo()); //formata saldo com 2 casas decimais
                                manipularView.mostrarMensagem("SAQUE realizado com sucesso!\nNOVO SALDO: R$ " + s); //exibe mensagem de sucesso
                                this.movimentarConta(2);    //exibe saldo
                            } else {
                                manipularView.apresentaErro("Não foi possível completar o SAQUE!\nO valor a ser sacado não pode ser negativo!");
                            }
                            break;
                        }catch(Exception e){
                            manipularView.apresentaErro(e.getMessage());
                        }
                        break;
                    }catch(Exception e){
                        if ("".equals(valor))
                            throw new RuntimeException("Digite um valor para SAQUE!");
                        else
                            throw new RuntimeException("Valor de SAQUE inválido...");
                    }
                case 1: //depósito
                    try{
                        valor = manipularView.getCampoValor().getText();            //pega valor digitado
                        v = Double.parseDouble(valor);                              //transforma valor em double
                        try{
                            sucesso = conta.deposita(v);                            //chama método depósito da conta
                            if (sucesso == true) {                                  //se método retornar true
                                contaDao.atualizaSaldo(conta, conta.getSaldo());    //atualiza salto
                                String s = String.format("%.2f", conta.getSaldo()); //formata saldo com 2 casas decimais
                                manipularView.mostrarMensagem("DEPÓSITO realizado com sucesso!\nNOVO SALDO: R$ " + s);  //mostra mensagem de sucesso
                                this.movimentarConta(2);
                            } else {
                                manipularView.apresentaErro("Não foi possível completar o DEPÓSITO!\nO valor a ser depositado não pode ser negativo!");
                            }
                            break;
                        }catch(Exception e){
                            manipularView.apresentaErro(e.getMessage());
                        }
                        break;
                    }catch(Exception e){
                        if ("".equals(valor))
                            throw new RuntimeException("Digite um valor para DEPÓSITO!");
                        else
                            throw new RuntimeException("Valor de DEPÓSITO inválido...");
                    }
                case 2: //ver saldo
                    double saldo = conta.getSaldo();    //pega saldo da conta selecionada
                    manipularView.mostrarSaldo(saldo);  //exibe saldo na tela
                    break;
                case 3: //remuneração
                    conta.remunera();                                   //chama método de remuneração da conta
                    contaDao.atualizaSaldo(conta, conta.getSaldo());    //atualiza saldo da conta na base de dados
                    String s = String.format("%.2f", conta.getSaldo()); //formata saldo com 2 casas decimais
                    manipularView.mostrarMensagem("Remuneração realiada com sucesso!\nNOVO SALDO: R$ " + s);    //apresenta mensagem de sucesso
                    this.movimentarConta(2);
                    break;
            }
        }
        catch(Exception e){
            manipularView.apresentaErro(e.getMessage());
        }
    }
    
    //troca visibilidade e volta ao menu
    public void visibilidade() {
        this.manipularView.desabilitaManipular(this);   //destroi view
        JanelaMenuView view = new JanelaMenuView();     //instancia novo menu
        new MenuController(view);                       //instancia novo controller menu
    }
}
