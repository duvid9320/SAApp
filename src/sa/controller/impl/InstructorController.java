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


import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import sa.model.dao.InstructorDAO;
import sa.model.to.InstructorTO;
import sa.utils.SAOutput;
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
public class InstructorController implements DocumentListener{
    private final InstructorView view;
    private final InstructorDAO instructorDAO;

    public InstructorController(InstructorView view, InstructorDAO instructorDAO) {
        this.view = view;
        this.instructorDAO = instructorDAO;
        initView();
        view.setVisible(true);
    }
    
    private void initView(){
        addDocumentListener();
        addButtonsListeners();
        addListSelectionListeners();
    }
    
    private void addListSelectionListeners(){
        view.getjTAQueryInstructores().getSelectionModel().addListSelectionListener(this::doSelectedInstructor);
    }
    
    private void doSelectedInstructor(ListSelectionEvent e){
        view.setInstructor(instructorDAO.getInstructor(view.getSelectedInstructor()));
    }
    
    private void addButtonsListeners(){
        view.getjBtnEliminarInstructor().addActionListener(this::deleteInstructor);
        view.getjBtnRegistrarInstructor().addActionListener(this::createInstructor);
        view.getjBtnModificarInstructor().addActionListener(this::updateInstructor);
        view.getjBtnBuscarInstructores().addActionListener(this::queryInstructores);
    }
    
    private void cleanView(){
        showUpdatedInstructor();
        view.resetView();
    }
    
    private void createInstructor(ActionEvent e){
        if(instructorDAO.createInstructor(view.getInstructor()))
            SAOutput.showInformationMessage("El instructor se registró");
        else
            SAOutput.showErrorMessage("El instructor no se pudo registrar");
        cleanView();
    }
    
    private void deleteInstructor (ActionEvent e){
        if(instructorDAO.deleteInstructor(view.getInstructor()))
            SAOutput.showInformationMessage("El instructor se eliminó");
        else 
            SAOutput.showErrorMessage("El instructor no se pudo eliminar");
        cleanView();
    }
    
    private void updateInstructor(ActionEvent e){
        if(instructorDAO.updateInstructor(view.getInstructor()))
            SAOutput.showInformationMessage("El instructor se modificó");
        else
            SAOutput.showErrorMessage("El instructor no se pudo modificar");
        cleanView();
    }
    
    private void addDocumentListener(){
        view.getjTFApellidosInstructor().getDocument().addDocumentListener(this);
        view.getjTFIdInstructor().getDocument().addDocumentListener(this);
        view.getjTFNombresInstructor().getDocument().addDocumentListener(this);
        view.getjTAGradoInstructor().getDocument().addDocumentListener(this);
    }
    
    public void showUpdatedInstructor(){
        view.getjTAQueryInstructores().setModel(instructorDAO.getDTM(view.getInstructor().getQuerySQL()));
    }
    
    public void queryInstructores(ActionEvent e) {
        view.getjTAQueryInstructores().setModel(instructorDAO.getDTM("SELECT * FROM Instructor"));
    }
    
    private void editIdInstructor(){
        System.out.println("Editando a id instructor");
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
        if(e.getDocument() == view.getjTFApellidosInstructor().getDocument())
            view.setApellidosInstructor();
        else if (e.getDocument() == view.getjTFIdInstructor().getDocument())
            editIdInstructor();
        else if (e.getDocument() == view.getjTFNombresInstructor().getDocument())
            view.setNombresInstructor();
        else if(e.getDocument() == view.getjTAGradoInstructor().getDocument())
            view.setGradoInstructor();
        enableButtons();
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
