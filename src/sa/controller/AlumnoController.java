/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.controller;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import sa.model.to.AlumnoTO;

/**
 *
 * @author dave
 */
public interface AlumnoController{
    
    public void createAlumno(AlumnoTO alumno);

    public DefaultComboBoxModel<String> getModelCarreras();

    public void buscarTodos(JTable jTAAlumnos);

    public AlumnoTO getAlumno(String selectionFromView);

    public void updateAlumno(AlumnoTO alumno);

    public void deleteAlumno(AlumnoTO alumno);
}
