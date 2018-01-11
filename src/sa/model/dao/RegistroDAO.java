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
import sa.model.to.ActividadTO;
import sa.model.to.AlumnoTO;
import sa.model.to.RegistroTO;
import sa.utils.SAInputOutput;

/**
 *
 * @author David Rodríguez <duvid9320@gmail.com>
 */
public class RegistroDAO extends GenericDAO<RegistroTO>{
    private static RegistroDAO instance;

    private RegistroDAO() {
    }

    public static RegistroDAO getInstance() {
        return instance == null ? (instance = new RegistroDAO()) : instance;
    }
    
    public DefaultTableModel getDTM(String query){
        return getDefaultTableModel(query);
    }

    public RegistroTO getRegistro(RegistroTO registroView) {
        return read(registroView, data -> new RegistroTO(data));
    }
    
    public RegistroTO getRegistro(AlumnoTO alumno, ActividadTO actividad){
        return getRegistro(new RegistroTO(actividad, alumno, 0));
    }
    
    public void createRegistro(RegistroTO registro){
        if(create(registro))
            SAInputOutput.showInformationMessage("Registro exitoso");
        else 
            SAInputOutput.showErrorMessage("No se pudo registrar");
    }

    public void deleteRegistro(RegistroTO registro) {
        if(!SAInputOutput.showDeleteConfirmation(String.format("Eliminar Registro de la Actividad %s del Alumno con NC %s", 
                                                        registro.getActividadFk().getNombre(),
                                                        registro.getAlumnoFk().getNumeroControl())
                                                )
                )
            SAInputOutput.showInformationMessage("Operación cancelada por el usuario");
        else if(delete(registro))
            SAInputOutput.showInformationMessage("El registro se eliminó correctamente");
        else
            SAInputOutput.showErrorMessage("No se pudo eliminar");
            
    }

    public void updateRegistro(RegistroTO registro) {
        if(update(registro))
            SAInputOutput.showInformationMessage("Asistencia confirmada");
        else
            SAInputOutput.showErrorMessage("No se pudo confirmar la asistencia");
    }
}
