/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.dao;

import javax.swing.table.DefaultTableModel;
import sa.model.to.ActividadTO;

/**
 *
 * @author dave
 */
public class ActividadDAO extends GenericDAO<ActividadTO>{
    
    private static ActividadDAO instance;

    private ActividadDAO() {
    }

    public static ActividadDAO getInstance() {
        return instance != null ? instance : (instance = new ActividadDAO());
    }
    
    public ActividadTO getActividad(String id){
        return getActividad(new ActividadTO(id, null, null, null, 0, null));
    }
    
    public ActividadTO getActividad(ActividadTO actividad){
        return read(actividad, data -> new ActividadTO(data));
    }
    
    public boolean actividadExists(ActividadTO actividad){
        return getActividad(actividad) != null;
    }
    
    public boolean createActividad(ActividadTO actividad){
        return create(actividad);
    }

    public boolean updateActividad(ActividadTO actividad) {
        return update(actividad);
    }

    public boolean deleteActividad(ActividadTO actividad) {
        return delete(actividad);
    }
    
    public DefaultTableModel getDTM(String query){
        return getDefaultTableModel(query);
    }
}
