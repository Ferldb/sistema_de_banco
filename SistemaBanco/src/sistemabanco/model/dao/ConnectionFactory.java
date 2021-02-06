package sistemabanco.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/sistemabancario", "admin", "root"); //Jessima
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void closeConnection(Connection con){
        try{
           con.close();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    public static void closeStatement(PreparedStatement stmt){
        try{
           stmt.close();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
