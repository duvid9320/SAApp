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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.JTextComponent;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.AsistenciaDAO;
import sa.model.dao.HorarioDAO;
import sa.model.dao.RegistroDAO;
import sa.model.to.AlumnoTO;
import sa.model.to.AsistenciaTO;
import sa.model.to.RegistroTO;
import sa.utils.SAInputOutput;
import sa.utils.SAUtils;
import sa.view.AsistenciaView;

/**
 *
 * @author David Rodríguez <duvid9320@gmail.com>
 */
public class AsistenciaController {
    private final AsistenciaView view; 
    private final ActividadDAO actividadDAO;
    private final AsistenciaDAO asistenciaDAO;
    private final AlumnoDAO alumnoDAO;
    private final RegistroDAO registroDAO;
    private final HorarioDAO horarioDAO;

    public AsistenciaController(HorarioDAO horarioDAO,ActividadDAO actividadDAO, AsistenciaView view, AsistenciaDAO asistenciaDAO, AlumnoDAO alumnoDAO, RegistroDAO registroDAO) {
        this.view = view;
        this.horarioDAO = horarioDAO;
        this.asistenciaDAO = asistenciaDAO;
        this.alumnoDAO = alumnoDAO;
        this.registroDAO = registroDAO;
        this.actividadDAO = actividadDAO;
        initView();
        view.setVisible(true);
    }
    
    private void initView(){
        addDocumentListeners();
        addListSelectionListener();
        addButtonListeners();
    }
    
    private void addButtonListeners(){
        view.getjBtnEntrada().addActionListener(e -> {asistenciaDAO.createAsistencia(registroDAO.getRegistro(view.getAlumno(), view.getActividad())); cleanView();});
        view.getjBtnSalida().addActionListener(e -> {asistenciaDAO.updateAsistencia(asistenciaDAO.getAsistencia(view.getAlumno(), view.getActividad()));});
    }
    
    private void cleanView(){
        view.setAlumno(null);
    }
    
    private void addListSelectionListener(){
        view.getjTActividades().getSelectionModel().addListSelectionListener(this::doActividadSelected);
    }
    
    private void doActividadSelected(ListSelectionEvent e ){
        int row = view.getjTActividades().getSelectedRow();
        if(row != -1)
            view.setActividad(actividadDAO.getActividad(view.getSelectedActividad(row)));
        view.getjTFQNControl().setEnabled(view.getActividad() != null);
    }
    
    private void addDocumentListeners(){
        SAUtils.addDocumentListener(getTextEditActions(), a -> enableButtons());
    }
    
    private void enableButtons(){
        AsistenciaTO asistencia = null;
        boolean isValid = view.getAlumno() != null && view.getActividad() != null;
        if(isValid)
            asistencia = asistenciaDAO.getAsistencia(view.getAlumno(), view.getActividad());
        view.getjBtnEntrada().setEnabled(asistencia == null && isValid);
        view.getjBtnSalida().setEnabled(asistencia != null && isValid);
    }
    
    private LinkedHashMap<JTextComponent, Consumer> getTextEditActions(){
        LinkedHashMap<JTextComponent, Consumer> actions = new LinkedHashMap<>();
        actions.put(view.getjTFQIdActividad(), a -> showActividadQuery());
        actions.put(view.getjTFQNActividad(), a -> showActividadQuery());
        actions.put(view.getjTFQTActividad(), a -> showActividadQuery());
        actions.put(view.getjTFQHActividad(), a -> showActividadQuery());
        actions.put(view.getjTFQNControl(), a -> showAlumno());
        return actions;
    }
    
    private void showAlumno(){
        AlumnoTO alumno = alumnoDAO.getAlumno(view.getNumeroControl());
        if(alumno == null)
            return;
        RegistroTO registro = registroDAO.getRegistro(alumno, view.getActividad());
        AsistenciaTO asistencia = registro != null ? asistenciaDAO.getAsistencia(alumno, view.getActividad()) : null;
        if(registro == null)
            SAInputOutput.showErrorMessage("El alumno no esta registrado a esta actividad");
        else if(asistencia != null)
            terminarAsistencia(asistencia, registro);
        else if(!SAUtils.isOnTime(SAUtils.getTimeFromString(horarioDAO.getHorario(view.getActividad()).getHoraInicio()), new Date()))
            SAInputOutput.showErrorMessage(String.format("El alumno con NC %s no llegó a tiempo, no se toma asistencia", alumno.getNumeroControl()));
        else if(asistenciaDAO.createAsistencia(registro))
            SAInputOutput.showInformationMessage("Se tomó asistencia del alumno");
        else 
            SAInputOutput.showErrorMessage("No se pudo tomar asistencia");
    }
    
    private void terminarAsistencia(AsistenciaTO asistencia, RegistroTO registro){
        if(!asistencia.getHoraSalida().trim().isEmpty()){
            SAInputOutput.showErrorMessage("El alumno ya dio salida");
            return;
        }
        asistencia.setHoraSalida(SAUtils.getFormattedTime(new Date()));
        registro.setHorasAsistidas(registro.getHorasAsistidas() + SAUtils.getAsistedHours(asistencia.getHoraLlegada(), asistencia.getHoraSalida()));
        registroDAO.updateRegistro(registro);
        asistenciaDAO.updateAsistencia(asistencia);
    }
    
    private void showActividadQuery(){
        view.getjTActividades().setModel(asistenciaDAO.getDTM(view.getQueryActividadesNow()));
    }
}
