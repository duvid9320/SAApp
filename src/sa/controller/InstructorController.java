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
package sa.controller;


import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.JTextComponent;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.CarreraDAO;
import sa.model.dao.InstructorDAO;
import sa.model.to.InstructorTO;
import sa.utils.SAUtils;
import sa.view.ActividadView;
import sa.view.AlumnoView;
import sa.view.InstructorView;


/**
 *
 * @author dave
 */
public class InstructorController {
    
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
        int[] selectedRows = view.getjTAQueryInstructores().getSelectedRows();
        if(selectedRows == null)
            return;
        else if(selectedRows.length == 1)
            view.setInstructor(instructorDAO.getInstructor(view.getSelectedInstructor()));
        else if(selectedRows.length > 1)
            view.resetView();
    }
    
    private void addButtonsListeners(){
        view.getjBtnQueryInstructores().addActionListener(e -> showQuery(view.getQueryInstructores()));
        view.getjBtnEliminarInstructor().addActionListener(e -> {instructorDAO.deleteInstructor(view.getInstructor()); cleanView();});
        view.getjBtnRegistrarInstructor().addActionListener(e -> {instructorDAO.createInstructor(view.getInstructor()); cleanView();});
        view.getjBtnModificarInstructor().addActionListener(e -> {instructorDAO.updateInstructor(view.getInstructor()); cleanView();});
        view.getjBtnMinimize().addActionListener( e -> view.setState(JFrame.ICONIFIED));
        view.getjBtnMaximize().addActionListener(e ->  view.setExtendedState(view.getExtendedState() != JFrame.MAXIMIZED_BOTH ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL));
        view.getjBtnClose().addActionListener(e -> view.dispose());
        view.getjBtnDeleteSelected().addActionListener(this::deleteSelected);
        view.getjBtnAdmAlumnoView().addActionListener(this::showAlumnoView);
        view.getjBtnAdmActividadView().addActionListener(this::showActividadView);
    }
    
    private void showActividadView(ActionEvent e){
        new ActividadController(new ActividadView(), instructorDAO, ActividadDAO.getInstance());
    }
    
    private void showAlumnoView(ActionEvent e){
        new AlumnoController(new AlumnoView(), AlumnoDAO.getInstance(), CarreraDAO.getInstance());
        view.dispose();
    }
    
    private void deleteSelected(ActionEvent e){
        Arrays.stream(view.getjTAQueryInstructores().getSelectedRows())
                .mapToObj(r -> view.getSelectedInstructor(r))
                .filter(s -> s!= null)
                .forEach(this.instructorDAO::deleteInstructor);
        showQuery(view.getQueryInstructores());
    }
    
    private void cleanView(){
        showQuery(view.getQueryInstructores());
        view.resetView();
    }
    
    private void addDocumentListener(){
        SAUtils.addDocumentListener(getTextEditActions(), a -> enableButtons());
    }
    
    public void showQuery(String query){
        view.getjTAQueryInstructores().setModel(instructorDAO.getDTM(query));
    }
    
    private void enableButtons(){
        InstructorTO instructor = null;
        if(view.getInstructor().isValid())
            instructor = instructorDAO.getInstructor(view.getIdInstructor());
        view.getjBtnRegistrarInstructor().setEnabled(view.getInstructor().isValid() && instructor == null);
        view.getjBtnModificarInstructor().setEnabled(instructor != null);
        view.getjBtnEliminarInstructor().setEnabled(instructor != null);
    }
    
    private void editIdInstructor(){
        InstructorTO instructor = instructorDAO.getInstructor(view.getIdInstructor());
        if(instructor != null)
            view.setInstructor(instructor);
        else
            view.setIdInstructor();
    }
    
    private LinkedHashMap<JTextComponent, Consumer> getTextEditActions(){
        LinkedHashMap<JTextComponent, Consumer> actions = new LinkedHashMap<>();
        actions.put(view.getjTFApellidosInstructor(), a -> view.setApellidosInstructor());
        actions.put(view.getjTFIdInstructor(), a -> editIdInstructor());
        actions.put(view.getjTFNombresInstructor(), a -> view.setNombresInstructor());
        actions.put(view.getjTAGradoInstructor(), a -> view.setGradoInstructor());
        actions.put(view.getjTFQApellidos(), a -> showQuery(view.getQueryInstructores()));
        actions.put(view.getjTFQId(), a -> showQuery(view.getQueryInstructores()));
        actions.put(view.getjTFQNombres(), a -> showQuery(view.getQueryInstructores()));
        return actions;
    }
}
