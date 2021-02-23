/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.controller;

import sistemabancario.model.Cliente;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.view.JanelaManipularContaView;
import sistemabancario.view.JanelaMenuView;

/**
 *
 * @author Franshinsca
 */
public class ClienteContaController {
    private JanelaManipularContaView ClienteContaView;
    private ClienteDao modelDao;
    
    public ClienteContaController(){
        this.ClienteContaView = new JanelaManipularContaView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
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
}
