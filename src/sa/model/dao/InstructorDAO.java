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
import sa.model.to.InstructorTO;
import sa.utils.SAInputOutput;

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
    
    public void createInstructor(InstructorTO instructor) {
        if(create(instructor))
            SAInputOutput.showInformationMessage("El instructor se registro");
        else
            SAInputOutput.showErrorMessage("El instructor no se pudo registrar");
    }

    public void updateInstructor(InstructorTO instructor) {
        if (update(instructor)) 
            SAInputOutput.showInformationMessage("El instructor se modificó");
        else
            SAInputOutput.showErrorMessage("El instructor no se pudo modificar");
    }

    public void deleteInstructor(InstructorTO instructor) {
        if(!SAInputOutput.showDeleteConfirmation("Desea eliminar al instructor "+instructor.getNombres()+" "+instructor.getApellidos()))
            SAInputOutput.showInformationMessage("Eliminación cancelada");
        else if(delete(instructor))
            SAInputOutput.showInformationMessage("El instructor se eliminó");
        else
            SAInputOutput.showErrorMessage("El instructor no se pudo eliminar");
    }
    
    public void deleteInstructor(String id){
        deleteInstructor(getInstructor(id));
    }

    public DefaultTableModel getDTM(String query) {
        return getDefaultTableModel(query);
    }
    
}
