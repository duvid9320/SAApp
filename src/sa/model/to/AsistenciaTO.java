/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.Date;
import java.util.HashMap;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;
import sa.utils.SAUtils;

/**
 *
 * @author dave
 */
public class AsistenciaTO implements GenericTO{
    private int idAsistencia;
    private ActividadTO actividadFk;
    private AlumnoTO alumnoFk;
    private Date fecha;
    private String horaLlegada;
    private String horaSalida;

    public AsistenciaTO(HashMap<String, Object> data) {
        this(
                Integer.parseInt(String.valueOf(data.get("IdAsistencia"))), 
                ActividadDAO.getInstance().getActividad(String.valueOf(data.get("ActividadFk"))), 
                AlumnoDAO.getInstance().getAlumno(String.valueOf(data.get("AlumnoFk"))), 
                new Date(String.valueOf(data.get("Fecha"))), 
                String.valueOf(data.get("HoraLlegada")), 
                String.valueOf(data.get("HoraSalida"))
        );
    }

    public AsistenciaTO(int idAsistencia, ActividadTO actividadFk, AlumnoTO alumnoFk, Date fecha, String horaLlegada, String horaSalida) {
        this.idAsistencia = idAsistencia;
        this.actividadFk = actividadFk;
        this.alumnoFk = alumnoFk;
        this.fecha = fecha;
        this.horaLlegada = horaLlegada;
        this.horaSalida = horaSalida;
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
        return actividadFk.isValid() && alumnoFk.isValid() && fecha != null && SAUtils.isValidString(horaLlegada);
    }
}
