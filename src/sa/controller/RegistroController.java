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

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.JTextComponent;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.RegistroDAO;
import sa.model.to.ActividadTO;
import sa.model.to.AlumnoTO;
import sa.model.to.RegistroTO;
import sa.utils.SAUtils;
import sa.view.RegistroView;

/**
 *
 * @author David Rodríguez <duvid9320@gmail.com>
 */
public class RegistroController {
    
    private RegistroView view;
    private RegistroDAO registroDAO;
    private AlumnoDAO alumnoDAO;
    private ActividadDAO actividadDAO;

    public RegistroController(RegistroView view, RegistroDAO registroDAO, AlumnoDAO alumnoDAO, ActividadDAO actividadDAO) {
        this.view = view;
        this.registroDAO = registroDAO;
        this.alumnoDAO = alumnoDAO;
        this.actividadDAO = actividadDAO;
        initView();
        view.setVisible(true);
    }
    
    private void initView(){
        addDocumentListener();
        addListSelectionListeners();
        addButtonListeners();
    }
    
    private void addButtonListeners(){
        view.getjBtnRegistrar().addActionListener(e -> {registroDAO.createRegistro(view.getRegistro()); cleanView();});
    }
    
    private void cleanView(){
        showQuerys();
        view.resetView();
    }
    
    private void showRegistroQuery(RegistroTO registro){
        view.getjTRegistro().setModel(registroDAO.getDTM(registro.getActividadesAlumnoQuery()));
    }
    
    private void addListSelectionListeners(){
        view.getjTActividades().getSelectionModel().addListSelectionListener(this::doSelectedActividad);
        view.getjTRegistro().getSelectionModel().addListSelectionListener(this::doSelectedRegistro);
    }
    
    private void doSelectedRegistro(ListSelectionEvent e){
        int row = view.getjTRegistro().getSelectedRow();
        ActividadTO actividad = actividadDAO.getActividad(view.getSelectedRegistroActividad(row));
        AlumnoTO alumno = alumnoDAO.getAlumno(view.getSelectedRegistroAlumno(row));
        if (alumno == null || actividad == null)
            return;
        view.setAlumno(alumno);
        view.setActividad(actividad);
        enableButtons();
    }
    
    private void doSelectedActividad(ListSelectionEvent e){
        int []selection = view.getjTActividades().getSelectedRows();
        if(selection != null && selection.length == 1 && selection[0] != -1)
            view.setActividad(actividadDAO.getActividad(view.getSelectedActividad(selection[0])));
        else
            view.resetActividad();
        enableButtons();
    }
    
    private void addDocumentListener(){
        SAUtils.addDocumentListener(getTextEditActions(), null);
    }
    
    private LinkedHashMap<JTextComponent, Consumer> getTextEditActions(){
        LinkedHashMap<JTextComponent, Consumer> actions = new LinkedHashMap<>();
        actions.put(view.getjTFQNControl(), a -> {showAlumno(); enableButtons();});
        actions.put(view.getjTFQIdActividad(), a -> showQueryActividad());
        actions.put(view.getjTFQNActividad(), a -> showQueryActividad());
        actions.put(view.getjTFQTActividad(), a -> showQueryActividad());
        actions.put(view.getjTFQHActividad(), a -> showQueryActividad());
        return actions;
    }
    
    private void enableButtons(){
        RegistroTO registroView = view.getRegistro();
        RegistroTO registro = registroView.isValid() ? registroDAO.getRegistro(registroView) : null;
        view.getjBtnRegistrar().setEnabled(registroView.isValid() && registro == null);
        view.getjBtnEliminarRegistro().setEnabled(registroView.isValid() && registro != null);
    }
    
    private void showQuerys(){
        showQueryActividad();
        showRegistroQuery(view.getRegistro());
    }
    
    private void showQueryActividad(){
        view.getjTActividades().setModel(registroDAO.getDTM(view.getQueryActividad()));
    }
    
    private void showAlumno(){
        AlumnoTO alumno = alumnoDAO.getAlumno(view.getNumeroControl());
        view.setAlumno(alumnoDAO.getAlumno(view.getNumeroControl()));
        if(alumno != null)
            showQuerys();
    }
}
