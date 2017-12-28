/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
