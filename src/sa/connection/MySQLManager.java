/* 
 * The MIT License
 *
 * Copyright 2017 David Rodríguez <duvid9320@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import sa.utils.SAOutput;

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
            SAOutput.showErrorMessage("Comprueba el servidor MYSQL");
            System.exit(0);
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
