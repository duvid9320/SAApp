/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.controller;

import javax.swing.JTable;
import sa.model.to.InstructorTO;

/**
 *
 * @author dave
 */
public interface InstructorController {

    public void createInstructor(InstructorTO instructor);

    public void updateInstructor(InstructorTO instructor);

    public void deleteInstructor(InstructorTO instructor);

    public void buscarTodos(JTable jTAResults);
    
    public InstructorTO getInstructor(String id);
    
}
