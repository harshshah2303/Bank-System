/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankksystem2016fall;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author harshshah2303
 */
public class DateAndTime {
    
    public static final String DATE_FORMAT_NOW = "dd-mm-yyyy HH:mm:ss";
    
    //return current date and time as a string
    public static String DateTime()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    
}
