/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.controller;

import java.util.List;
import sistemabancario.model.Cliente;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.view.JanelaClienteView;

/**
 *
 * @author Franshinsca
 */
public class ClienteController {
    private JanelaClienteView clienteView;
    private ClienteDao modelDao;
    
    public ClienteController(){
        this.clienteView = new JanelaClienteView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
        initController();
    }
    
    private void initController(){
        this.clienteView.setController(this);
        this.clienteView.initView();
        this.listarClientes();
    }
    
    public void inserirCliente() {
        Cliente cliente = clienteView.getCliente();
        if(cliente!=null){
            modelDao.inserir(cliente);
            clienteView.inserirCliente(cliente);
            clienteView.mostrarMensagem("Cliente inserido com sucesso");
        }
    }
    
    public void buscarCliente(){
        try{
            String cpf = clienteView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            clienteView.preencherFormulario(cliente);
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao atualizar contato."+e.getMessage());
        }
    }
    
    public void atualizarCliente() {
        try{
            Cliente cliente = clienteView.getCliente();                 //pega cliente do formulario
            long id = modelDao.getCliente(cliente.getCpf()).getId();    //pega id
            cliente.setId(id);                                          //seta id
            if (cliente == null){
                clienteView.apresentaErro("Selecione um contato para atualizar!!");
                return;
            }
            modelDao.atualizar(cliente);
            clienteView.mostrarMensagem("Cliente atualizado com sucesso");
            listarClientes();
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao atualizar contato."+e.getMessage());
        }
    }

    public void excluirCliente() {
       
    }

    public void listarClientes() {
        try{
            List<Cliente> lista = this.modelDao.getLista();
            clienteView.mostrarListaContatos(lista);
        }catch(Exception ex){
            clienteView.apresentaErro("Erro ao listar contatos.");
        }
    }

    public void ordenarClientes() {
    
    }

}
