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

