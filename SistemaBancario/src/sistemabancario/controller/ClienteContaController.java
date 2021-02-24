/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            if (conta.getTipoconta() == 1) {
                ContaCorrente contac = contaDao.buscaContaCorrente(cliente);
                double valor;
                switch (index) {
                    //0 - saque
                    case 0:
                        valor = ClienteContaView.getCampoValor();
                        boolean sucesso = contac.saca(valor);
                        if (sucesso == true) {
                            contaDao.atualizaSaldo(contac, contac.getSaldo());
                            ClienteContaView.MostraMensagem("Sacado com sucesso! NOVO SALDO: " + contac.getSaldo());
                        } else {
                            ClienteContaView.apresentaErro("Erro ao sacar");
                        }
                        break;
                    case 1:
                        valor = ClienteContaView.getCampoValor();
                        sucesso = contac.deposita(valor);
                        if (sucesso == true) {
                            contaDao.atualizaSaldo(contac, contac.getSaldo());
                            ClienteContaView.MostraMensagem("Depositado com sucesso! NOVO SALDO: " + contac.getSaldo());
                        } else {
                            ClienteContaView.apresentaErro("Erro ao depositar");
                        }
                        break;
                    case 2:
                        double saldo = contac.getSaldo();
                        ClienteContaView.mostrarSaldo(saldo);
                        break;
                    case 3:
                        contac.remunera();
                        contaDao.atualizaSaldo(contac, contac.getSaldo());
                        ClienteContaView.MostraMensagem("Remunerado com sucesso! NOVO SALDO: " + contac.getSaldo());
                        break;
                }
            }
            if (conta.getTipoconta() == 2) {
                ContaInvestimento contai = contaDao.buscaContaInvestimento(cliente);
                double valor;
                switch (index) {
                    //0 - saque
                    case 0:
                        valor = ClienteContaView.getCampoValor();
                        boolean sucesso = contai.saca(valor);
                        if (sucesso == true) {
                            contaDao.atualizaSaldo(contai, contai.getSaldo());
                            ClienteContaView.MostraMensagem("Sacado com sucesso! NOVO SALDO: " + contai.getSaldo());
                        } else {
                            ClienteContaView.apresentaErro("Erro ao sacar");
                        }
                        break;
                    case 1:
                        valor = ClienteContaView.getCampoValor();
                        sucesso = contai.deposita(valor);
                        if (sucesso == true) {
                            contaDao.atualizaSaldo(contai, contai.getSaldo());
                            ClienteContaView.MostraMensagem("Depositado com sucesso! NOVO SALDO: " + contai.getSaldo());
                        } else {
                            ClienteContaView.apresentaErro("Erro ao depositar");
                        }
                        break;
                    case 2:
                        double saldo = contai.getSaldo();
                        ClienteContaView.mostrarSaldo(saldo);
                        break;
                    case 3:
                        contai.remunera();
                        contaDao.atualizaSaldo(contai, contai.getSaldo());
                        ClienteContaView.MostraMensagem("Remunerado com sucesso! NOVO SALDO: " + contai.getSaldo());
                        break;
                }
            }
        } catch (Exception e) {
            ClienteContaView.apresentaErro("Erro na operação.");
        }
    }
}
