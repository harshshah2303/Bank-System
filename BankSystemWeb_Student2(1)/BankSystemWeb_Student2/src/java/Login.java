/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.*;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author harshshah2303
 */
@Named(value = "login")
@ManagedBean
@SessionScoped
//if managed bean is session scope it implements serializable
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    
    //attributes
    private String id;
    private String password;
    //Login contains or its related to an OnlineAccount
    private OnlineAccount theLoginAccount;
    
    //login method
    public String login()
    {
        //load the driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //return to internalerror.xhtml
            return "internalError";
        }
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/DrLin";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try
        {
           conn = DriverManager.getConnection(DB_URL,"DrLin","UHCL2014");
           stat = conn.createStatement();
           rs = stat.executeQuery("select * from OnlineAccount where id = '" + id + "'");
           if(rs.next())
           {
               //id is found,then check  password
               if(password.equals(rs.getString(3)))
               {
                   //password is good
                   //create onlineAccount object
                   theLoginAccount = new OnlineAccount(id, rs.getString(2), password); //attributes id,password,login
                   //go to welcome.xhtml
                   return "welcome";
               }
               else
               {
                   id = "";
                   password = "";
                   //return to loginNotOk.xhtml
                   return "loginNotOK";
               }
           }
           else
           {
               //id not found
               id = "";
               password = "";
               //return to loginNotOk.xhtml
               return "loginNotOK";
           }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            try
            {
                conn.close();
                stat.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OnlineAccount getTheLoginAccount() {
        return theLoginAccount;
    }

    public void setTheLoginAccount(OnlineAccount theLoginAccount) {
        this.theLoginAccount = theLoginAccount;
    }
    
    
    
}
