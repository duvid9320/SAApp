/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dave
 */
public class MySQLConnection {
    private final String user;
    private final String password;
    private final String database;
    private final String host;
    private final Connection connection;
    
    MySQLConnection(String user, String password, String database, String host) throws SQLException {
        this.user = user;
        this.password = password;
        this.database = database;
        this.host = host;
        connection = DriverManager.getConnection(getUrl(), user, password);
        System.out.println(connection != null ? "Se realizó la conexión a la base de datos" : "No se pudo conectar");
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void closeConnection() throws SQLException{
        connection.close();
        System.out.println("Conexión terminada");
    }
    
    private String getUrl(){
        return String.format("jdbc:mysql://%s/%s", host, database);
    }
}
