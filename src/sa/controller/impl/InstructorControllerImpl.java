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
package sa.controller.impl;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import sa.connection.MySQLManager;
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
public class InstructorControllerImpl implements DocumentListener{
    private final InstructorView view;
    private final InstructorDAO instructorDAO;

    public InstructorControllerImpl(InstructorView view, InstructorDAO instructorDAO) {
        this.view = view;
        this.instructorDAO = instructorDAO;
        initView();
        view.setVisible(true);
    }
    
    private void initView(){
        addDocumentListener();
    }
    
    private void addDocumentListener(){
        view.getjTFApellidosInstructor().getDocument().addDocumentListener(this);
        view.getjTFIdInstructor().getDocument().addDocumentListener(this);
        view.getjTFNombresInstructor().getDocument().addDocumentListener(this);
        view.getjTAGradoInstructor().getDocument().addDocumentListener(this);
    }
    
    public void createInstructor(InstructorTO instructor) {
        if(!instructor.isValid())
            SAOutput.showErrorMessage("La información del instructor no es valida");
        else if(InstructorDAO.getInstance().createInstructor(instructor))
            SAOutput.showInformationMessage("El instructor se registrar correctamente");
        else 
            SAOutput.showErrorMessage("El instructor no se pudo registrar");
    }
    
    public void updateInstructor(InstructorTO instructor) {
        if(!instructor.isValid())
            SAOutput.showErrorMessage("La información del instructor no es valida");
        else if(InstructorDAO.getInstance().updateInstructor(instructor))
            SAOutput.showInformationMessage("El instructor se modifico correctamente");
        else
            SAOutput.showErrorMessage("El instructor no se pudo modificar");
    }

    public void deleteInstructor(InstructorTO instructor) {
        if(!instructor.isValid())
            SAOutput.showErrorMessage("Carga el instructor desde la tabla");
        else if(InstructorDAO.getInstance().deleteInstructor(instructor))
            SAOutput.showInformationMessage("El instructor se elimino correctamente");
        else 
            SAOutput.showErrorMessage("El instructor no se pudo eliminar");
    }

    
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

    public InstructorTO getInstructor(String id) {
        return id != null ? InstructorDAO.getInstance().getInstructor(id) : null;
    }
    
    private void editIdInstructor(){
        InstructorTO instructor = instructorDAO.getInstructor(view.getIdInstructor());
        if(instructor != null)
            view.setInstructor(instructor);
        else
            view.setIdInstructor();
    }
    
    private void enableButtons(){
        if(!view.getInstructor().isValid())
            return;
        InstructorTO instructor = instructorDAO.getInstructor(view.getIdInstructor());
        view.getjBtnRegistrarInstructor().setEnabled(instructor == null);
        view.getjBtnModificarInstructor().setEnabled(instructor != null);
        view.getjBtnEliminarInstructor().setEnabled(instructor != null);
    }
    
    private void textEdited(DocumentEvent e){
        if(e == view.getjTFApellidosInstructor())
            view.setApellidosInstructor();
        else if (e == view.getjTFIdInstructor())
            editIdInstructor();
        else if (e == view.getjTFNombresInstructor())
            view.setNombresInstructor();
        else if(e == view.getjTAGradoInstructor())
            view.setGradoInstructor();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        textEdited(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        textEdited(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        textEdited(e);
    }
    
}
