package sistemabancario.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            stmtAdiciona.setLong(4, conta.getIdcliente());
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
            stmtAdiciona.setLong(5, conta.getIdcliente());
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
    public void excluirConta(Conta conta){
        
    }
    
    //atualiza o saldo da conta
    public void atualizaSaldo(Conta conta, double valor){
        
    }
    
    //pega uma conta no banco de dados
    /*public Conta getConta(){
        
    }*/

}