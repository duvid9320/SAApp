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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import sa.model.dao.ActividadDAO;
import sa.model.dao.HorarioDAO;
import sa.model.dao.InstructorDAO;
import sa.model.to.HorarioTO;
import sa.utils.SAUtils;
import sa.view.ActividadView;

/**
 *
 * @author dave
 */
public class HorarioControllerImpl{
    private final ActividadView view;
    private final ActividadDAO actividadDAO;
    private final InstructorDAO instructorDAO;
    private final HorarioDAO horarioDAO;

    public HorarioControllerImpl(ActividadView view, ActividadDAO actividadDAO, InstructorDAO instructorDAO, HorarioDAO horarioDAO) {
        this.actividadDAO = actividadDAO;
        this.instructorDAO = instructorDAO;
        this.horarioDAO = horarioDAO;
        this.view = view;
        initView();
    }
    
    private void initView(){
        addDocumentListener();
        addChangeListeners();
        addListSelectionListeners();
        addButtonListeners();
    }
    
    private void addButtonListeners(){
        view.getjBtnRegistrarHorario().addActionListener(e -> {horarioDAO.createHorario(view.getHorario()); cleanHorarioView();});
        view.getjBtnModificarHorario().addActionListener(e -> {horarioDAO.updateHorario(view.getHorario()); cleanHorarioView();});
        view.getjBtnEliminarHorario().addActionListener(e -> {horarioDAO.deleteHorario(view.getHorario()); cleanHorarioView();});
        view.getjBtnClean().addActionListener(e -> view.resetHorarioView());
    }
    
    private void cleanHorarioView(){
        view.resetHorarioView();
        showHorarioQuery(view.getHorario().getHorarioActividadQuery());
    }
    
    public void showHorarioQuery(String query){
        view.getjTQHActividad().setModel(horarioDAO.getDTM(query));
    }
    
    private void addListSelectionListeners(){
        view.getjTQHActividad().getSelectionModel().addListSelectionListener(e -> doSelectedHorario());
    }
    
    private void doSelectedHorario(){
        int []ids = Arrays.stream(view.getjTQHActividad().getSelectedRows()).filter(r -> r != -1).map(row -> view.getSelectedHorario(row)).toArray();
        if(ids == null)
            view.getjTFIdHorario().setText("");
        else if(ids.length == 1)
            view.setHorario(horarioDAO.getHorario(ids[0]));
        else 
            view.resetHorarioView();
    }
    
    private void addChangeListeners(){
        view.getjSHInicio().addChangeListener(e -> {view.setHoraInicioHorario(); enableButtons();});
        view.getjSHFin().addChangeListener(e -> {view.setHoraFinHorario(); enableButtons();});
    }
    
    private void addDocumentListener(){
        SAUtils.addDocumentListener(getTextEditActions(), a -> enableButtons());
    }
    
    private LinkedHashMap<JTextComponent, Consumer> getTextEditActions(){
        LinkedHashMap<JTextComponent, Consumer> actions = new LinkedHashMap<>();
        actions.put(view.getjTFIdHorario(), a -> view.setIdHorario());
        actions.put(view.getjTFLugarHorario(), a -> view.setLugarHorario());
        actions.put((JTextField)view.getjDCFechaHorario().getDateEditor(), a -> view.setFechaHorario());
        return actions;
    }
    
    private void enableButtons(){
        HorarioTO horarioExists=  null;
        HorarioTO horarioView = view.getHorario();
        if(horarioView.isValid())
            horarioExists = horarioDAO.getHorario(horarioView.getIdHorario());
        view.getjBtnRegistrarHorario().setEnabled(horarioView.isValid() && horarioView.getIdHorario() == 0 && horarioExists == null);
        view.getjBtnModificarHorario().setEnabled(horarioView.getIdHorario() != 0 && horarioExists != null);
        view.getjBtnEliminarHorario().setEnabled(horarioView.getIdHorario() != 0 && horarioExists != null);
    }
}
