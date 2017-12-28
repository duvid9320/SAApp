/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.Date;
import java.util.HashMap;
import sa.model.dao.ActividadDAO;
import sa.utils.SAUtils;

/**
 *
 * @author Dave
 */
public class HorarioTO implements GenericTO{
    private int idHorario;
    private ActividadTO actividadFk;
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private String lugar;

    public HorarioTO(HashMap<String, Object> data) {
        this(
                Integer.parseInt(String.valueOf(data.get("IdHorario"))), 
                ActividadDAO.getInstance().getActividad(String.valueOf(data.get("ActividadFk"))),
                SAUtils.getDateFromMySQLDateString(String.valueOf(data.get("Fecha"))),
                String.valueOf(data.get("HoraInicio")), 
                String.valueOf(data.get("HoraFin")), 
                String.valueOf(data.get("Lugar"))
        );
    }

    public HorarioTO(int idHorario, ActividadTO actividadFk, Date fecha, String horaInicio, String horaFin, String lugar) {
        this.idHorario = idHorario;
        this.actividadFk = actividadFk;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lugar = lugar;
    }
    
    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public ActividadTO getActividadFk() {
        return actividadFk;
    }

    public void setActividadFk(ActividadTO actividadFk) {
        this.actividadFk = actividadFk;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String getInsertSQL() {
        return String.format("INSERT INTO Horario (ActividadFk, Fecha, HoraInicio, HoraFin, Lugar) "
                + "VALUES ('%s', '%s', '%s', '%s', '%s')",
                actividadFk.getIdActividad(),
                SAUtils.getFormattedDate(fecha),
                horaInicio,
                horaFin,
                lugar
        );
    }

    @Override
    public String getUpdateSQL() {
        return String.format(
                "UPDATE Horario SET Fecha = '%s', HoraInicio = '%s', HoraFin = '%s', Lugar = '%s' WHERE IdHorario = %d", 
                SAUtils.getFormattedDate(fecha),
                horaInicio,
                horaFin,
                lugar,
                idHorario
        );
    }

    @Override
    public String getQuerySQL() {
        if(idHorario > 0)
            return String.format("SELECT * FROM Horario WHERE IdHorario = %d", idHorario);
        return String.format("SELECT * FROM Horario WHERE ActividadFk = '%s' "
                + "AND Fecha = '%s' AND HoraInicio = '%s' AND HoraFin = '%s'", 
                actividadFk.getIdActividad(),
                fecha != null ? SAUtils.getFormattedDate(fecha) : "",
                horaInicio,
                horaFin
        );
    }

    @Override
    public String getDeleteSQL() {
        return String.format(
                "DELETE FROM Horario WHERE IdHorario = %d", 
                idHorario
        );
    }

    @Override
    public boolean isValid() {
        return SAUtils.stringsAreValid(horaInicio, horaFin, lugar) && actividadFk.isValid() && fecha != null;
    }
}
