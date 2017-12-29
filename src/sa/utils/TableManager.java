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
package sa.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dave
 */
public class TableManager {
    
    public static DefaultTableModel getDefaultTableModelFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(rs != null && rs.getMetaData().getColumnCount() > 0)
            return new DefaultTableModel(getDataFromResultSet(rs), getColumnIdentifiersFromResultSet(rs));
        return new DefaultTableModel();
    }
    
    public static Vector<Vector> getDataFromResultSet(ResultSet rs) throws SQLException{
        Vector<Vector> tableData = new Vector<>();
        while (rs.next())
            tableData.add(getRowDataFromResultSet(rs));
        return tableData;
    }
    
    public static Vector<Object> getRowDataFromResultSet(ResultSet rs) throws SQLException{
        Vector<Object> row = new Vector<>(rs.getMetaData().getColumnCount());
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) 
            row.add(rs.getObject((i+1)));
        return row;
    }
    
    public static Vector<String> getColumnIdentifiersFromResultSet(ResultSet rs) throws SQLException{
        if(rs != null && rs.getMetaData().getColumnCount() > 0){
            ResultSetMetaData rsm = rs.getMetaData();
            Vector<String> columnIdentifiers = new Vector<>(rsm.getColumnCount());
            for (int i = 0; i < rsm.getColumnCount(); i++) 
                columnIdentifiers.add(rsm.getColumnLabel((i+1)));
            return columnIdentifiers;
        }
        return null;
    }
    
    public static void printResult(ResultSet rs) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(rs != null && rs.getMetaData().getColumnCount() > 0)
            JOptionPane.showMessageDialog(null, new JTable(getDefaultTableModelFromResultSet(rs)), "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }
}
