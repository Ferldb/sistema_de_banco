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
    
    public ClienteContaController(){
        this.ClienteContaView = new JanelaManipularContaView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
        this.contaDao = new ContaDao(new ConnectionFactory());
        initController();
    }
    
    private void initController(){
        this.ClienteContaView.setController(this);
        this.ClienteContaView.initView();
    }
    
    public void buscarCliente(){
        try{
            String cpf = ClienteContaView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            String nome = "Nome do cliente: "+cliente.getNome()+" "+cliente.getSobrenome();
            if (nome!=null) ClienteContaView.resultadoCpf(nome);
            else throw new RuntimeException();

            ClienteContaView.repaint();
        }
        catch(Exception e){
            ClienteContaView.apresentaErro("Contato não encontrado.");
        }
    }
    
    public void visibilidade() {
        this.ClienteContaView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
    
    public void movimentarConta(int index){
        try{
        String cpf = ClienteContaView.getCPF();
        Cliente cliente = modelDao.getCliente(cpf);
         //a busca cliente tem que verificar se tem conta
        boolean busca = contaDao.clienteExiste(cliente); //verifica se cliente tem conta //retornar erro se não tiver
        Conta conta = contaDao.buscaConta(cliente);
        //switch com os indices
        
        //0 - saque
        
        //pega o valor do campo valor na view
        //executa método da conta
        //chama dao
        //limpa campo e seta invisivel (botao e campo)
        //emitir notificação de sucesso
        
        
        // 1 - deposito
        
        //
        
        // 2 - saldo
        
        double saldo = conta.getSaldo();
        System.out.println("Saldo: "+saldo);
        ClienteContaView.mostrarSaldo(saldo);
        
        // 3 - remunera
        
        
        
        
        }
        catch(Exception e){
            ClienteContaView.apresentaErro("Cliente não encontrado.");
        }
    }
}
