/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabancario.controller;

import java.util.ArrayList;
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
            clienteView.apresentaErro("Erro ao atualizar contato.");
        }
    }

    public void excluirCliente() {
       
    }

    public void listarClientes() {
        try{
            List<Cliente> lista = this.modelDao.getLista();
            clienteView.mostrarListaClientes(lista);
        }catch(Exception ex){
            clienteView.apresentaErro("Erro ao listar contatos.");
        }
    }

    public void listarPorNome() {
        try{
            String nome = clienteView.getCampoListar();
            List<Cliente> lista = this.modelDao.listaNome(nome);
            clienteView.mostrarListaClientes(lista);
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao listar contatos.");
        }
    }

    public void listarPorSobrenome() {
        try{
            String sobrenome = clienteView.getCampoListar();
            List<Cliente> lista = this.modelDao.listaSobrenome(sobrenome);
            clienteView.mostrarListaClientes(lista);
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao listar contatos.");
        }
    }

    public void listarPorRg() {
        try{
            String rg = clienteView.getCampoListar();
            List<Cliente> lista = this.modelDao.listaRG(rg);
            clienteView.mostrarListaClientes(lista);
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao listar contatos.");
        }
    }

    public void listarPorCpf() {
        try{
            String cpf = clienteView.getCampoListar();
            Cliente c = this.modelDao.getCliente(cpf);
            List<Cliente> lista = new ArrayList();
            lista.add(c);
            clienteView.mostrarListaClientes(lista);
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao listar contatos.");
        }
    }
    
    public void ordenarClientes(int index) {
        switch(index){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
