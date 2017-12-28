package sa.controller.impl;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import sa.connection.MySQLManager;
import sa.controller.InstructorController;
import sa.model.dao.InstructorDAO;
import sa.model.to.InstructorTO;
import sa.utils.SAOutput;
import sa.utils.TableManager;
import sa.view.InstructorView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dave
 */
public class InstructorControllerImpl implements InstructorController{
    private InstructorView v;

    public InstructorControllerImpl() {
        v = new InstructorView(this);
        v.setVisible(true);
    }

    @Override
    public void createInstructor(InstructorTO instructor) {
        if(!instructor.isValid())
            SAOutput.showErrorMessage("La información del instructor no es valida");
        else if(InstructorDAO.getInstance().createInstructor(instructor))
            SAOutput.showInformationMessage("El instructor se registrar correctamente");
        else 
            SAOutput.showErrorMessage("El instructor no se pudo registrar");
    }

    @Override
    public void updateInstructor(InstructorTO instructor) {
        if(!instructor.isValid())
            SAOutput.showErrorMessage("La información del instructor no es valida");
        else if(InstructorDAO.getInstance().updateInstructor(instructor))
            SAOutput.showInformationMessage("El instructor se modifico correctamente");
        else
            SAOutput.showErrorMessage("El instructor no se pudo modificar");
    }

    @Override
    public void deleteInstructor(InstructorTO instructor) {
        if(!instructor.isValid())
            SAOutput.showErrorMessage("Carga el instructor desde la tabla");
        else if(InstructorDAO.getInstance().deleteInstructor(instructor))
            SAOutput.showInformationMessage("El instructor se elimino correctamente");
        else 
            SAOutput.showErrorMessage("El instructor no se pudo eliminar");
    }

    @Override
    public void buscarTodos(JTable jTAResults) {
        try {
            jTAResults.setModel(
                    TableManager.getDefaultTableModelFromResultSet(
                            MySQLManager.getInstance().getResultSetFromQuery(
                                    "SELECT * FROM Instructor"
                            )
                    )
            );
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(InstructorControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public InstructorTO getInstructor(String id) {
        return id != null ? InstructorDAO.getInstance().getInstructor(id) : null;
    }
    
}
