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
                SAUtils.getDateFromString(String.valueOf(data.get("Fecha"))),
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
