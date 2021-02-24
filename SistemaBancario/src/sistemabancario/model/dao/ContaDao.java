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
    
    //queries não testadas
    private ConnectionFactory connectionFactory;
    private final String insertCC = "insert into conta (tipoconta,saldo,limite,idcliente) values (?,?,?,?)";
    private final String insertCI = "insert into conta (tipoconta,saldo,montanteMinimo,depositoMinimo,idcliente) values (?,?,?,?,?)";
    private final String update = "update conta set saldo = ? where idcliente = ?";
    private final String select = "select * from conta WHERE idcliente = ?";
    private final String delete = "delete from conta WHERE idcliente=?";
    
    public ContaDao(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }
    
    //insere conta corrente
    public void inserirCC(ContaCorrente conta){
        Connection connection=connectionFactory.getConnection();
        try {
            // prepared statement para inserção
            PreparedStatement stmtAdiciona = connection.prepareStatement(insertCC, Statement.RETURN_GENERATED_KEYS);
            // seta os valores
            stmtAdiciona.setLong(1, 1);
            stmtAdiciona.setDouble(2, conta.getSaldo());
            stmtAdiciona.setDouble(3, conta.getLimite());
            stmtAdiciona.setLong(4, conta.getCliente().getId());
            // executa
            stmtAdiciona.execute();
            //Seta o id da conta
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            conta.setIdconta(id);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    //insere conta investimento
    public void inserirCI(ContaInvestimento conta){
        Connection connection=connectionFactory.getConnection();
        try {
            // prepared statement para inserção
            PreparedStatement stmtAdiciona = connection.prepareStatement(insertCI, Statement.RETURN_GENERATED_KEYS);
            // seta os valores
            stmtAdiciona.setLong(1, 2);
            stmtAdiciona.setDouble(2, conta.getSaldo());
            stmtAdiciona.setDouble(3, conta.getMontanteMin());
            stmtAdiciona.setDouble(4, conta.getDepositoMin());
            stmtAdiciona.setLong(5, conta.getCliente().getId());
            // executa
            stmtAdiciona.execute();
            //Seta o id da conta
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            conta.setIdconta(id);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    //exclui uma conta (decidir se passar objeto conta ou cliente)
    public void excluirConta(long idcliente) throws SQLException{
        Connection connection=connectionFactory.getConnection();
        PreparedStatement stmtExcluir;
        stmtExcluir = connection.prepareStatement(delete);
        try {
            stmtExcluir.setLong(1, idcliente);
            stmtExcluir.executeUpdate();
        } finally{
            stmtExcluir.close();
        }
    }
    
    //atualiza o saldo da conta
    public void atualizaSaldo(Conta conta, double valor){
         Connection connection=connectionFactory.getConnection();
        try {
            // prepared statement para inserção
            PreparedStatement stmtUpdate = connection.prepareStatement(update);
            // seta os valores
            stmtUpdate.setDouble(1, valor);
            stmtUpdate.setLong(2, conta.getIdconta());
            // executa
            stmtUpdate.execute();
            //Seta o id da conta
            stmtUpdate.execute();            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    public void procuraCliente(long idcliente) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(select);
        try{
            stmtBusca.setLong(1, idcliente);
            rs = stmtBusca.executeQuery();
            if(rs.next()) throw new RuntimeException("Cliente já possui conta vinculada!");
        }
        finally{
            stmtBusca.close();
        }
    }
    
    public boolean clienteExiste(Cliente c) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(select);
        boolean existe = false;
        try{
            stmtBusca.setLong(1, c.getId());
            rs = stmtBusca.executeQuery();
            if(rs.next()) existe = true;
        }
        finally{
            stmtBusca.close();
        }
        return existe;
    }

    public Conta buscaConta(Cliente cliente) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        PreparedStatement stmtBusca;
        ResultSet rs = null;
        stmtBusca = connection.prepareStatement(select);
        Conta conta = null;
        long tipo;
        try{
            stmtBusca.setLong(1, cliente.getId());
            rs = stmtBusca.executeQuery();
            if(rs.next()){
                tipo = rs.getLong("tipoconta"); //pega o tipo da conta
                if (tipo == 1) {
                    conta = new ContaCorrente(rs.getDouble("limite"),rs.getLong("numconta"),cliente,rs.getDouble("saldo"));
                    conta.setTipoconta(tipo);
                }
                else {
                    conta = new ContaInvestimento(rs.getLong("numconta"),cliente,rs.getDouble("saldo"),rs.getDouble("montanteMinimo"),rs.getDouble("depositoMinimo"));
                    conta.setTipoconta(tipo);
                }
            }
        }
        finally{
            stmtBusca.close();
        }
        return conta;
    }
}
