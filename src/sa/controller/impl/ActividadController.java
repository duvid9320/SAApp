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
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.JTextComponent;
import sa.connection.MySQLManager;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.CarreraDAO;
import sa.model.dao.InstructorDAO;
import sa.model.to.ActividadTO;
import sa.utils.SAUtils;
import sa.view.ActividadView;
import sa.view.AlumnoView;
import sa.view.InstructorView;

/**
 *
 * @author dave
 */
public class ActividadController {    
    private final ActividadView view;
    private final HorarioControllerImpl horarioController;
    private final InstructorDAO instructorDAO;
    private final ActividadDAO actividadDAO;
    
    public ActividadController(ActividadView view,  InstructorDAO instructorDAO, ActividadDAO actividadDAO) {
        this.view = view;
        this.instructorDAO = instructorDAO;
        this.actividadDAO = actividadDAO;
        horarioController = new HorarioControllerImpl();
        initView();
        this.view.setVisible(true);
    }
    
    private void initView(){
        addDocumentListener();
        addItemListener();
        addListSelectionListeners();
        addButtonListeners();
    }
    
    private void addButtonListeners(){
        view.getjBtnRegistrarActividad().addActionListener(e -> {actividadDAO.createActividad(view.getActividad()); cleanView();});
        view.getjBtnModificarActividad().addActionListener(e -> {actividadDAO.updateActividad(view.getActividad()); cleanView();}); 
        view.getjBtnEliminarActividad().addActionListener(e -> {actividadDAO.deleteActividad(view.getActividad()); cleanView();});
        view.getjBtnAdmAlumnoView().addActionListener(e -> {new AlumnoControllerImpl(new AlumnoView(), AlumnoDAO.getInstance(), CarreraDAO.getInstance()); view.dispose();});
        view.getjBtnAdmInstructorView().addActionListener(e -> {new InstructorController(new InstructorView(), InstructorDAO.getInstance()); view.dispose();});
        view.getjBtnClose().addActionListener(e -> {view.dispose(); MySQLManager.getInstance().close();});
        view.getjBtnMaximize().addActionListener(e -> view.setExtendedState(view.getExtendedState() == JFrame.NORMAL ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL));
        view.getjBtnMinimize().addActionListener(e -> view.setExtendedState(JFrame.ICONIFIED));
        view.getjBtnQueryActividad().addActionListener(e -> showActividadQuery(view.getActividadQuery()));
        view.getjBtnQueryInstructores().addActionListener(e -> showInstructorQuery(view.getInstructorQuery()));
    }
    
    private void cleanView(){
        view.resetView();
        showActividadQuery(view.getActividadQuery());
    }
    
    private void addListSelectionListeners(){
        view.getjTRInstructores().getSelectionModel().addListSelectionListener(this::doSelectedInstructor);
        view.getjTQActividades().getSelectionModel().addListSelectionListener(this::doSelectedActividad);
    }
    
    private void doSelectedInstructor(ListSelectionEvent e){
        Object []ids = Arrays.stream(view.getjTRInstructores().getSelectedRows()).filter(r -> r != -1).mapToObj(r -> view.getSelectedInstructor(r)).toArray();
        if(ids == null)
            return;
        else if(ids.length == 1)
            view.setInstructorActividad(instructorDAO.getInstructor(String.valueOf(ids[0])));
        else 
            view.resetView();
        enableButtons();
    }
    
    private void doSelectedActividad(ListSelectionEvent e){
        Object []ids = Arrays.stream(view.getjTQActividades().getSelectedRows()).filter(r -> r!=-1).mapToObj(r -> view.getSelectedActividad(r)).toArray();
        if(ids == null)
            return;
        else if(ids.length == 1)
            view.setActividad(actividadDAO.getActividad(String.valueOf(ids[0])));
        else
            view.resetView();
    }
    
    private void addItemListener(){
        view.getjCBTipoActividad().addItemListener(e -> {view.setTipoActividad(); enableButtons();});
        view.getjCBHorasActividad().addItemListener(e -> {view.setHorasActividad(); enableButtons();});
    }
    
    private void enableButtons(){
        ActividadTO actividad = null;
        if(view.getActividad().isValid())
            actividad = actividadDAO.getActividad(view.getActividad().getIdActividad());
        view.getjBtnRegistrarActividad().setEnabled(view.getActividad().isValid() && actividad == null);
        view.getjBtnEliminarActividad().setEnabled(actividad != null);
        view.getjBtnModificarActividad().setEnabled(actividad != null);
    }
    
    private void addDocumentListener(){
        SAUtils.addDocumentListener(getTextEditActions(), a -> enableButtons());
    }
    
    private LinkedHashMap<JTextComponent, Consumer> getTextEditActions(){
        LinkedHashMap<JTextComponent, Consumer> actions = new LinkedHashMap<>();
        actions.put(view.getjTFIdActividad(), t -> editIdActividad());
        actions.put(view.getjTADescripcionActividad(), t -> view.setDescripcion());
        actions.put(view.getjTFNombreActividad(), t -> view.setNombreActividad());
        actions.put(view.getjTFQNInstructor(), t -> showInstructorQuery(view.getInstructorQuery()));
        actions.put(view.getjTFQAInstructor(), t -> showInstructorQuery(view.getInstructorQuery()));
        actions.put(view.getjTFQGInstructor(), t -> showInstructorQuery(view.getInstructorQuery()));
        actions.put(view.getjTFQNActividad(), t -> showActividadQuery(view.getActividadQuery()));
        actions.put(view.getjTFQTActividad(), t -> showActividadQuery(view.getActividadQuery()));
        actions.put(view.getjTFQHActividad(), t -> showActividadQuery(view.getActividadQuery()));
        actions.put(view.getjTFQIActividad(), t -> showActividadQuery(view.getActividadQuery()));
        return actions;
    }
    
    private void editIdActividad(){
        ActividadTO actividad = actividadDAO.getActividad(view.getIdActividad());
        if(actividad != null)
            view.setActividad(actividad);
        else 
            view.setIdActividad();
    }
    
    private void showActividadQuery(String query){
        view.getjTQActividades().setModel(actividadDAO.getDTM(query));
    }
    
    private void showInstructorQuery(String query){
        view.getjTRInstructores().setModel(instructorDAO.getDTM(query));
    }
}

