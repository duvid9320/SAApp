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
 * @author David Rodríguez
 */
public class CarreraTO implements GenericTO{
    private String idCarrera;
    private String nombre;

    public CarreraTO(HashMap<String, Object> data) {
        this(String.valueOf(data.get("IdCarrera")), String.valueOf(data.get("Nombre")));
    }
    
    public CarreraTO(String idCarrera, String nombre) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
    }
    
    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isValid() {
        return SAUtils.stringsAreValid(idCarrera, nombre);
    }

    @Override
    public String toString() {
        return idCarrera;
    }

    @Override
    public String getInsertSQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateSQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQuerySQL() {
        return String.format("SELECT * FROM Carrera WHERE IdCarrera = '%s'", idCarrera);
    }

    @Override
    public String getDeleteSQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
