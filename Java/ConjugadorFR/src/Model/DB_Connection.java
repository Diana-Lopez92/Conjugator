package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Diana
 */
public class DB_Connection {
    
    String dataBase = "frances";
    String url = "jdbc:mysql://localhost/" + dataBase;
    String user = "diana";
    String pass = "87654321_";
    
    private Connection conn = null;
    
    public Connection getConnection(){
        try{
            // This is taken from MySQL Java Connector Guide
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, pass);
            
            if(conn != null){
                System.out.println("Successful Connection!");            
            }
            else{
                System.out.println("Failed to make connection!");
            }
        }catch(Exception ex){
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return conn;
    }
}
