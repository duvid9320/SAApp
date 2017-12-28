/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.HashMap;
import sa.model.dao.ActividadDAO;
import sa.model.dao.CarreraDAO;

/**
 *
 * @author Dave
 */
public class CarreraRequeridaTO implements GenericTO{
    private ActividadTO actividadFk;
    private CarreraTO carreraFk;

    public CarreraRequeridaTO(HashMap<String, Object> data) {
        this(
                ActividadDAO.getInstance().getActividad(String.valueOf(data.get("ActividadFk"))), 
                CarreraDAO.getInstance().getCarrera(String.valueOf(data.get("CarreraFk")))
        );
    }

    public CarreraRequeridaTO(ActividadTO actividadFk, CarreraTO carreraFk) {
        this.actividadFk = actividadFk;
        this.carreraFk = carreraFk;
    }

    public ActividadTO getActividadFk() {
        return actividadFk;
    }

    public void setActividadFk(ActividadTO actividadFk) {
        this.actividadFk = actividadFk;
    }

    public CarreraTO getCarreraFk() {
        return carreraFk;
    }

    public void setCarreraFk(CarreraTO carreraFk) {
        this.carreraFk = carreraFk;
    }

    @Override
    public String getInsertSQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateSQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQuerySQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeleteSQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValid() {
        return actividadFk.isValid() && carreraFk.isValid();
    }
}
