/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.controller;

import javax.swing.JTable;
import sa.model.to.ActividadTO;
import sa.model.to.HorarioTO;
import sa.model.to.InstructorTO;

/**
 *
 * @author dave
 */
public interface ActividadController {

    public void getAllInstructores(JTable jTRInstructores);
    
    public InstructorTO getInstructor(String id);

    public void createActividad(ActividadTO actividad);

    public void updateActividad(ActividadTO actividad);

    public void deleteActividad(ActividadTO actividad);

    public void showAllActividades(JTable jTQActividades);

    public ActividadTO getActividad(String actividad);

    public void createHorario(HorarioTO horario);

    public void showAllHorarios(ActividadTO actividad, JTable tableHorarios);

    public HorarioTO getHorario(String id);

    public void deleteHorario(HorarioTO horarioFromView);

    public void updateHorario(HorarioTO horario);
    
}
