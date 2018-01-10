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

import javax.swing.table.DefaultTableModel;
import sa.model.to.AsistenciaTO;
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
    
    public DefaultTableModel getDTM(String query){
        return getDefaultTableModel(query);
    }

    public void createAsistencia(AsistenciaTO asistencia) {
        if(create(asistencia))
            SAInputOutput.showInformationMessage(String.format("Se registro el alumno con NC %s en la actividad %s", asistencia.getAlumnoFk().getNumeroControl(), asistencia.getActividadFk().getNombre()));
        else
            SAInputOutput.showErrorMessage("No se pudo registrar");
    }

    public void updateAsistencia(AsistenciaTO asistencia) {
        if(update(asistencia))
            SAInputOutput.showInformationMessage("Asistencia modificada");
        else
            SAInputOutput.showErrorMessage("No se pudo modificar la asistencia");
    }
}
