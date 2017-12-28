/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import sa.connection.MySQLManager;
import sa.model.to.GenericTO;
import sa.model.to.GenericFactoryTO;
import sa.utils.TableManager;

/**
 *
 * @author dave
 */
public abstract class GenericDAO<T extends GenericTO> {
    
    protected boolean create(T to){
        try {
            return MySQLManager.getInstance().executeUpdate(to.getInsertSQL());
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    protected boolean update(T to){
        try {
            return MySQLManager.getInstance().executeUpdate(to.getUpdateSQL());
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    protected T read (T to, GenericFactoryTO<T> f){
        try {
            return MySQLManager.getInstance().getDataListFromQuery(to.getQuerySQL())
                    .stream()
                    .filter(r -> r != null && !r.isEmpty())
                    .map(r -> f.newTO(r))
                    .findFirst()
                    .orElse(null);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    protected List<T> readList(String query, GenericFactoryTO<T> f){
        try {
            return MySQLManager.getInstance().getDataListFromQuery(query)
                    .stream()
                    .filter(r -> r != null && !r.isEmpty())
                    .map(r -> f.newTO(r))
                    .collect(Collectors.toList());
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.emptyList();
    }
    
    protected boolean delete(T to){
        try {
            return MySQLManager.getInstance().executeUpdate(to.getDeleteSQL());
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    protected DefaultTableModel getDefaultTableModel(String query){
        try {
            return TableManager.getDefaultTableModelFromResultSet(
                    MySQLManager.getInstance().getResultSetFromQuery(query)
            );
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DefaultTableModel();
    }
}
