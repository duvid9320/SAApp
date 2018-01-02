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

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import sa.model.dao.ActividadDAO;
import sa.model.dao.InstructorDAO;
import sa.utils.SAUtils;
import sa.view.ActividadView;

/**
 *
 * @author dave
 */
public class ActividadController implements DocumentListener
{    
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
    }
    
    private void addItemListener(){
        view.getjCBTipoActividad().addItemListener(e -> {view.setTipoActividad(); enableButtons();});
        view.getjCBHorasActividad().addItemListener(e -> {view.setHorasActividad(); enableButtons();});
    }
    
    private void enableButtons(){
        
    }
    
    private void addDocumentListener(){
        SAUtils.addDocumentListener(
                this, 
                view.getjTFIdActividad(),
                view.getjTFNombreActividad(),
                view.getjTFIdInstructorActividad(),
                view.getjTADescripcionActividad()
        );
    }
    
    private void textEdited(DocumentEvent e){
        HashMap<Predicate, Consumer> actions = new HashMap<>();
        //si "c -> true" entonces "t -> ejecuta"
        actions.put(c->SAUtils.isJTComponentEdited(e, view.getjTFIdActividad()), t -> view.setIdActividad());
        actions.put(c->SAUtils.isJTComponentEdited(e, view.getjTADescripcionActividad()), t -> view.setDescripcion());
        actions.put(c->SAUtils.isJTComponentEdited(e, view.getjTFIdInstructorActividad()), t -> view.setInstructorActividad(instructorDAO.getInstructor(view.getSelectedInstructor())));
        actions.put(c->SAUtils.isJTComponentEdited(e, view.getjTFNombreActividad()), t -> view.setNombreActividad());
        SAUtils.doListener(actions);
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

