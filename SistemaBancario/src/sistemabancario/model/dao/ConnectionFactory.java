package sistemabancario.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/sistemabancario", "admin", "root"); //Jessima
            //return DriverManager.getConnection("jdbc:mariadb://localhost:3306/sistemabancario","root", ""); //Duda
            //return DriverManager.getConnection("jdbc:mysql://localhost/sistemabancario", "root", ""); //Madu
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
