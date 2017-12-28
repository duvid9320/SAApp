/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.HashMap;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;

/**
 *
 * @author Dave
 */
public class RegistroTO implements GenericTO{
    
    private ActividadTO actividadFk;
    private AlumnoTO alumnoFk;
    private int horasAsistidas;

    public RegistroTO(HashMap<String, Object> data) {
        this(
                ActividadDAO.getInstance().getActividad(String.valueOf(data.get("ActividadFk"))), 
                AlumnoDAO.getInstance().getAlumno(String.valueOf(data.get("AlumnoFk"))), 
                Integer.parseInt(String.valueOf(data.get("HorasAsistidas")))
        );
    }

    public RegistroTO(ActividadTO actividadFk, AlumnoTO alumnoFk, int horasAsistidas) {
        this.actividadFk = actividadFk;
        this.alumnoFk = alumnoFk;
        this.horasAsistidas = horasAsistidas;
    }

    public ActividadTO getActividadFk() {
        return actividadFk;
    }

    public void setActividadFk(ActividadTO actividadFk) {
        this.actividadFk = actividadFk;
    }

    public AlumnoTO getAlumnoFk() {
        return alumnoFk;
    }

    public void setAlumnoFk(AlumnoTO alumnoFk) {
        this.alumnoFk = alumnoFk;
    }

    public int getHorasAsistidas() {
        return horasAsistidas;
    }

    public void setHorasAsistidas(int horasAsistidas) {
        this.horasAsistidas = horasAsistidas;
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
        return actividadFk.isValid() && alumnoFk.isValid() && horasAsistidas != -1;
    }
}
