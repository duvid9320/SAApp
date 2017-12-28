/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.controller.impl;

import javax.swing.JTable;
import sa.controller.ActividadController;
import sa.model.dao.ActividadDAO;
import sa.model.dao.HorarioDAO;
import sa.model.dao.InstructorDAO;
import sa.model.to.ActividadTO;
import sa.model.to.HorarioTO;
import sa.model.to.InstructorTO;
import sa.utils.SAOutput;
import sa.view.ActividadView;

/**
 *
 * @author dave
 */
public class ActividadControllerImpl implements ActividadController{
    private final ActividadView v;
    private final HorarioControllerImpl horarioController;

    public ActividadControllerImpl() {
        v = new ActividadView(this);
        v.setVisible(true);
        horarioController = new HorarioControllerImpl();
    }

    @Override
    public void getAllInstructores(JTable jTRInstructores) {
        jTRInstructores.setModel(InstructorDAO.getInstance().getDTM("SELECT * FROM Instructor"));
    }

    @Override
    public InstructorTO getInstructor(String id) {
        return InstructorDAO.getInstance().getInstructor(id);
    }

    @Override
    public void createActividad(ActividadTO actividad) {
        actividad.setInstructorFk(InstructorDAO.getInstance().getInstructor(actividad.getInstructorFk()));
        if(actividad.getInstructorFk() == null || !actividad.getInstructorFk().isValid())
            SAOutput.showErrorMessage("Instructor no es valido");
        else if(!actividad.isValid())
            SAOutput.showErrorMessage("La información de la actividad no es válida.");
        else if(ActividadDAO.getInstance().createActividad(actividad))
            SAOutput.showInformationMessage("La actividad se creo correctamente");
        else
            SAOutput.showErrorMessage("La actividad no se pudo crear");
    }

    @Override
    public void updateActividad(ActividadTO actividad) {
        actividad.setInstructorFk(InstructorDAO.getInstance().getInstructor(actividad.getInstructorFk()));
        if(!actividad.isValid())
            SAOutput.showErrorMessage("Carga la actividad desde la tabla");
        else if(ActividadDAO.getInstance().updateActividad(actividad))
            SAOutput.showInformationMessage("Se modifico la actividad");
        else
            SAOutput.showErrorMessage("La actividad no se pudo modificar");
    }

    @Override
    public void deleteActividad(ActividadTO actividad) {
        actividad.setInstructorFk(InstructorDAO.getInstance().getInstructor(actividad.getInstructorFk()));
        if(!actividad.isValid())
            SAOutput.showErrorMessage("Carga la actividad desde la tabla");
        else if(ActividadDAO.getInstance().deleteActividad(actividad))
            SAOutput.showInformationMessage("La actividad se eliminó");
        else
            SAOutput.showErrorMessage("La actividad no se pudo eliminar");
        
    }

    @Override
    public void showAllActividades(JTable jTQActividades) {
        jTQActividades.setModel(
            ActividadDAO.getInstance().getDTM(
                String.format(
                    "SELECT %s, %s, %s, %s, %s, %s FROM Actividad "
                            + "INNER JOIN Instructor ON Instructor.IdInstructor = Actividad.InstructorFk", 
                    "IdActividad AS Id",
                    "Nombre",
                    "Tipo AS 'Tipo Actividad'",
                    "Descripcion",
                    "Horas",
                    "Instructor.Nombres AS 'Nombre Instructor'"
                )
            )
        );
    }

    @Override
    public ActividadTO getActividad(String actividad) {
        return ActividadDAO.getInstance().getActividad(actividad);
    }

    @Override
    public void createHorario(HorarioTO horario) {
        horario.setActividadFk(ActividadDAO.getInstance().getActividad(horario.getActividadFk().getIdActividad()));
        horarioController.createHorario(horario);
    }

    @Override
    public void showAllHorarios(ActividadTO actividad, JTable tableHorarios) {
        horarioController.showAll(ActividadDAO.getInstance().getActividad(actividad), tableHorarios);
    }

    @Override
    public HorarioTO getHorario(String id) {
        return id != null && !id.trim().isEmpty() ? HorarioDAO.getInstance().getHorario(Integer.parseInt(id)) : null;
    }

    @Override
    public void deleteHorario(HorarioTO horario) {
        horario.setActividadFk(ActividadDAO.getInstance().getActividad(horario.getActividadFk()));
        horarioController.deleteHorario(horario);
    }

    @Override
    public void updateHorario(HorarioTO horario) {
        horario.setActividadFk(ActividadDAO.getInstance().getActividad(horario.getActividadFk()));
        horarioController.updateHorario(horario);
    }
}

