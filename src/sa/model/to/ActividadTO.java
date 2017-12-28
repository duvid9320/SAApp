/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.HashMap;
import sa.model.dao.InstructorDAO;
import sa.utils.SAUtils;

/**
 *
 * @author Dave
 */
public class ActividadTO implements GenericTO{
    private String idActividad;
    private String nombre;
    private String tipo;
    private String descripcion;
    private int horas;
    private InstructorTO instructorFk;

    public ActividadTO(HashMap<String, Object> data){
        this(
                String.valueOf(data.get("IdActividad")), 
                String.valueOf(data.get("Nombre")), 
                String.valueOf(data.get("Tipo")), 
                String.valueOf(data.get("Descripcion")), 
                Integer.parseInt(String.valueOf(data.get("Horas"))), 
                InstructorDAO.getInstance().getInstructor(String.valueOf(data.get("InstructorFk")))
        );
    }

    public ActividadTO(String idActividad, String nombre, String tipo, String descripcion, int horas, InstructorTO instructorFk) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.horas = horas;
        this.instructorFk = instructorFk;
    }
    
    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public InstructorTO getInstructorFk() {
        return instructorFk;
    }

    public void setInstructorFk(InstructorTO instructorFk) {
        this.instructorFk = instructorFk;
    }

    public boolean isValid() {
        return SAUtils.stringsAreValid(idActividad, nombre, tipo) && horas != 0 && instructorFk.isValid();
    }

    @Override
    public String getInsertSQL() {
        return String.format(
                "INSERT INTO Actividad VALUES ('%s', '%s', '%s', '%s', %d, '%s')",
                idActividad,
                nombre,
                tipo,
                descripcion,
                horas,
                instructorFk.getIdInstructor()
        );
    }

    @Override
    public String getQuerySQL() {
        return String.format("SELECT * FROM Actividad WHERE IdActividad = '%s'", idActividad);
    }

    @Override
    public String getUpdateSQL() {
        return String.format(
                "UPDATE Actividad SET Nombre = '%s', Tipo = '%s', Descripcion = '%s', Horas = %d, InstructorFK = '%s' WHERE IdActividad = '%s'",
                nombre,
                tipo,
                descripcion,
                horas,
                instructorFk.getIdInstructor(),
                idActividad
                
        );
    }

    @Override
    public String getDeleteSQL() {
        return String.format("DELETE FROM Actividad WHERE IdActividad = '%s'", idActividad);
    }
    
    public String getQueryHorariosSQL(){
        return String.format(
                "SELECT * FROM Horario WHERE ActividadFk = '%s'",
                idActividad
        );
    }
}
