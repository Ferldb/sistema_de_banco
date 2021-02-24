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

    private void initController() {
        this.manipularView.setController(this);
        this.manipularView.initView();
    }

    public void buscarCliente() {
        try {
            String cpf = manipularView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            boolean busca = contaDao.clienteExiste(cliente); //verifica se cliente tem conta vinculada
            if (!busca) throw new RuntimeException("Cliente "+cliente.getNome()+" "+cliente.getNome()+" CPF: "+cliente.getCpf()+" não possui conta vinculada");
            String nome = "Nome do cliente: " + cliente.getNome() + " " + cliente.getSobrenome();
            if (nome != null) {
                manipularView.resultadoCpf(nome);
            } else {
                throw new RuntimeException("Cliente não encontrado");
            }

            manipularView.repaint();
        } catch (Exception e) {
            manipularView.apresentaErro(e.getMessage());
        }
    }

    public void visibilidade() {
        this.manipularView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
    
    public void movimentarConta(int index){
        try{
            String cpf = manipularView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            Conta conta = contaDao.buscaConta(cliente);
            double valor;
            
            switch (index) {
                case 0: //saque
                    valor = manipularView.getCampoValor();
                    boolean sucesso = conta.saca(valor);
                    if (sucesso == true) {
                        contaDao.atualizaSaldo(conta, conta.getSaldo());
                        manipularView.MostraMensagem("Sacado com sucesso! NOVO SALDO: " + conta.getSaldo());
                    } else {
                        manipularView.apresentaErro("Erro ao sacar");
                    }
                    break;
                case 1: //depósito
                    valor = manipularView.getCampoValor();
                    sucesso = conta.deposita(valor);
                    if (sucesso == true) {
                        contaDao.atualizaSaldo(conta, conta.getSaldo());
                        manipularView.MostraMensagem("Depositado com sucesso! NOVO SALDO: " + conta.getSaldo());
                    } else {
                        manipularView.apresentaErro("Erro ao depositar");
                    }
                    break;
                case 2: //saldo
                    double saldo = conta.getSaldo();
                    manipularView.mostrarSaldo(saldo);
                    break;
                case 3: //remuneração
                    conta.remunera();
                    contaDao.atualizaSaldo(conta, conta.getSaldo());
                    manipularView.MostraMensagem("Remunerado com sucesso! NOVO SALDO: " + conta.getSaldo());
                    break;
            }
        }
        catch(Exception e){
            manipularView.apresentaErro("Erro na operação. "+e.getMessage());
        }
    }
    
}
