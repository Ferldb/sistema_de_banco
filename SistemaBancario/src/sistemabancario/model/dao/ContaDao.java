package sistemabancario.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sistemabancario.model.Cliente;
import sistemabancario.model.Conta;
import sistemabancario.model.ContaCorrente;
import sistemabancario.model.ContaInvestimento;

public class ContaDao {
    
    private ConnectionFactory connectionFactory;
    private final String insertCC = "insert into conta (tipoconta,saldo,limite,idcliente) values (?,?,?,?)";
    private final String insertCI = "insert into conta (tipoconta,saldo,montanteMinimo,depositoMinimo,idcliente) values (?,?,?,?,?)";
    private final String update = "update conta set saldo = ? where numconta = ?";
    private final String select = "select * from conta WHERE idcliente = ?";
    private final String delete = "delete from conta WHERE idcliente=?";
    
    public ContaDao(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }
    
    //insere conta corrente na base de dados
    public void inserirCC(ContaCorrente conta) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        // prepared statement para inserção
        PreparedStatement stmtAdiciona = connection.prepareStatement(insertCC, Statement.RETURN_GENERATED_KEYS);
        try { 
            // seta os valores
            stmtAdiciona.setLong(1, 1);
            stmtAdiciona.setDouble(2, conta.getSaldo());
            stmtAdiciona.setDouble(3, conta.getLimite());
            stmtAdiciona.setLong(4, conta.getDono().getId());
            // executa
            stmtAdiciona.execute();
            //Seta o id da conta
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            conta.setNumero(id);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            stmtAdiciona.close();
        }
    }
    
    //insere conta investimento na base de dados
    public void inserirCI(ContaInvestimento conta) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        // prepared statement para inserção
        PreparedStatement stmtAdiciona = connection.prepareStatement(insertCI, Statement.RETURN_GENERATED_KEYS);
        try {
            // seta os valores
            stmtAdiciona.setLong(1, 2);
            stmtAdiciona.setDouble(2, conta.getSaldo());
            stmtAdiciona.setDouble(3, conta.getMontanteMin());
            stmtAdiciona.setDouble(4, conta.getDepositoMin());
            stmtAdiciona.setLong(5, conta.getDono().getId());
            // executa
            stmtAdiciona.execute();
            //Seta o id da conta
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            conta.setNumero(id);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            stmtAdiciona.close();
        }
    }
    
    //exclui uma conta da base de dados
    public void excluirConta(long idcliente) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        //prepared statemente para delete
        PreparedStatement stmtExcluir;
        stmtExcluir = connection.prepareStatement(delete);
        try {
            stmtExcluir.setLong(1, idcliente);  //seta idcliente para exclusão
            stmtExcluir.executeUpdate();        //executa query
        } finally{
            stmtExcluir.close();   //finaliza conexão com base de dados
        }
    }
    
    //atualiza o saldo da conta
    public void atualizaSaldo(Conta conta, double valor) throws SQLException{
         Connection connection=connectionFactory.getConnection();
         //prepared statemente para update
         PreparedStatement stmtUpdate = connection.prepareStatement(update);
        try {
            // seta os valores
            stmtUpdate.setDouble(1, valor);
            stmtUpdate.setLong(2, conta.getNumero());
            // executa
            stmtUpdate.executeUpdate();
            
        } finally{
            stmtUpdate.close(); //finaliza conexão com base de dados
        } 
    }
    
    //procura cliente na tabela de contas - retorna exceção se o cliente JÁ possuir conta vinculada
    public int procuraCliente(long idcliente) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        //prepared statemente para select
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(select);
        try{
            stmtBusca.setLong(1, idcliente);    //seta idcliente a ser buscado
            rs = stmtBusca.executeQuery();      //executa query
            if (rs.next()){                     //levanta exceção se encontrar cliente
                throw new RuntimeException("\nCliente já possui uma conta vinculada!");
            }
            else 
                return 0;
        }
        finally{
            stmtBusca.close();  //finaliza conexão com base de dados
        }
    }
    
    //retorna exceção se o cliente NÃO possuir conta vinculada
    public int procuraCliente2(long idcliente) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(select);
        try{
            stmtBusca.setLong(1, idcliente);    //seta idcliente a ser buscado 
            rs = stmtBusca.executeQuery();      //executa query
            if(rs.next()){
                return 0;
            }
            else    //levanta exceção se cliente não possui conta vinculada
                throw new RuntimeException("\nCliente NÃO possui uma conta vinculada!");
        }
        finally{
            stmtBusca.close();  //finaliza conexão com base de dados
        }
    }
    
    //verifica se cliente a ser excluído está na tabela de contas
    public boolean clienteExiste(Cliente c) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(select);
        boolean existe = false;
        try{
            stmtBusca.setLong(1, c.getId());    //seta idcliente a ser buscado
            rs = stmtBusca.executeQuery();      //executa query
            if(rs.next()) existe = true;        
        }
        finally{
            stmtBusca.close();  //finaliza conexão com base de dados
        }
        return existe;
    }
    
    //busca conta de cliente passado por parametro
    public Conta buscaConta(Cliente cliente) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(select);
        Conta conta = null;
        long tipo;
        try{
            stmtBusca.setLong(1, cliente.getId());  //seta idcliente
            rs = stmtBusca.executeQuery();          //executa query
            if(rs.next()){                          //se uma conta for encontrada
                tipo = rs.getLong("tipoconta");     //pega o tipo da conta
                if (tipo == 1) { //conta corrente
                    conta = new ContaCorrente(rs.getDouble("limite"),rs.getLong("numconta"),cliente,rs.getDouble("saldo"));
                }
                else {  //conta investimento
                    conta = new ContaInvestimento(rs.getLong("numconta"),cliente,rs.getDouble("saldo"),rs.getDouble("montanteMinimo"),rs.getDouble("depositoMinimo"));
                }
            }
        }
        finally{
            stmtBusca.close();  //finaliza conexão com base de dados
        }
        return conta;   //retorna a conta encontrada
    }
}