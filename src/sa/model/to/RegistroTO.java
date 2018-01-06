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

    public RegistroTO() {
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
        return String.format(
                "INSERT INTO Registro VALUES ('%s', '%s', %d)", 
                actividadFk.getIdActividad(),
                alumnoFk.getNumeroControl(),
                0
        );
    }

    @Override
    public String getUpdateSQL() {
        return String.format(
                "UPDATE Registro SET HorasAsistidas = %d WHERE ActividadFk = '%s' AND AlumnoFk = '%s'", 
                horasAsistidas,
                actividadFk.getIdActividad(),
                alumnoFk.getNumeroControl()
        );
    }

    @Override
    public String getQuerySQL() {
        return String.format(
                "SELECT * FROM Registro WHERE ActividadFk = '%s' AND AlumnoFk = '%s'", 
                actividadFk != null ? actividadFk.getIdActividad() : "",
                alumnoFk != null ? alumnoFk.getNumeroControl() : ""
        );
    }

    @Override
    public String getDeleteSQL() {
        return String.format(
                "DELETE FROM Registro WHERE ActividadFk = '%s' AND AlumnoFk = '%s'", 
                actividadFk != null ? actividadFk.getIdActividad() : "",
                alumnoFk != null ? alumnoFk.getNumeroControl() : ""
        );
    }
    
    public String getIdActividadesRegistradas(){
        return String.format(
                "SELECT IdActividad "
                        + "FROM Registro AS r INNER JOIN Actividad AS ac ON r.ActividadFk = ac.IdActividad INNER JOIN Alumno AS al ON r.AlumnoFk = al.NumeroControl "
                        + "WHERE AlumnoFk = '%s'", 
                alumnoFk != null ? alumnoFk.getNumeroControl() : ""
        );
    }
    
    public String getActividadesAlumnoQuery(){
        String sql = String.format(
                "SELECT ac.IdActividad, al.NumeroControl, ac.Nombre, ac.Tipo, ac.Horas, CONCAT(al.Nombres, ' ', al.ApellidoPaterno, ' ', al.ApellidoMaterno) AS 'Alumno'"
                        + " FROM Registro as r INNER JOIN Actividad AS ac ON r.ActividadFk = ac.IdActividad INNER JOIN Alumno AS al ON r.AlumnoFk = al.NumeroControl "
                        + "WHERE r.AlumnoFk = '%s'", 
                alumnoFk != null ? alumnoFk.getNumeroControl() : ""
        );
        System.out.println(sql);
        return sql;
    }

    @Override
    public boolean isValid() {
        return actividadFk != null && actividadFk.isValid() && alumnoFk != null && alumnoFk.isValid() && horasAsistidas != -1;
    }
}
