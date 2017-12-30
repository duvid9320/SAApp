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


import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import sa.model.dao.InstructorDAO;
import sa.model.to.InstructorTO;
import sa.view.InstructorView;


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
        showQuery(view.getQueryInstructores());
    }
    
    private void addListSelectionListeners(){
        view.getjTAQueryInstructores().getSelectionModel().addListSelectionListener(this::doSelectedInstructor);
    }
    
    private void doSelectedInstructor(ListSelectionEvent e){
        System.out.println(Arrays.toString(view.getjTAQueryInstructores().getSelectedRows()));
        view.setInstructor(instructorDAO.getInstructor(view.getSelectedInstructor()));
    }
    
    private void addButtonsListeners(){
        view.getjBtnQueryInstructores().addActionListener(e -> showQuery(view.getQueryInstructores()));
        view.getjBtnEliminarInstructor().addActionListener(e -> {instructorDAO.deleteInstructor(view.getInstructor()); cleanView();});
        view.getjBtnRegistrarInstructor().addActionListener(e -> {instructorDAO.createInstructor(view.getInstructor()); cleanView();});
        view.getjBtnModificarInstructor().addActionListener(e -> {instructorDAO.updateInstructor(view.getInstructor()); cleanView();});
        view.getjBtnMinimize().addActionListener( e -> view.setState(JFrame.ICONIFIED));
        view.getjBtnMaximize().addActionListener(e ->  view.setExtendedState(view.getExtendedState() != JFrame.MAXIMIZED_BOTH ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL));
        view.getjBtnClose().addActionListener(e -> view.dispose());
    }
    
    private void cleanView(){
        showQuery(view.getInstructor().getQuerySQL());
        view.resetView();
    }
    
    private void addDocumentListener(){
        view.getjTFApellidosInstructor().getDocument().addDocumentListener(this);
        view.getjTFIdInstructor().getDocument().addDocumentListener(this);
        view.getjTFNombresInstructor().getDocument().addDocumentListener(this);
        view.getjTAGradoInstructor().getDocument().addDocumentListener(this);
        view.getjTFQApellidos().getDocument().addDocumentListener(this);
        view.getjTFQId().getDocument().addDocumentListener(this);
        view.getjTFQNombres().getDocument().addDocumentListener(this);
    }
    
    public void showQuery(String query){
        view.getjTAQueryInstructores().setModel(instructorDAO.getDTM(query));
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
        if(e.getDocument() == view.getjTFApellidosInstructor().getDocument())
            view.setApellidosInstructor();
        else if (e.getDocument() == view.getjTFIdInstructor().getDocument())
            editIdInstructor();
        else if (e.getDocument() == view.getjTFNombresInstructor().getDocument())
            view.setNombresInstructor();
        else if(e.getDocument() == view.getjTAGradoInstructor().getDocument())
            view.setGradoInstructor();
        else if (isQueryEdited(e))
            showQuery(view.getQueryInstructores());
        enableButtons();
    }
    
    private boolean isQueryEdited(DocumentEvent e){
        return e.getDocument() == view.getjTFQApellidos().getDocument() || 
                e.getDocument() == view.getjTFQId().getDocument() ||
                e.getDocument() == view.getjTFQNombres().getDocument();
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