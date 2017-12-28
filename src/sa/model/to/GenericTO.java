/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;


/**
 *
 * @author dave
 */
public interface GenericTO {
    
    public abstract String getInsertSQL();
    public abstract String getUpdateSQL();
    public abstract String getQuerySQL();
    public abstract String getDeleteSQL();
    public abstract boolean isValid();
}
