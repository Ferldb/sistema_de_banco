package sistemabancario.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sistemabancario.model.Cliente;

public class ClienteDao {
// a conexão com o banco de dados

    private ConnectionFactory connectionFactory;
    private final String insert = "insert into cliente (nome,sobrenome,rg,cpf,endereco,salario) values (?,?,?,?,?,?)";
    private final String select = "select * from cliente";
    private final String update = "update cliente set nome=?, sobrenome=?, rg=?, endereco=?, salario=? WHERE idcliente=?";
    private final String delete = "delete from cliente WHERE idcliente=?";
    private final String buscaCPF = "select * from cliente WHERE cpf = ?"; //busca cliente a partir de cpf
    private final String buscaNome = "select * from cliente WHERE nome LIKE ?"; //busca cliente a partir de nome ou parte
    private final String buscaSobrenome = "select * from cliente WHERE sobrenome LIKE ?"; //busca cliente a partir de sobrenome ou parte
    private final String buscaRG = "select * from cliente WHERE rg = ?"; //busca cliente a partir do rg

    public ClienteDao(ConnectionFactory conFactory) {
        this.connectionFactory = conFactory;
    }
    
    //insere novo cliente na base de dados
    public void inserir(Cliente cliente) throws SQLException {
        Connection connection=connectionFactory.getConnection();
        // prepared statement para inserção
        PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        try {
            // seta os valores
            stmtAdiciona.setString(1, cliente.getNome());
            stmtAdiciona.setString(2, cliente.getSobrenome());
            stmtAdiciona.setString(3, cliente.getRg());
            stmtAdiciona.setString(4, cliente.getCpf());
            stmtAdiciona.setString(5, cliente.getEndereco());
            stmtAdiciona.setDouble(6, cliente.getSalario());
            // executa
            stmtAdiciona.execute();
            //Seta o id do cliente
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            cliente.setId(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            stmtAdiciona.close();   //finaliza conexão com base de dados
        }
    }
    
    //retorna lista de clientes cadastrados na base de dados
    public List<Cliente> getLista(int o) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        ResultSet rs = null;
        //prepare statement para select
        PreparedStatement stmtLista = connection.prepareStatement(select);
        try {
            //executa query
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
            //percorre resultado instanciando clientes e inserindo na lista
            while (rs.next()) {
                // criando o objeto Cliente
                long idcliente = rs.getLong("idcliente");
                String nome= rs.getString("nome");
                String sobrenome= rs.getString("sobrenome");
                String rg= rs.getString("rg");
                String cpf= rs.getString("cpf");
                String endereco = rs.getString("endereco");
                Double salario = rs.getDouble("salario");
                
                // instancia objeto cliente
                Cliente c = new Cliente(idcliente, nome, sobrenome, rg, cpf, endereco, salario);
                c.setOrdenar(o); //seta indice para ordenação (default 0 se não for chamada de ordenação)
                clientes.add(c); //adiciona cliente à lista
            }
           
            return clientes; //retorna lista de clientes
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{  //finaliza conexão com base de dados
            rs.close();
            stmtLista.close();
        }

    }
    
    //atualiza cliente na base de dados
    public void atualizar(Cliente cliente) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        //prepared statmement de update
        PreparedStatement stmtAtualiza = connection.prepareStatement(update);
        try {   //seta os dados atualizados (sem cpf, pois cpf não muda)
            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getSobrenome());
            stmtAtualiza.setString(3, cliente.getRg());
            stmtAtualiza.setString(4, cliente.getEndereco());
            stmtAtualiza.setDouble(5, cliente.getSalario());
            stmtAtualiza.setLong(6, cliente.getId());
            //executa query
            stmtAtualiza.executeUpdate();
        } finally{
            stmtAtualiza.close();   //finaliza conexão com base de dados
        }
    }

    //exclusão de cliente da base de dados
    public void excluir(Cliente cliente) throws SQLException {
        Connection connection=connectionFactory.getConnection();
        //prepared statement para delete
        PreparedStatement stmtExcluir;
        stmtExcluir = connection.prepareStatement(delete);
        try {
            stmtExcluir.setLong(1, cliente.getId());    //seta cliente a ser excluído
            stmtExcluir.executeUpdate();                //executa query
        } finally{
            stmtExcluir.close();    //finaliza conexão com base de dados
        }
    }
    
    //retorna exceção se o cliente NÃO for cadastrado
    public Cliente getCliente(String cpf) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        //prepared statement para busca de cliente por cpf
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(buscaCPF);
        try{
            stmtBusca.setString(1, cpf);    //seta cpf a ser buscado
            rs = stmtBusca.executeQuery();  //executa query
            if (rs.next()){                 //se cliente for econtrado, instancia novo objeto
                Cliente cliente = new Cliente(rs.getInt("idcliente"),rs.getString("nome"),rs.getString("sobrenome"),rs.getString("rg"),rs.getString("cpf"),rs.getString("endereco"),rs.getDouble("salario"));
                return cliente;     //retorna cliente instanciado
            }
            else{   //levanta exceção se não encontrar cliente
                throw new RuntimeException("\nNão existe cliente cadastrado com o CPF: " + cpf);
            }
        }
        finally{
            stmtBusca.close();  //finaliza conexão com base de dados
        }
    }
    
    //retorna exceção se o cliente JÁ for cadastrado
    public int buscaCliente(String cpf) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        //prepared statemente para busca de cliente por cpf
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(buscaCPF);
        try{
            stmtBusca.setString(1, cpf);        //seta o cpf a ser buscado
            rs = stmtBusca.executeQuery();      //executa query
            if (rs.next()){                     //se cliente existe na base de dados, levanta exceção
                throw new RuntimeException("\nCliente com o CPF " + cpf + " já é cadastrado...");
            }
            else 
                return 0;   //se cliente não tem cadastro
        }
        finally{
            stmtBusca.close();
        }
    }
    
    //cria lista com clientes a partir de filtro 1-nome 2-sobrenome 3-rg
    public List<Cliente> listaFiltro(String n, int i) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = null;
        String query = null;
        if(i == 1) {
            stmtLista = connection.prepareStatement(buscaNome);         //seta query de busca por nome ou parte
            query = "%"+n+"%";
        }
        if(i == 2){
            stmtLista = connection.prepareStatement(buscaSobrenome);    //seta query de busca por sobrenome ou parte
            query = "%"+n+"%";
        }
        if(i == 3){
            query = n;
            stmtLista = connection.prepareStatement(buscaRG);           //seta query de busca por rg
        }
        try {
            stmtLista.setString(1, query);  //seta query a ser buscada
            rs = stmtLista.executeQuery();  //executa query
            List<Cliente> clientes = new ArrayList();
            //percorre resultado instanciando clientes encontrados
            while (rs.next()) {
                // criando o objeto Cliente
                long idcliente = rs.getLong("idcliente");
                String nome= rs.getString("nome");
                String sobrenome= rs.getString("sobrenome");
                String rg= rs.getString("rg");
                String cpf= rs.getString("cpf");
                String endereco = rs.getString("endereco");
                Double salario = rs.getDouble("salario");
                
                // adicionando o objeto à lista
                clientes.add(new Cliente(idcliente, nome, sobrenome, rg, cpf, endereco, salario));
            }
            //levanta exceções se não encontrou nenhum resultado com o filtro escolhido
            if(clientes.isEmpty()){
                if(i == 1)
                    throw new RuntimeException("\nNão existem clientes cadastrados com o NOME ou parte dele ''" + n + "''");
                if(i == 2)
                    throw new RuntimeException("\nNão existem clientes cadastrados com o SOBRENOME ou parte dele ''" + n + "''");
                if(i == 3)
                    throw new RuntimeException("\nNão existe cliente cadastrado com o RG: " + n);
            }
            return clientes;    //retorna lista de clientes
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            rs.close();
            stmtLista.close();  //finaliza conexão com base de dados
        }       
    }
}
