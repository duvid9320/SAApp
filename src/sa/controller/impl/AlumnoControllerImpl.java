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
import sa.utils.SAInputOutput;
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
            SAInputOutput.showErrorMessage("La carrera no es valida");
        else if(!alumno.isValid())
            SAInputOutput.showErrorMessage("La información del alumno no es valida");
        else if(AlumnoDAO.getInstance().insertAlumno(alumno))
            SAInputOutput.showInformationMessage("Alumno registrado");
        else
            SAInputOutput.showErrorMessage("El alumno no se pudo registrar");
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
            SAInputOutput.showErrorMessage("La carrera no es valida");
        else if(!alumno.isValid())
            SAInputOutput.showErrorMessage("La información del alumno no es valida");
        else if(AlumnoDAO.getInstance().updateAlumno(alumno))
            SAInputOutput.showInformationMessage("Alumno modificado");
        else 
            SAInputOutput.showErrorMessage("El alumno no se pudo modificar");
    }

    @Override
    public void deleteAlumno(AlumnoTO alumno) {
        alumno.setCarrera(CarreraDAO.getInstance().getCarrera(alumno.getCarrera().getIdCarrera()));
        if(!alumno.isValid())
            SAInputOutput.showErrorMessage("El alumno no es valido, carguelo desde la tabla");
        else if(AlumnoDAO.getInstance().deleteAlumno(alumno))
            SAInputOutput.showInformationMessage("Alumno eliminado");
        else 
            SAInputOutput.showErrorMessage("El alumno no se pudo eliminar");
    }
}
