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

import java.util.Date;
import javax.swing.table.DefaultTableModel;
import sa.model.to.ActividadTO;
import sa.model.to.AlumnoTO;
import sa.model.to.AsistenciaTO;
import sa.model.to.RegistroTO;
import sa.utils.SAInputOutput;

/**
 *
 * @author David Rodríguez <duvid9320@gmail.com>
 */
public class AsistenciaDAO extends GenericDAO<AsistenciaTO>{
    private static AsistenciaDAO instance;

    private AsistenciaDAO() {
    }

    public static AsistenciaDAO getInstance() {
        return instance == null ? (instance = new AsistenciaDAO()) : instance;
    }
    
    public AsistenciaTO getAsistencia(AsistenciaTO asistencia){
        return read(asistencia, data -> new AsistenciaTO(data));
    }
    
    public AsistenciaTO getAsistencia(AlumnoTO alumno, ActividadTO actividad){
        return getAsistencia(new AsistenciaTO(0, actividad, alumno, new Date(), "", ""));
    }
    
    public DefaultTableModel getDTM(String query){
        return getDefaultTableModel(query);
    }
    
    public boolean createAsistencia(RegistroTO registro){
        return createAsistencia(new AsistenciaTO(registro));
    }

    public boolean createAsistencia(AsistenciaTO asistencia) {
        return create(asistencia);
    }

    public void updateAsistencia(AsistenciaTO asistencia) {
        if(update(asistencia))
            SAInputOutput.showInformationMessage(String.format("Salida de alumno con NC %s", asistencia.getAlumnoFk().getNumeroControl()));
        else
            SAInputOutput.showErrorMessage("No se pudo modificar la asistencia");
    }
}
