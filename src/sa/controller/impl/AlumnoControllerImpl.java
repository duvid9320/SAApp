/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.controller.impl;

import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import sa.connection.MySQLManager;
import sa.controller.AlumnoController;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.CarreraDAO;
import sa.model.to.AlumnoTO;
import sa.model.to.CarreraTO;
import sa.utils.SAOutput;
import sa.utils.TableManager;
import sa.view.AlumnoView;

/**
 *
 * @author dave
 */
public class AlumnoControllerImpl implements AlumnoController{
    private AlumnoView v;

    public AlumnoControllerImpl() {
        v = new AlumnoView(this);
        v.setVisible(true);
    }
    
    @Override
    public void createAlumno(AlumnoTO alumno) {
        alumno.setCarrera(CarreraDAO.getInstance().getCarrera(alumno.getCarrera().getIdCarrera()));
        if(alumno.getCarrera() == null || !alumno.getCarrera().isValid())
            SAOutput.showErrorMessage("La carrera no es valida");
        else if(!alumno.isValid())
            SAOutput.showErrorMessage("La información del alumno no es valida");
        else if(AlumnoDAO.getInstance().insertAlumno(alumno))
            SAOutput.showInformationMessage("Alumno registrado");
        else
            SAOutput.showErrorMessage("El alumno no se pudo registrar");
    }

    @Override
    public DefaultComboBoxModel<String> getModelCarreras() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        CarreraDAO.getInstance().getAllCarreras()
                .forEach(c -> model.addElement(c.toString()));
        return model;
    }

    @Override
    public void busarTodos(JTable jTAAlumnos) {
        try {
            jTAAlumnos.setModel(
                    TableManager.getDefaultTableModelFromResultSet(
                            MySQLManager.getInstance().getResultSetFromQuery(
                                    String.format(
                                            "SELECT %s, %s, %s, %s, %s, %s FROM Alumno "
                                            + "INNER JOIN Carrera ON Carrera.idCarrera = Alumno.CarreraFk",
                                            "NumeroControl AS 'Numero de control'",
                                            "Nombres AS 'Nombre(s)'",
                                            "ApellidoPaterno AS 'Apellido Paterno'",
                                            "ApellidoMaterno AS 'Apellido Materno'",
                                            "Semestre",
                                            "Carrera.Nombre AS Carrera"
                                    )
                            )
                    )
            );
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AlumnoControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public AlumnoTO getAlumno(String selectionFromView) {
        if(selectionFromView != null && !selectionFromView.trim().isEmpty())
            return AlumnoDAO.getInstance().getAlumno(selectionFromView);
        return null;
    }

    @Override
    public void updateAlumno(AlumnoTO alumno) {
        alumno.setCarrera(CarreraDAO.getInstance().getCarrera(alumno.getCarrera().getIdCarrera()));
        if(alumno.getCarrera() == null || !alumno.getCarrera().isValid())
            SAOutput.showErrorMessage("La carrera no es valida");
        else if(!alumno.isValid())
            SAOutput.showErrorMessage("La información del alumno no es valida");
        else if(AlumnoDAO.getInstance().updateAlumno(alumno))
            SAOutput.showInformationMessage("Alumno modificado");
        else 
            SAOutput.showErrorMessage("El alumno no se pudo modificar");
    }

    @Override
    public void deleteAlumno(AlumnoTO alumno) {
        alumno.setCarrera(CarreraDAO.getInstance().getCarrera(alumno.getCarrera().getIdCarrera()));
        if(!alumno.isValid())
            SAOutput.showErrorMessage("El alumno no es valido, carguelo desde la tabla");
        else if(AlumnoDAO.getInstance().deleteAlumno(alumno))
            SAOutput.showInformationMessage("Alumno eliminado");
        else 
            SAOutput.showErrorMessage("El alumno no se pudo eliminar");
    }
}
