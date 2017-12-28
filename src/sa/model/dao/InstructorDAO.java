/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.dao;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sa.model.to.InstructorTO;
import sa.utils.SAOutput;

/**
 *
 * @author dave
 */
public class InstructorDAO extends GenericDAO<InstructorTO>{
    private static InstructorDAO instance;

    private InstructorDAO() {
    }

    public static InstructorDAO getInstance() {
        return instance != null ? instance : (instance = new InstructorDAO());
    }
    
    public InstructorTO getInstructor(String id){
        return getInstructor(new InstructorTO(id, null, null, null));
    }
    
    public InstructorTO getInstructor(InstructorTO instructor){
        return read(instructor, data -> new InstructorTO(data));
    }
    
    public boolean instructorExists(InstructorTO instructor){
        return getInstructor(instructor) != null;
    }
    
    public boolean createInstructor(InstructorTO instructor) {
        if(instructorExists(instructor))
            SAOutput.showErrorMessage("El instructor ya existe");
        else 
            return create(instructor);
        return false;
    }

    public boolean updateInstructor(InstructorTO instructor) {
        if(!instructorExists(instructor))
            SAOutput.showErrorMessage("El instructor no existe, no se puede modificar");
        else
            return update(instructor);
        return false;
    }

    public boolean deleteInstructor(InstructorTO instructor) {
        if(!instructorExists(instructor))
            SAOutput.showErrorMessage("El instructor no existe");
        else 
            return delete(instructor);
        return false;
    }

    public DefaultTableModel getDTM(String query) {
        return getDefaultTableModel(query);
    }
    
}
