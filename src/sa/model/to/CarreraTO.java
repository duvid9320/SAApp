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
