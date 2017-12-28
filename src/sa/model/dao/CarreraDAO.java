/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.dao;

import java.util.List;
import sa.model.to.CarreraTO;

/**
 *
 * @author dave
 */
public class CarreraDAO extends GenericDAO<CarreraTO>{
    private static CarreraDAO instance;

    private CarreraDAO() {
    }

    public static CarreraDAO getInstance() {
        return instance != null ? instance : (instance = new CarreraDAO());
    }
    
    public CarreraTO getCarrera(String id){
        return getCarrera(new CarreraTO(id, null));
    }
    
    public CarreraTO getCarrera(CarreraTO carrera){
        return read(carrera, data -> new CarreraTO(data));
    }
    
    public List<CarreraTO> getAllCarreras() {
        return readList("SELECT * FROM Carrera", data -> new CarreraTO(data));
    }
}
