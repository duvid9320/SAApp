/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
