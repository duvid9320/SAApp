/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.dao;

import javax.swing.table.DefaultTableModel;
import sa.model.to.ActividadTO;
import sa.model.to.HorarioTO;

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

    public boolean createHorario(HorarioTO horario) {
        return create(horario);
    }

    public DefaultTableModel getDTM(ActividadTO actividad) {
        return getDefaultTableModel(actividad.getQueryHorariosSQL());
    }

    public HorarioTO getHorario(int id) {
        return getHorario(new HorarioTO(id, null, null, null, null, null));
    }

    public boolean deleteHorario(HorarioTO horario) {
        return delete(horario);
    }

    public boolean updateHorario(HorarioTO horario) {
        return update(horario);
    }
}

