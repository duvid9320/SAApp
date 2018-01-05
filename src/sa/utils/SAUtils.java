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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import sa.model.to.AlumnoTO;

/**
 *
 * @author Dave
 */
public class SAUtils {
    public static final String MYSQL_DATE_FORMAT;
    public static final String MYSQL_TIME_FORMAT;
    public static final Long ZERO_HOUR;
    static{
        MYSQL_DATE_FORMAT = "YYYY-MM-dd";
        MYSQL_TIME_FORMAT = "HH:mm:ss";
        ZERO_HOUR = 21600000L;
    }
    
    public static void sout(Object... os){
        Arrays.stream(os)
                .filter(o -> o != null)
                .forEach(System.out::println);
    }
    
    public static void addDocumentListener(LinkedHashMap<JTextComponent, Consumer> jtfcs, Consumer alwaysDo){
        DocumentListener dc = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                switchEventAction(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                switchEventAction(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                switchEventAction(e);
            }
            
            void switchEventAction(DocumentEvent e){
                jtfcs.entrySet().stream()
                                    .filter(a -> SAUtils.isJTComponentEdited(e, a.getKey()))
                                    .findFirst()
                                    .ifPresent(a -> a.getValue().andThen(alwaysDo).accept(a));
            }
        };
        jtfcs.entrySet().forEach(j -> j.getKey().getDocument().addDocumentListener(dc));        
    }
    
    public static boolean isAnyEdited(DocumentEvent e, JTextComponent... jtfs){
        return Arrays.stream(jtfs).anyMatch(j -> e.getDocument() == j.getDocument());
    }
    
    public static boolean isJTComponentEdited(DocumentEvent e, JTextComponent jtf){
        return e.getDocument() == jtf.getDocument();
    }
    
    public static void addDocumentListener(DocumentListener l, JTextComponent... jtfs){
        Arrays.stream(jtfs).forEach( j -> j.getDocument().addDocumentListener(l));
    }
    
    private static String getFormattedConditions(HashMap<String, JTextComponent> conditions){
        String format = "WHERE "+String.join(
                            " AND ", 
                            conditions.entrySet()
                                    .stream()
                                    .filter(c -> !c.getValue().getText().trim().isEmpty())
                                    .map(c -> String.format("%s LIKE '%%%s%%'", c.getKey(),c.getValue().getText().trim()))
                                    .collect(Collectors.toList()
                        )
        );
        return format.trim().endsWith("WHERE") ? "" : format;
    }
    
    private static String getFormattedFields(LinkedHashMap<String, String> fields){
        return String.join(
                ", ", 
                fields.entrySet()
                        .stream()
                        .map( f -> f.getValue().isEmpty() ? f.getKey() : String.format("%s AS '%s'", f.getKey(), f.getValue()))
                        .collect(Collectors.toList())
        );
    }
    
    public static String getQuery(LinkedHashMap<String, String> fields, String table, HashMap<String, JTextComponent> conditions){
        return String.format(
                "SELECT %s FROM %s %s",
                fields != null ? getFormattedFields(fields) : "*", 
                table,
                getFormattedConditions(conditions)
        );
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
        return new SimpleDateFormat(MYSQL_DATE_FORMAT).format(date);
    }
    
    public static String getFormattedTime(Date date){
        return new SimpleDateFormat(MYSQL_TIME_FORMAT).format(date);
    }
    
    public static boolean isValidString(String str){
        return str != null && !str.trim().isEmpty();
    }
    
    public static boolean stringsAreValid(String... str){
        return Arrays.stream(str).allMatch(s -> isValidString(s));
    }
    
    public static void initSpinnerHourEditor(JSpinner jsp){
        jsp.setModel(new SpinnerDateModel(new Date(ZERO_HOUR), null, null, Calendar.HOUR_OF_DAY));
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
