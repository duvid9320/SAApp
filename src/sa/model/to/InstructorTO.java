/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.HashMap;
import sa.utils.SAUtils;

/**
 *
 * @author Dave
 */
public class InstructorTO implements GenericTO{
    private String idInstructor;
    private String nombres;
    private String apellidos;
    private String grado;

    public InstructorTO(HashMap<String, Object> data) {
        this(
                String.valueOf(data.get("IdInstructor")), 
                String.valueOf(data.get("Nombres")), 
                String.valueOf(data.get("Apellidos")), 
                String.valueOf(data.get("Grado"))
        );
    }

    public InstructorTO(String idInstructor, String nombres, String apellidos, String grado) {
        this.idInstructor = idInstructor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.grado = grado;
    }

    public String getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(String idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public boolean isValid() {
        return SAUtils.stringsAreValid(idInstructor, nombres, apellidos, grado);
    }

    @Override
    public String getInsertSQL() {
        return String.format(
                "INSERT INTO Instructor VALUES ('%s', '%s', '%s', '%s')",
                idInstructor,
                nombres,
                apellidos,
                grado
        );
    }
    
    @Override
    public String getQuerySQL(){
        return String.format("SELECT * FROM Instructor WHERE IdInstructor = '%s'", idInstructor);
    }

    @Override
    public String getUpdateSQL() {
        return String.format(
                "UPDATE Instructor SET Nombres = '%s', Apellidos = '%s', Grado = '%s' WHERE IdInstructor = '%s'", 
                nombres,
                apellidos,
                grado,
                idInstructor
        );
    }

    @Override
    public String getDeleteSQL() {
        return String.format("DELETE FROM Instructor WHERE IdInstructor = '%s'", idInstructor);
    }
}
