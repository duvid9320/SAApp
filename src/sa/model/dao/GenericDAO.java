/* 
 * The MIT License
 *
 * Copyright 2018 David Rodríguez <duvid9320@gmail.com>.
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
