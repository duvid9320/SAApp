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

    public AsistenciaTO() {
    }

    public AsistenciaTO(HashMap<String, Object> data) {
        this(
                Integer.parseInt(String.valueOf(data.get("IdAsistencia"))), 
                ActividadDAO.getInstance().getActividad(String.valueOf(data.get("ActividadFk"))), 
                AlumnoDAO.getInstance().getAlumno(String.valueOf(data.get("AlumnoFk"))), 
                SAUtils.getDateFromString(String.valueOf(data.get("Fecha"))), 
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

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    
    
    @Override
    public String getInsertSQL() {
        return String.format(
                "INSERT INTO Asistencia (ActividadFk, AlumnoFk, Fecha, HoraLlegada)"
                                + "VALUES ('%s', '%s', '%s', '%s')", 
                actividadFk.getIdActividad(),
                alumnoFk.getNumeroControl(),
                SAUtils.getFormattedDate((fecha = new Date())),
                (horaLlegada = SAUtils.getFormattedTime(fecha))
        );
    }

    @Override
    public String getUpdateSQL() {
        if(idAsistencia != 0)
            return String.format(
                    "UPDATE Asistencia "
                        + "SET HoraSalida = '%s' "
                        + "WHERE IdAsistencia = %d ",
                    idAsistencia
            );
        return String.format(
                    "UPDATE Asistencia "
                        + "SET HoraSalida = '%s' "
                        + "WHERE ActividadFk = '%s' AND AlumnoFk = '%s' AND Fecha = '%s' ",
                    actividadFk.getIdActividad(),
                    alumnoFk.getNumeroControl(),
                    SAUtils.getFormattedDate(fecha)
        );
    }

    @Override
    public String getQuerySQL() {
        if(idAsistencia != 0)
            return String.format(
                    "SELECT * FROM Asistencia "
                            + "WHERE IdAsistencia = %d", 
                    idAsistencia
            );
        return String.format(
                "SELECT * FROM Asistencia "
                        + "WHERE ActividadFk = '%s' AND AlumnoFk = '%s' AND Fecha = '%s'", 
                actividadFk.getIdActividad(),
                alumnoFk.getNumeroControl(),
                fecha != null ? SAUtils.getFormattedDate(fecha) : ""
        );
    }

    @Override
    public String getDeleteSQL() {
        if(idAsistencia != 0)
            return String.format(
                    "DELETE FROM Asistencia WHERE IdAsistencia = %d", 
                    idAsistencia
            );
        return String.format(
                "DELETE FROM Asistencia "
                        + "WHERE ActividadFk = '%s' AND AlumnoFk = '%s' AND Fecha = '%s' ", 
                actividadFk.getIdActividad(),
                alumnoFk.getNumeroControl(),
                SAUtils.getFormattedDate(fecha)
        );
    }

    @Override
    public boolean isValid() {
        return actividadFk.isValid() && alumnoFk.isValid() && fecha != null && SAUtils.isValidString(horaLlegada);
    }
}
