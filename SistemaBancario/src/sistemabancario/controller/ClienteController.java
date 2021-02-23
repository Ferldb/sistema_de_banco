package sistemabancario.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sistemabancario.model.Cliente;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.view.JanelaClienteView;
import sistemabancario.view.JanelaMenuView;

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
           try{
                Cliente cliente = clienteView.getCliente();
                int x = modelDao.buscaCliente(cliente.getCpf());
                if (x == 0){
                    modelDao.inserir(cliente);
                    clienteView.inserirCliente(cliente);
                    clienteView.limparFormulario();
                    clienteView.mostrarMensagem("Cliente inserido com sucesso!");
                }
           }
           catch(Exception e){
               clienteView.apresentaErro(e.getMessage());
           }
    }
    
    public void buscarCliente(){
        try{
            String cpf = clienteView.getCPF();
            Cliente cliente = modelDao.getCliente(cpf);
            clienteView.initBotoes(1);
            clienteView.preencherFormulario(cliente);
        }
        catch(Exception e){
            clienteView.apresentaErro(e.getMessage());
        }
    }
    
    public void atualizarCliente() {
        try{
            Cliente cliente = clienteView.getCliente();                 //pega cliente do formulario
            long id = modelDao.getCliente(cliente.getCpf()).getId();    //pega id
            cliente.setId(id);                                          //seta id
            modelDao.atualizar(cliente);
            clienteView.limparFormulario();
            listarClientes();
            clienteView.mostrarMensagem("Cliente atualizado com sucesso!");
            clienteView.initBotoes(0);
        }
        catch(Exception e){
            //clienteView.apresentaErro("Erro ao atualizar cliente.");
            clienteView.apresentaErro(e.getMessage());
        }
    }

    public void excluirCliente() {
       try{
           String cpf = clienteView.getCPF();
           Cliente cliente = modelDao.getCliente(cpf);
           int res = clienteView.confirmacao(cliente);
           if (res == 0){
               //buscar lista de contas para excluir
               //chamar contaDao para excluir contas
               modelDao.excluir(cliente);
               listarClientes();
               clienteView.mostrarMensagem("Cliente removido com sucesso!");
           }
       }
       catch (Exception e){
           clienteView.apresentaErro(e.getMessage());
       }
    }

    public void listarClientes() {
        try{
            List<Cliente> lista = this.modelDao.getLista(0);
            clienteView.mostrarListaClientes(lista);
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao listar clientes.");
        }
    }
    
    public void listarFiltro(int index){
        switch (index) {
            case 0:
                this.listarClientes();
                break;
            case 4:
                this.listarPorCpf();
                break;
            default:
                try{
                    String busca = clienteView.getCampoListar();
                    List<Cliente> lista = this.modelDao.listaFiltro(busca, index);
                    clienteView.mostrarListaClientes(lista);
                }
                catch(SQLException e){
                    clienteView.apresentaErro("Erro ao listar clientes: " + e.getMessage()); 
                }
                break;
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
            clienteView.apresentaErro("Erro ao listar clientes: " + e.getMessage());
        }
    }
    
    public void ordenarClientes(int index) {
        try{
            List<Cliente> lista = this.modelDao.getLista(index);
            Collections.sort(lista);
            clienteView.mostrarListaClientes(lista);
        }
        catch (Exception e){
            clienteView.apresentaErro("Erro ao listar clientes: " + e.getMessage());
        }
    }
    
    public void visibilidade() {
        this.clienteView.desabilitaMenu(this);
        JanelaMenuView view = new JanelaMenuView();
        new MenuController(view);
    }
}
