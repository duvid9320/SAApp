/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author dave
 */
public class SAOutput {
    public static void showErrorMessage(String msg, String title){
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showErrorMessage(String msg){
        showErrorMessage(msg, "Error");
    }
    
    public static void showInformationMessage(String msg, String title){
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showInformationMessage(String msg){
        showInformationMessage(msg, "Operación realizada con éxito");
    }
    
    public static String showInputDialog(String msg){
        return JOptionPane.showInputDialog(null, msg);
    }
}
