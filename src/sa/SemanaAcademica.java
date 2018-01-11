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
package sa;
import sa.connection.MySQLManager;
import sa.controller.ActividadController;
import sa.controller.AsistenciaController;
import sa.controller.RegistroController;
import sa.model.dao.ActividadDAO;
import sa.model.dao.AlumnoDAO;
import sa.model.dao.AsistenciaDAO;
import sa.model.dao.HorarioDAO;
import sa.model.dao.InstructorDAO;
import sa.model.dao.RegistroDAO;
import sa.view.ActividadView;
import sa.view.AsistenciaView;
import sa.view.RegistroView;

/**
 *
 * @author David Rodríguez
 */
public class SemanaAcademica {
    
    static {
        new Thread(() -> MySQLManager.getInstance()).start();
    }
    
    public static void main(String[] args) {
        //new ActividadController(new ActividadView(), InstructorDAO.getInstance(), ActividadDAO.getInstance());
        //new RegistroController(new RegistroView(), RegistroDAO.getInstance(), AlumnoDAO.getInstance(), ActividadDAO.getInstance());
        new AsistenciaController(HorarioDAO.getInstance(), ActividadDAO.getInstance(), new AsistenciaView(), AsistenciaDAO.getInstance(), AlumnoDAO.getInstance(), RegistroDAO.getInstance());
    }
    
}
