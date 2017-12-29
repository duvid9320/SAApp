/* 
 * The MIT License
 *
 * Copyright 2017 David Rodríguez <duvid9320@gmail.com>.
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
package sa.controller.impl;

import javax.swing.JTable;
import sa.controller.HorarioController;
import sa.model.dao.HorarioDAO;
import sa.model.to.ActividadTO;
import sa.model.to.HorarioTO;
import sa.utils.SAOutput;

/**
 *
 * @author dave
 */
public class HorarioControllerImpl implements HorarioController{
    
    @Override
    public void createHorario(HorarioTO horario){
        if(!horario.isValid())
            SAOutput.showErrorMessage("La información del horario no es válida");
        else if(HorarioDAO.getInstance().horarioExists(horario))
            SAOutput.showErrorMessage("El horario ya existe");
        else if (HorarioDAO.getInstance().createHorario(horario))
            SAOutput.showInformationMessage("El horario se creo correctamente");
        else 
            SAOutput.showErrorMessage("El horario no se pudo crear");
    }

    public void showAll(ActividadTO actividad, JTable tableHorarios) {
        if(actividad != null && actividad.isValid())
            tableHorarios.setModel(HorarioDAO.getInstance().getDTM(actividad));
   }

    void deleteHorario(HorarioTO horario) {
        if(!horario.isValid())
            SAOutput.showErrorMessage("Debes cargar el horario desde la tabla");
        else if(!HorarioDAO.getInstance().horarioExists(horario))
            SAOutput.showErrorMessage("El horario no existe");
        else if(HorarioDAO.getInstance().deleteHorario(horario))
            SAOutput.showInformationMessage("El horario se eliminó correctamente");
        else 
            SAOutput.showErrorMessage("El horario no se pudo eliminar");
    }

    void updateHorario(HorarioTO horario) {
        if(!horario.isValid())
            SAOutput.showErrorMessage("Debes cargar el horario desde la tabla");
        else if (!HorarioDAO.getInstance().horarioExists(horario))
            SAOutput.showErrorMessage("El horario no existe");
        else if(HorarioDAO.getInstance().updateHorario(horario))
            SAOutput.showInformationMessage("El horario se modificó correctamente");
        else
            SAOutput.showErrorMessage("El horario no se pudo modificar");
    }
}
