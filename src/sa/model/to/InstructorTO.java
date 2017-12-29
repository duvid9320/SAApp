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
 * @author Dave
 */
public class InstructorTO implements GenericTO{
    private String idInstructor;
    private String nombres;
    private String apellidos;
    private String grado;

    public InstructorTO() {
    }

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
