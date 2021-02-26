package sistemabancario.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sistemabancario.model.Cliente;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ConnectionFactory;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.view.JanelaClienteView;
import sistemabancario.view.JanelaMenuView;

public class ClienteController {
    private JanelaClienteView clienteView;
    private ClienteDao modelDao;
    private ContaDao contaDao;
    
    public ClienteController(){
        this.clienteView = new JanelaClienteView();
        this.modelDao = new ClienteDao(new ConnectionFactory());
        this.contaDao = new ContaDao(new ConnectionFactory());
        initController();
    }
    
    //inicializa a janela Clientes
    private void initController(){
        this.clienteView.setController(this);   //seta os elementos da view
        this.clienteView.initView();            //inicializa a view
        this.listarClientes();                  //lista clientes cadastrados na tabela
    }
    
    //insere um cliente na base de dados
    public void inserirCliente() {
        try{
            Cliente cliente = clienteView.getCliente();         //instancia cliente com dados do formulário
            int x = modelDao.buscaCliente(cliente.getCpf());    //busca se cliente já existe na base de dados
            if (x == 0){                                        //se cliente não existe na base de dados, procede com a operação
                modelDao.inserir(cliente);                      //chama clienteDao para inserção
                clienteView.inserirCliente(cliente);            //insere o novo cliente na tabela de clientes
                clienteView.limparFormulario();                 //limpa o formulário de inserção de cliente
                clienteView.mostrarMensagem("Cliente inserido com sucesso!"); //mostra mensagem de sucesso
            }
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao inserir cliente! " + e.getMessage());
        }
    }
    
    //busca um cliente para atualização na base de dados
    public void buscarCliente(){
        try{
            String cpf = clienteView.getCPF();                                  //pega o cpf digitado no campo de busca
            if("".equals(cpf)){                                                 //se campo está vazio, emite mensagem de erro
                clienteView.mostrarMensagem("Digite um CPF para busca...");
            }
            else {
                Cliente cliente = modelDao.getCliente(cpf);                     //chama clienteDao e instancia cliente com os dados salvos
                clienteView.initBotoes(1);                                      //modifica visibilidade dos botões
                clienteView.preencherFormulario(cliente);                       //preenche o formulário com os dados do cliente a ser alterado
            }
        }catch(Exception e){
            clienteView.apresentaErro("Erro ao buscar cliente! " + e.getMessage());
        }
    }
    
    //atualiza dados do cliente na base de dados
    public void atualizarCliente() {
        try{
            Cliente cliente = clienteView.getCliente();                     //pega cliente do formulario
            long id = modelDao.getCliente(cliente.getCpf()).getId();        //pega id do cliente na base de dados
            cliente.setId(id);                                              //seta id do cliente 
            modelDao.atualizar(cliente);                                    //chama clienteDao passando cliente como parametro
            clienteView.limparFormulario();                                 //limpa formulário
            listarClientes();                                               //gera nova lista de clientes
            clienteView.mostrarMensagem("Cliente atualizado com sucesso!"); //mostra mensagem de sucesso
            clienteView.initBotoes(0);                                      //modifica visibilidade dos botoes
        }
        catch(Exception e){
            clienteView.apresentaErro(e.getMessage());
        }
    }
    
    //exclui cliente da base de dados
    public void excluirCliente() {
        try{
            String cpf = clienteView.getCPF();                                  //pega cpf digitado na busca
            if("".equals(cpf)){                                                 //se campo busca está vazio, mostra mensagem de erro
                clienteView.mostrarMensagem("Digite um CPF para busca...");
            }
            else {
                Cliente cliente = modelDao.getCliente(cpf);                     //pega cliente na base de dados
                int res = clienteView.confirmacao(cliente);                     //mostra confirmação de exclusão
                if (res == 0){                                                  //se usuário confirmou exclusão
                    boolean existe = contaDao.clienteExiste(cliente);           //busca cliente na tabela de contas
                    if(existe){                                                 //se cliente tem conta vinculada, exclui conta da base de dados
                        contaDao.excluirConta(cliente.getId());
                    }
                    modelDao.excluir(cliente);                                  //exclui cliente da base de dados
                    listarClientes();                                           //gera nova lista de clientes
                    clienteView.mostrarMensagem("Cliente removido com sucesso!"); //mostra mensagem de sucesso
                }
            }
        }
        catch (Exception e){
            clienteView.apresentaErro("Erro ao buscar cliente! " + e.getMessage());
       }
    }
    
    //lista clientes cadastrados na base de dados
    public void listarClientes() {
        try{
            List<Cliente> lista = this.modelDao.getLista(0);        //chama clienteDao e instancia lista com os clientes cadastrados
            clienteView.mostrarListaClientes(lista);                //mostra na tabela todos os clientes cadastrados
        }
        catch(Exception e){
            clienteView.apresentaErro("Erro ao listar clientes!");
        }
    }
    
    //lista clientes a partir de filtro
    public void listarFiltro(int index){
        switch (index) {
            case 0: //lista todos os clientes cadastrados
                this.listarClientes();
                break;
            case 4: //lista cliente a partir de um cpf digitado
                this.listarPorCpf();
                break;
            default: //outros filtros
                try{
                    String busca = clienteView.getCampoListar(); //pega informação do campo de busca
                    if("".equals(busca)){ //se campo de busca está vazio
                        if(index == 1)
                            clienteView.mostrarMensagem("Digite um NOME (ou parte) para busca...");
                        if(index == 2)
                            clienteView.mostrarMensagem("Digite um SOBRENOME (ou parte) para busca...");
                        if(index == 3)
                            clienteView.mostrarMensagem("Digite um RG para busca...");
                    }
                    else {
                        List<Cliente> lista = this.modelDao.listaFiltro(busca, index);  //gera lista de clientes a partir do filtro escolhido
                        clienteView.mostrarListaClientes(lista);                        //mostra na tabela a lista resultante
                    }
                }
                catch(Exception e){
                    List<Cliente> listaVazia = new ArrayList();
                    clienteView.mostrarListaClientes(listaVazia);   //se lista resultante não tem clientes
                    clienteView.apresentaErro("Erro ao listar clientes! " + e.getMessage());
                }
                break;
        }
    }
    
    //lista cliente por cpf buscado
    public void listarPorCpf() {
        try{
            String cpf = clienteView.getCampoListar();                          //pega cpf inserido no campo de busca
            if("".equals(cpf)){                                                 //se campo busca está vazio
                clienteView.mostrarMensagem("Digite um CPF para busca...");
            }
            else{
                Cliente c = this.modelDao.getCliente(cpf);                      //busca cliente na base de dados com cpf
                List<Cliente> lista = new ArrayList();                          //instancia nova lista e insere cliente
                lista.add(c);
                clienteView.mostrarListaClientes(lista);                        //mostra lista resultado na tabela
            }
        }
        catch(Exception e){
            List<Cliente> lista = new ArrayList();
            clienteView.mostrarListaClientes(lista);                            //se não encontrou o cpf buscado
            clienteView.apresentaErro("Erro ao listar cliente! " + e.getMessage());
        }
    }
    
    //ordena clientes a partir de filtro
    public void ordenarClientes(int index) {                                        //index possui número do filtro escolhido
        try{
            List<Cliente> lista = this.modelDao.getLista(index);                    //pega lista de clientes na base de dados
            Collections.sort(lista);                                                //ordena lista de clientes a partir do filtro escolhido
            clienteView.mostrarListaClientes(lista);                                //mostra lista ordenada na tabela
        }
        catch (Exception e){
            clienteView.apresentaErro("Erro ao listar clientes: " + e.getMessage());
        }
    }
    
    //troca visibilidade do menu e cria janela clientes
    public void visibilidade() {
        this.clienteView.desabilitaCliente(this);       //destroi clienteview
        JanelaMenuView view = new JanelaMenuView();     //instancia nova view para menu
        new MenuController(view);                       //instancia novo menu controller
    }
}
