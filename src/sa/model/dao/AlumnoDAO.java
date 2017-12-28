/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.dao;

import java.util.List;
import sa.model.to.AlumnoTO;
import sa.utils.SAOutput;

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
    
    public boolean insertAlumno(AlumnoTO alumno){
        if(alumnoExists(alumno))
            SAOutput.showErrorMessage("El alumno ya existe");
        else
            return create(alumno);
        return false;
    }
    
    public boolean updateAlumno(AlumnoTO alumno){
        if(!alumnoExists(alumno))
            SAOutput.showErrorMessage("El alumno no existe, no se puede modificar");
        else
            return update(alumno);
        return false;
    }
    
    public boolean deleteAlumno(AlumnoTO alumno){
        if(!alumnoExists(alumno))
            SAOutput.showErrorMessage("El alumno no existe");
        else 
            return delete(alumno);
        return false;
    }
    
    public List<AlumnoTO> getAllAlumnos(){
        return readList("SELECT * FROM Alumno", data -> new AlumnoTO(data));
    }
}
