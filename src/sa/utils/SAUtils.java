/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sa.model.to.AlumnoTO;

/**
 *
 * @author Dave
 */
public class SAUtils {
    
    public static Date getDateFromMySQLDateString(String date){
        try {
            return new SimpleDateFormat("YYYY-MM-dd").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(SAUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Date getHourDate(String hour){
        try {
            return new SimpleDateFormat("HH:mm:ss").parse(hour);
        } catch (ParseException ex) {
            Logger.getLogger(SAUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getFormattedDate(Date date){
        return new SimpleDateFormat("YYYY-MM-dd").format(date);
    }
    
    public static String getFormattedTime(Date date){
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }
    
    public static boolean isValidString(String str){
        return str != null && !str.trim().isEmpty();
    }
    
    public static boolean stringsAreValid(String... str){
        return Arrays.stream(str).allMatch(s -> isValidString(s));
    }
    
    public static byte[] getBytesArrayFromObject(Object o){
        byte[] array = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
          out = new ObjectOutputStream(bos);   
          out.writeObject(o);
          out.flush();
          array = bos.toByteArray();
        } 
        catch (IOException ex) {
            Logger.getLogger(AlumnoTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
          try {
            bos.close();
          } catch (IOException ex) {
            // ignore close exception
          }
        }
        return array;
    }
}
