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
package sa.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.JTextComponent;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.CarreraDAO;
import sa.model.dao.InstructorDAO;
import sa.model.to.AlumnoTO;
import sa.utils.SAUtils;
import sa.view.ActividadView;
import sa.view.AlumnoView;
import sa.view.InstructorView;

/**
 *
 * @author dave
 */
public class AlumnoControllerImpl{
    
    private final AlumnoView view;
    private final AlumnoDAO alumnoDAO;
    private final CarreraDAO carreraDAO;
    
    public AlumnoControllerImpl(AlumnoView view, AlumnoDAO alumnoDAO, CarreraDAO carreraDAO) {
        this.view = view;
        this.alumnoDAO = alumnoDAO;
        this.carreraDAO = carreraDAO;
        initView();
        view.setVisible(true);
    }
    
    private void initView(){
        view.getjCBCarrera().setModel(getModelCarreras());
        addButtonListeners();
        addDocumentListener();
        addItemListener();
        view.getjTAAlumnos().getSelectionModel().addListSelectionListener(this::loadSelected);
    }
    
    private void addItemListener(){
        view.getjCBCarrera().addItemListener(e -> {view.setCarrera(carreraDAO.getCarrera(view.getSelectedCarrera())); enableButtons();});
        view.getjCBSemestre().addItemListener(e -> {view.setSemestre(); enableButtons();});
    }
    
    private void addDocumentListener(){
        SAUtils.addDocumentListener(getTextEditActions(), a -> enableButtons());
    }
    
    private void addButtonListeners(){
        view.getjBtnRegistrar().addActionListener(e -> { alumnoDAO.insertAlumno(view.getAlumno()); cleanView();});
        view.getjBtnModificar().addActionListener(e -> {alumnoDAO.updateAlumno(view.getAlumno()); cleanView();});
        view.getjBtnEliminar().addActionListener(e -> {alumnoDAO.deleteAlumno(view.getAlumno()); cleanView();});
        view.getjBtnClose().addActionListener(e -> view.dispose());
        view.getjBtnMaximize().addActionListener(e -> view.setExtendedState(view.getExtendedState() != JFrame.MAXIMIZED_BOTH ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL));
        view.getjBtnMinimize().addActionListener(e -> view.setExtendedState(JFrame.ICONIFIED));
        view.getjBtnEliminarSeleccionados().addActionListener(this::deleteSelected);
        view.getjBtnQuery().addActionListener(e -> showQuery(view.getQueryAlumnos()));
        view.getjBtnAdmInstructorView().addActionListener(this::showInstructorView);
        view.getjBtnAdmActividadView().addActionListener(this::showActividadView);
    }
    
    private void showActividadView(ActionEvent e){
        new ActividadController(new ActividadView(), InstructorDAO.getInstance(), ActividadDAO.getInstance());
    }

    private void showInstructorView(ActionEvent e){
        new InstructorController(new InstructorView(), InstructorDAO.getInstance());
        view.dispose();
    }
    
    private void loadSelected(ListSelectionEvent e){
        int []selection = view.getjTAAlumnos().getSelectedRows();
        if(selection == null)
            return;
        else if(selection.length == 1)
            view.setAlumno(alumnoDAO.getAlumno(view.getSelectedAlumno(selection[0])));
        else if(selection.length > 1)
            view.resetView();
    }
    
    private void deleteSelected(ActionEvent e){
        Arrays.stream(view.getjTAAlumnos().getSelectedRows())
                .mapToObj(r -> view.getSelectedAlumno(r))
                .forEach(nc -> alumnoDAO.deleteAlumno(nc));
        showQuery(view.getQueryAlumnos());
    }
    
    private void cleanView(){
        view.resetView();
        showQuery(view.getQueryAlumnos());
    }
    
    private void showQuery(String query){
        view.getjTAAlumnos().setModel(alumnoDAO.getDTM(query));
    }
    
    private DefaultComboBoxModel<String> getModelCarreras() {
        return new DefaultComboBoxModel<>(
                new Vector(
                    carreraDAO.getAllCarreras().stream().
                            map(c -> c.getIdCarrera())
                            .collect(Collectors.toList())
                )
        );
    }
    
    private LinkedHashMap<JTextComponent, Consumer> getTextEditActions(){
        LinkedHashMap<JTextComponent, Consumer> actions = new LinkedHashMap<>();
        actions.put(view.getjTFAMaterno(), a -> view.setApellidoMaterno());
        actions.put(view.getjTFAPaterno(), a -> view.setApellidoPaterno());
        actions.put(view.getjTFNombres(), a-> view.setNombres());
        actions.put(view.getjTFNumeroControl(), a -> editNumeroControl());
        actions.put(view.getjTFQAMaterno(), a -> showQuery(view.getQueryAlumnos()));
        actions.put(view.getjTFQAPaterno(), a -> showQuery(view.getQueryAlumnos()));
        actions.put(view.getjTFQCarrera(), a -> showQuery(view.getQueryAlumnos()));
        actions.put(view.getjTFQNControl(), a -> showQuery(view.getQueryAlumnos()));
        actions.put(view.getjTFQNombres(), a -> showQuery(view.getQueryAlumnos()));
        actions.put(view.getjTFQSemestre(), a -> showQuery(view.getQueryAlumnos()));
        return actions;
    }
    
    private void editNumeroControl(){
        AlumnoTO alumno = alumnoDAO.getAlumno(view.getNumeroControl());
        view.setNumeroControl();
        if(alumno != null)
            view.setAlumno(alumno);
        else
            view.setNumeroControl();
    }
    
    private void enableButtons(){
        AlumnoTO alumno = null;
        if(view.getAlumno().isValid())
            alumno = alumnoDAO.getAlumno(view.getNumeroControl());
        view.getjBtnRegistrar().setEnabled(view.getAlumno().isValid() && alumno == null);
        view.getjBtnModificar().setEnabled(alumno != null);
        view.getjBtnEliminar().setEnabled(alumno != null);
    }
}
