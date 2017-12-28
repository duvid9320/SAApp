/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class MySQLManager {
    private static final String USER;
    private static final String PASSWORD;
    private static final String DATABASE;
    private static final String HOST;
    
    static{
        USER = "root";
        PASSWORD = "root";
        DATABASE = "itzDB";
        HOST = "localhost";
    }
    
    private static MySQLManager mySQLManager;
    private MySQLConnection mySQLConnection;

    private MySQLManager() {
        try {
            this.mySQLConnection = new MySQLConnection(USER, PASSWORD, DATABASE, HOST);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MySQLManager getInstance(){
        return mySQLManager != null ? mySQLManager : (mySQLManager = new MySQLManager());
    }
   
    public ResultSet getResultSetFromQuery(String query) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        return query != null && !query.trim().isEmpty() ? mySQLConnection.getConnection().createStatement().executeQuery(query) : null;
    }
    
    public boolean executeUpdate(String sql) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        try(Statement st = mySQLConnection.getConnection().createStatement()){
            return st.executeUpdate(sql) > 0;
        }
    }
    
    public boolean executeSQL(String sql) throws SQLException{
        try(Statement st = mySQLConnection.getConnection().createStatement()){
            return st.execute(sql);
        }
    }
    
    public List<HashMap<String, Object>> getDataListFromQuery(String query) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        try (ResultSet rs = getResultSetFromQuery(query)) {
            if(rs != null && rs.getMetaData().getColumnCount() > 0){
                List<HashMap<String, Object>> rows = new LinkedList<>();
                while(rs.next()){
                    HashMap<String, Object> row = new HashMap();
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++)
                        row.put(rs.getMetaData().getColumnName((i+1)), rs.getObject((i+1)));
                    rows.add(row);
                }
                return rows;
            }
        }
        return Collections.<HashMap<String, Object>>emptyList();
    }
    
    public DatabaseMetaData getMetadata() throws SQLException{
        return mySQLConnection.getConnection().getMetaData();
    }
}
