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

import sa.model.dao.ActividadDAO;
import sa.model.dao.InstructorDAO;
import sa.view.ActividadView;

/**
 *
 * @author dave
 */
public class ActividadController //implements DocumentListener, ItemListener
{    
    private final ActividadView view;
    private final HorarioControllerImpl horarioController;
    private final InstructorDAO instructorDAO;
    private final ActividadDAO actividadDAO;
    
    public ActividadController(ActividadView view,  InstructorDAO instructorDAO, ActividadDAO actividadDAO) {
        this.view = view;
        //initView();
        this.view.setVisible(true);
        this.instructorDAO = instructorDAO;
        this.actividadDAO = actividadDAO;
        horarioController = new HorarioControllerImpl();
    }
//    
//    private void initView(){
//        addButtonListeners();
//        addDocumentListener();
//        addItemListener();
//    }
//    
//    private void addItemListener(){
//        view.getjCBHoras().addItemListener(this);
//        view.getjCBTipo().addItemListener(this);
//    }
//    
//    private void addDocumentListener(){
//        view.getjTFIdActividad().getDocument().addDocumentListener(this);
//        view.getjTFNombreActividad().getDocument().addDocumentListener(this);
//        view.getjTFIdInstructorActividad().getDocument().addDocumentListener(this);
//        view.getjTADescripcionActividad().getDocument().addDocumentListener(this);
//    }
//
//    private void addButtonListeners() {
//        view.getjBtnRegistrarActividad().addActionListener(this::createActividad);
//        view.getjBtnModificarActividad().addActionListener(this::updateActividad);
//        view.getjBtnEliminarActividad().addActionListener(this::deleteActividad);
//    }
//    
//    private void deleteActividad(ActionEvent e){
//        if(actividadDAO.deleteActividad(view.getActividad()))
//            SAInputOutput.showInformationMessage("La actividad se eliminó");
//        else 
//            SAInputOutput.showErrorMessage("La actividad no se pudo eliminar");
//    }
//
//    private void createActividad(ActionEvent e){
//        if(actividadDAO.createActividad(view.getActividad()))
//            SAInputOutput.showInformationMessage("La actividad se creo correctamente");
//        else
//            SAInputOutput.showErrorMessage("La actividad no se pudo crear");
//    }
//    
//    private void updateActividad(ActionEvent e){
//        if(actividadDAO.updateActividad(view.getActividad()))
//            SAInputOutput.showInformationMessage("La actividad se modificó");
//        else 
//            SAInputOutput.showErrorMessage("La actividad no se pudo modificar");
//    }
//    
////    public void getAllInstructores(JTable jTRInstructores) {
////        jTRInstructores.setModel(instructorDAO.getDTM("SELECT * FROM Instructor"));
////    }
////
////    public InstructorTO getInstructor(String id) {
////        return InstructorDAO.getInstance().getInstructor(id);
////    }
////
////    public void createActividad(ActividadTO actividad) {
////        actividad.setInstructorFk(InstructorDAO.getInstance().getInstructor(actividad.getInstructorFk()));
////        if(actividad.getInstructorFk() == null || !actividad.getInstructorFk().isValid())
////            SAOutput.showErrorMessage("Instructor no es valido");
////        else if(!actividad.isValid())
////            SAOutput.showErrorMessage("La información de la actividad no es válida.");
////        else if(ActividadDAO.getInstance().createActividad(actividad))
////            SAOutput.showInformationMessage("La actividad se creo correctamente");
////        else
////            SAOutput.showErrorMessage("La actividad no se pudo crear");
////    }
////
////    public void updateActividad(ActividadTO actividad) {
////        actividad.setInstructorFk(InstructorDAO.getInstance().getInstructor(actividad.getInstructorFk()));
////        if(!actividad.isValid())
////            SAOutput.showErrorMessage("Carga la actividad desde la tabla");
////        else if(ActividadDAO.getInstance().updateActividad(actividad))
////            SAOutput.showInformationMessage("Se modifico la actividad");
////        else
////            SAOutput.showErrorMessage("La actividad no se pudo modificar");
////    }
////
////    public void deleteActividad(ActividadTO actividad) {
////        actividad.setInstructorFk(InstructorDAO.getInstance().getInstructor(actividad.getInstructorFk()));
////        if(!actividad.isValid())
////            SAOutput.showErrorMessage("Carga la actividad desde la tabla");
////        else if(ActividadDAO.getInstance().deleteActividad(actividad))
////            SAOutput.showInformationMessage("La actividad se eliminó");
////        else
////            SAOutput.showErrorMessage("La actividad no se pudo eliminar");
////        
////    }
////
////    public void showAllActividades(JTable jTQActividades) {
////        jTQActividades.setModel(
////            ActividadDAO.getInstance().getDTM(
////                String.format(
////                    "SELECT %s, %s, %s, %s, %s, %s FROM Actividad "
////                            + "INNER JOIN Instructor ON Instructor.IdInstructor = Actividad.InstructorFk", 
////                    "IdActividad AS Id",
////                    "Nombre",
////                    "Tipo AS 'Tipo Actividad'",
////                    "Descripcion",
////                    "Horas",
////                    "Instructor.Nombres AS 'Nombre Instructor'"
////                )
////            )
////        );
////    }
////
////    public ActividadTO getActividad(String actividad) {
////        return ActividadDAO.getInstance().getActividad(actividad);
////    }
////
////    public void createHorario(HorarioTO horario) {
////        horario.setActividadFk(ActividadDAO.getInstance().getActividad(horario.getActividadFk().getIdActividad()));
////        horarioController.createHorario(horario);
////    }
////
////    public void showAllHorarios(ActividadTO actividad, JTable tableHorarios) {
////        horarioController.showAll(ActividadDAO.getInstance().getActividad(actividad), tableHorarios);
////    }
////
////    public HorarioTO getHorario(String id) {
////        return id != null && !id.trim().isEmpty() ? HorarioDAO.getInstance().getHorario(Integer.parseInt(id)) : null;
////    }
////
////    public void deleteHorario(HorarioTO horario) {
////        horario.setActividadFk(ActividadDAO.getInstance().getActividad(horario.getActividadFk()));
////        horarioController.deleteHorario(horario);
////    }
////
////    public void updateHorario(HorarioTO horario) {
////        horario.setActividadFk(ActividadDAO.getInstance().getActividad(horario.getActividadFk()));
////        horarioController.updateHorario(horario);
////    }
////    
//    private void textEdited(DocumentEvent e){
//        if(e == view.getjTFIdActividad())
//            editIdActividad();
//        else if(e == view.getjTFNombreActividad())
//            view.setNombreActividad();
//        else if(e == view.getjTFIdInstructorActividad())
//            view.setInstructorActividad(instructorDAO.getInstructor(view.getIdInstructorActividad()));
//        else if(e == view.getjTADescripcionActividad())
//            view.setDescripcionActividad();
//        view.enableButtons(actividadDAO.getActividad(view.getIdActividad()));
//    }
//    
//    private void editIdActividad(){
//        ActividadTO actividad = actividadDAO.getActividad(view.getIdActividad());
//        if(actividad != null)
//            view.setActividad(actividad);
//        else
//            view.setIdActividad();
//    }
//    
//    private void itemSelected(ItemEvent e){
//        if(e.getStateChange() == ItemEvent.SELECTED){
//            if(e.getSource() == view.getjCBHoras())
//                view.setHorasActividad();
//            else if(e.getSource() == view.getjCBTipo())
//                view.setTipoActividad();
//            view.enableButtons(actividadDAO.getActividad(view.getIdActividad()));
//        }
//    }
//
//    @Override
//    public void insertUpdate(DocumentEvent e) {
//        textEdited(e);
//    }
//
//    @Override
//    public void removeUpdate(DocumentEvent e) {
//        textEdited(e);
//    }
//
//    @Override
//    public void changedUpdate(DocumentEvent e) {
//        textEdited(e);
//    }
//
//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        itemSelected(e);
//    }
}

