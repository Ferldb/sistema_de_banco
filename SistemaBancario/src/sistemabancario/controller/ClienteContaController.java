/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.controller;

import java.sql.SQLException;
import sistemabancario.model.Cliente;
import sistemabancario.model.Conta;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.view.JanelaManipularContaView;
import sistemabancario.view.JanelaMenuView;

/**
 *
 * @author Franshinsca
 */
public class ClienteContaController {

    private JanelaManipularContaView ClienteContaView;
    private ClienteDao modelDao;
    private ContaDao contaDao;

    public ClienteContaController() {
        this.ClienteContaView = new JanelaManipularContaView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
        this.contaDao = new ContaDao(new ConnectionFactory());
        initController();
    }

    private void initController() {
        this.ClienteContaView.setController(this);
        this.ClienteContaView.initView();
    }

    public void buscarCliente() {
        try {
            String cpf = ClienteContaView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            String nome = "Nome do cliente: " + cliente.getNome() + " " + cliente.getSobrenome();
            if (nome != null) {
                ClienteContaView.resultadoCpf(nome);
            } else {
                throw new RuntimeException();
            }

            ClienteContaView.repaint();
        } catch (Exception e) {
            ClienteContaView.apresentaErro("Contato não encontrado.");
        }
    }

    public void visibilidade() {
        this.ClienteContaView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }

    public void movimentarConta(int index) {
        try {
            String cpf = ClienteContaView.getCPF();

            Cliente cliente = modelDao.getCliente(cpf);
            //a busca cliente tem que verificar se tem conta
            boolean busca = contaDao.clienteExiste(cliente); //verifica se cliente tem conta //retornar erro se não tiver
            Conta conta = contaDao.buscaConta(cliente);
            //switch com os indices
            double valor;
            switch (index) {
                //0 - saque
                case 0:
                    valor = ClienteContaView.getCampoValor();
                    boolean sucesso = conta.saca(valor);
                    contaDao.atualizaSaldo(conta, conta.getSaldo());
                    if (sucesso == true) {
                        ClienteContaView.MostraMensagem("Sacado com sucesso!");
                    } else {
                        ClienteContaView.apresentaErro("Erro ao sacar");
                    }
                    break;
                //pega o valor do campo valor na view
                //executa método da conta
                //chama dao
                //limpa campo e seta invisivel (botao e campo)
                //emitir notificação de sucesso
                case 1:
                    valor = ClienteContaView.getCampoValor();
                    sucesso = conta.deposita(valor);
                    contaDao.atualizaSaldo(conta, conta.getSaldo());
                    if (sucesso == true) {
                        ClienteContaView.MostraMensagem("Depositado com sucesso!");
                    } else {
                        ClienteContaView.apresentaErro("Erro ao depositar");
                    }
                    break;
                case 2:
                    double saldo = conta.getSaldo();
                    ClienteContaView.mostrarSaldo(saldo);
                    break;
                case 3:
                    long tipoc = conta.getTipoconta();
                    if (tipoc == 1) {
                        valor = conta.getSaldo() * 1.01;
                        contaDao.atualizaSaldo(conta, valor);
                    } else if (tipoc == 2) {
                        valor = conta.getSaldo() * 1.02;
                        contaDao.atualizaSaldo(conta, valor);
                    }
                    break;
            }
        } catch (Exception e) {
            ClienteContaView.apresentaErro("Clientea não encontrado.");
        }
    }
}
