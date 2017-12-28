/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.model.to;

import java.util.HashMap;
import sa.model.to.GenericTO;

/**
 *
 * @author dave
 */
public interface GenericFactoryTO<T extends GenericTO> {
    public T newTO(HashMap data);
}
