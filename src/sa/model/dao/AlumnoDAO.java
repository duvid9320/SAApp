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

import java.util.List;
import javax.swing.table.DefaultTableModel;
import sa.model.to.AlumnoTO;
import sa.utils.SAInputOutput;

/**
 *
 * @author dave
 */
public class AlumnoDAO extends GenericDAO<AlumnoTO>{
    private static AlumnoDAO instance;

    private AlumnoDAO() {
    }
    
    public static AlumnoDAO getInstance() {
        return instance != null ? instance : (instance = new AlumnoDAO());
    }
    
    public AlumnoTO getAlumno(AlumnoTO alumno){
        return read(alumno, data -> new AlumnoTO(data));
    }
    
    public AlumnoTO getAlumno(String nc){
        return getAlumno(new AlumnoTO(nc, null, null, null, 0, null, null));
    }
    
    public boolean alumnoExists(AlumnoTO alumno){
        return getAlumno(alumno) != null;
    }
    
    public void insertAlumno(AlumnoTO alumno){
        if(create(alumno))
            SAInputOutput.showInformationMessage("El alumno se registro correctamente");
        else
            SAInputOutput.showErrorMessage("El alumno no se pudo registrar");
    }
    
    public void updateAlumno(AlumnoTO alumno){
        if(update(alumno))
            SAInputOutput.showInformationMessage("El alumno se modificó");
        else
            SAInputOutput.showErrorMessage("El alumno no se pudo modificar");
    }
    
    public void deleteAlumno(AlumnoTO alumno){
        if(!SAInputOutput.showDeleteConfirmation("Desea eliminar el alumno con NC "+alumno.getNumeroControl()))
            SAInputOutput.showInformationMessage("Eliminación cancelada por el usuario");
        else if(delete(alumno))
            SAInputOutput.showInformationMessage("El alumno se eliminó");
        else
            SAInputOutput.showErrorMessage("El alumno no se pudo eliminar");
    }
    
    public List<AlumnoTO> getAllAlumnos(){
        return readList("SELECT * FROM Alumno", data -> new AlumnoTO(data));
    }
    
    public DefaultTableModel getDTM(String query){
        return getDefaultTableModel(query);
    }

    public void deleteAlumno(String nc) {
        deleteAlumno(getAlumno(nc));
    }
}
