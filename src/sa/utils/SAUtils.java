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
package sa.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import sa.model.to.AlumnoTO;

/**
 *
 * @author Dave
 */
public class SAUtils {
    private static final String MYSQL_DATE_FORMAT;
    private static final String MYSQL_TIME_FORMAT;
    
    static{
        MYSQL_DATE_FORMAT = "YYYY-MM-dd";
        MYSQL_TIME_FORMAT = "";
    }
    
    public static Date getDateFromString(String date){
        try {
            return new SimpleDateFormat(MYSQL_DATE_FORMAT).parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(SAUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Date getTimeFromString(String hour){
        try {
            return new SimpleDateFormat(MYSQL_TIME_FORMAT).parse(hour);
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
    
    public static void initSpinnerHourEditor(JSpinner jsp){
        jsp.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        jsp.setEditor(new JSpinner.DateEditor(jsp, MYSQL_TIME_FORMAT));
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
