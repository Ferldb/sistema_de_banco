/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco.controller;

import sistemabanco.model.Cliente;
import sistemabanco.model.dao.ClienteDao;
import sistemabanco.view.ClienteView;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteController {
    private ClienteDao modelDao;
    private ClienteView view;

    public ClienteController(ClienteView view,ClienteDao modelDao) {
        this.modelDao = modelDao;
        this.view = view;
        initController();
    }
    
    private void initController() {
        this.view.initView(this);
        this.listarClientes();
    }
    
    public void inserirCliente(){
        Cliente cliente = view.getCliente();
        if(cliente!=null){
            modelDao.inserir(cliente);
            view.inserirCliente(cliente);
            view.mostrarMensagem("Cliente inserido com sucesso");
        }
        
    }

    public void listarClientes() {
        try {
            List<Cliente> lista = modelDao.getLista();
            view.mostrarListaClientes(lista);
        } catch (SQLException ex) {
            view.mostrarErro("Erro ao listar clientes. Exceção:" + ex.getMessage());
        }
    }
    
    
    
}
