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
    private final String update = "update cliente set nome=?, sobrenome=?, rg=?, cpf=?, endereco=?, salario=? WHERE idcliente=?";
    private final String delete = "delete from cliente WHERE idcliente=?";
    private final String buscaCPF = "select * from cliente WHERE cpf = ?"; //busca cliente a partir de cpf
    private final String buscaNome = "select * from cliente WHERE nome LIKE ?"; //busca cliente a partir de nome ou parte
    private final String buscaSobrenome = "select * from cliente WHERE sobrenome LIKE ?"; //busca cliente a partir de sobrenome ou parte
    private final String buscaRG = "select * from cliente WHERE rg = ?"; //busca cliente a partir do rg

    public ClienteDao(ConnectionFactory conFactory) {
        this.connectionFactory = conFactory;
    }

    public void inserir(Cliente cliente) {
        Connection connection=connectionFactory.getConnection();
        try {
            // prepared statement para inserção
            PreparedStatement stmtAdiciona = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            // seta os valores
            stmtAdiciona.setString(1, cliente.getNome());
            stmtAdiciona.setString(2, cliente.getSobrenome());
            stmtAdiciona.setString(3, cliente.getRg());
            stmtAdiciona.setString(4, cliente.getCpf());
            stmtAdiciona.setString(5, cliente.getEndereco());
            stmtAdiciona.setDouble(6, cliente.getSalario());
            // executa
            stmtAdiciona.execute();
            //Seta o id do contato
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long i = rs.getLong(1);
            cliente.setId(i);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }

    public List<Cliente> getLista() throws SQLException{
        Connection connection=connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = connection.prepareStatement(select);
        try {
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
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
            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            rs.close();
            stmtLista.close();
        }

    }

    public void atualizar(Cliente cliente) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        PreparedStatement stmtAtualiza = connection.prepareStatement(update);
        try {
            stmtAtualiza.setString(1, cliente.getNome());
            stmtAtualiza.setString(2, cliente.getSobrenome());
            stmtAtualiza.setString(3, cliente.getRg());
            stmtAtualiza.setString(4, cliente.getCpf());
            stmtAtualiza.setString(5, cliente.getEndereco());
            stmtAtualiza.setDouble(6, cliente.getSalario());
            stmtAtualiza.setLong(7, cliente.getId());
            stmtAtualiza.executeUpdate();
        } finally{
            stmtAtualiza.close();
        }

    }
    
    public void exluirLista(List<Cliente> clientes) throws SQLException {
        for(Cliente cliente:clientes){
            excluir(cliente);
        }
    }

    public void excluir(Cliente cliente) throws SQLException {
        Connection connection=connectionFactory.getConnection();
        PreparedStatement stmtExcluir;
        stmtExcluir = connection.prepareStatement(delete);
        try {
            stmtExcluir.setLong(1, cliente.getId());
            stmtExcluir.executeUpdate();
        } finally{
            stmtExcluir.close();
        }
    }
    
    public Cliente getCliente(String cpf) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(buscaCPF);
        Cliente cliente;
        try{
            stmtBusca.setString(1, cpf);
            rs = stmtBusca.executeQuery();
            if (rs.next()){
                cliente = new Cliente(rs.getInt("idcliente"),rs.getString("nome"),rs.getString("sobrenome"),rs.getString("rg"),rs.getString("cpf"),rs.getString("endereco"),rs.getDouble("salario"));
                return cliente;
            }
            else{
                throw new RuntimeException("Não existe cliente com este cpf. CPF="+cpf);
            }
        } finally{
            stmtBusca.close();
        }
    }
    
    public List<Cliente> listaNome(String n) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = connection.prepareStatement(buscaNome);
        try {
            stmtLista.setString(1, "%" + n + "%");
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
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
            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            rs.close();
            stmtLista.close();
        }       
    }
    
    public List<Cliente> listaSobrenome(String s) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = connection.prepareStatement(buscaSobrenome);
        try {
            stmtLista.setString(1, "%" + s + "%");
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
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
            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            rs.close();
            stmtLista.close();
        } 
    }
    
    public List<Cliente> listaRG(String r) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        ResultSet rs = null;
        PreparedStatement stmtLista = connection.prepareStatement(buscaRG);
        try {
            stmtLista.setString(1,r);
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
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
            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            rs.close();
            stmtLista.close();
        }   
    }
}
