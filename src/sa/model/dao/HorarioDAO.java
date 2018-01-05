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
import sa.model.to.HorarioTO;
import sa.utils.SAInputOutput;

/**
 *
 * @author dave
 */
public class HorarioDAO extends GenericDAO<HorarioTO>{
    private static HorarioDAO instance;

    private HorarioDAO() {
    }

    public static HorarioDAO getInstance() {
        return instance != null ? instance : (instance = new HorarioDAO());
    }

    public boolean horarioExists(HorarioTO horario) {
        return getHorario(horario) != null;
    }
    
    public HorarioTO getHorario(HorarioTO horario){
        return read(horario, data -> new HorarioTO(data));
    }

    public void createHorario(HorarioTO horario) {
        if(create(horario))
            SAInputOutput.showInformationMessage("El horario se registró correctamente");
        else
            SAInputOutput.showErrorMessage("El horario no se pudo registrar");
    }

    public DefaultTableModel getDTM(String query) {
        return getDefaultTableModel(query);
    }

    public HorarioTO getHorario(int id) {
        return getHorario(new HorarioTO(id, null, null, null, null, null));
    }

    public void deleteHorario(HorarioTO horario) {
        if(!SAInputOutput.showDeleteConfirmation("Seguro que deseas eliminar este horario de la actividad "+horario.getActividadFk().getNombre()))
            SAInputOutput.showInformationMessage("Operación cancelada por el usuario");
        else if(delete(horario))
            SAInputOutput.showInformationMessage("El horario se eliminó correctamente");
        else 
            SAInputOutput.showErrorMessage("El horario no se pudo eliminar");
    }

    public void updateHorario(HorarioTO horario) {
        if(update(horario))
            SAInputOutput.showInformationMessage("El horario se modificó correctamente");
        else
            SAInputOutput.showErrorMessage("El horario no se pudo modificar");
    }
}

