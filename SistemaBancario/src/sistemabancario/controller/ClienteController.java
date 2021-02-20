/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.controller;

import sistemabancario.view.JanelaClienteView;

/**
 *
 * @author Franshinsca
 */
public class ClienteController {
    private JanelaClienteView clienteView;
    
    public ClienteController(){
        this.clienteView = new JanelaClienteView();
        initController();
    }
    
    private void initController(){
        this.clienteView.setController(this);
        this.clienteView.initView();
    }
    
    public void criarCliente() {
    
    }

    public void atualizarCliente() {
    
    }

    public void excluirCliente() {
       
    }

    public void listarClientes() {
    
    }

    public void ordenarClientes() {
    
    }

}
