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
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.CarreraDAO;
import sa.model.to.AlumnoTO;
import sa.utils.SAUtils;
import sa.view.AlumnoView;

/**
 *
 * @author dave
 */
public class AlumnoControllerImpl implements DocumentListener{
    
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
    }
    
    private void addDocumentListener(){
        SAUtils.addDocumentListener(
                this, 
                view.getjTFAMaterno(),
                view.getjTFAPaterno(),
                view.getjTFNombres(),
                view.getjTFNumeroControl(),
                view.getjTFQAMaterno(),
                view.getjTFQAPaterno(),
                view.getjTFQCarrera(),
                view.getjTFQNControl(),
                view.getjTFQNombres(),
                view.getjTFQSemestre()
        );
    }
    
    private void removeDocumentListener(){
        SAUtils.removeDocumentListener(
                this, 
                view.getjTFAMaterno(),
                view.getjTFAPaterno(),
                view.getjTFNombres(),
                view.getjTFNumeroControl(),
                view.getjTFQAMaterno(),
                view.getjTFQAPaterno(),
                view.getjTFQCarrera(),
                view.getjTFQNControl(),
                view.getjTFQNombres(),
                view.getjTFQSemestre()
        );
    }
    
    private void addButtonListeners(){
        view.getjBtnRegistrar().addActionListener(e -> { alumnoDAO.insertAlumno(view.getAlumno()); cleanView();});
        view.getjBtnModificar().addActionListener(e -> {alumnoDAO.updateAlumno(view.getAlumno()); cleanView();});
        view.getjBtnEliminar().addActionListener(e -> {alumnoDAO.deleteAlumno(view.getAlumno()); cleanView();});
        view.getjBtnClose().addActionListener(e -> view.dispose());
        view.getjBtnMaximize().addActionListener(e -> view.setExtendedState(view.getState() != JFrame.MAXIMIZED_BOTH ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL));
        view.getjBtnMinimize().addActionListener(e -> view.setExtendedState(JFrame.ICONIFIED));
        view.getjBtnEliminarSeleccionados().addActionListener(this::deleteSelected);
        view.getjBtnQuery().addActionListener(e -> showQuery(view.getQueryAlumnos()));
    }
    
    private void deleteSelected(ActionEvent e){
        Arrays.stream(view.getjTAAlumnos().getSelectedRows())
                .mapToObj(r -> view.getSelectedAlumno(r))
                .forEach(nc -> alumnoDAO.deleteAlumno(nc));
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
    
    private void textEdited(DocumentEvent e){
        if(SAUtils.isJTFEdited(e, view.getjTFAMaterno()))
            view.setApellidoMaterno();
        else if(SAUtils.isJTFEdited(e, view.getjTFAPaterno()))
            view.setApellidoPaterno();
        else if(SAUtils.isJTFEdited(e, view.getjTFNombres()))
            view.setNombres();
        else if(SAUtils.isJTFEdited(e, view.getjTFNumeroControl()))
            editNumeroControl();
        else if(isQueryEdited(e))
            showQuery(view.getQueryAlumnos());
        enableButtons();
    }
    
    private void editNumeroControl(){
        AlumnoTO alumno = alumnoDAO.getAlumno(view.getNumeroControl());
        if(alumno != null)
            view.setAlumno(alumno);
        else
            view.setNumeroControl();
    }
    
    private void enableButtons(){
        AlumnoTO alumno = alumnoDAO.getAlumno(view.getNumeroControl());
        if(!view.getAlumno().isValid())
            return;
        view.getjBtnRegistrar().setEnabled(alumno == null);
        view.getjBtnModificar().setEnabled(alumno != null);
        view.getjBtnEliminar().setEnabled(alumno != null);
    }
    
    private boolean isQueryEdited(DocumentEvent e){
        return SAUtils.isAnyEdited(
                e, 
                view.getjTFQAMaterno(),
                view.getjTFQAPaterno(),
                view.getjTFQCarrera(),
                view.getjTFQNControl(),
                view.getjTFQNombres(),
                view.getjTFQSemestre()
        );
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
