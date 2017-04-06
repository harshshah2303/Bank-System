/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Denny Desktop
 */
@ManagedBean
@RequestScoped
public class Registration {

    private String id;
    private String password;
    private String ssn;

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getSsn() {
        return ssn;
    }
    
    public String register()
    {
        //load the driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
         
         
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/drlin";
            
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "DrLin", "UHCL2014");   
            statement = connection.createStatement();
            //to search an onlineaccount based on id or ssn
            resultSet = statement.executeQuery("Select * from onlineaccount "
                    + "where id = '" + 
                    id + "' or ssn = '" + ssn + "'" );
            
            if(resultSet.next())
            {
                 return("Either you have an online account already "
                        + "or your online ID is not available to register");
            }
            else
            {
                int r = statement.executeUpdate("insert into onlineAccount "
                        + "values ('" + id + "', '" + ssn + "', '" 
                    + password + "')");
                return ("Registration Successful! Please "
                         + "return to login your account.");
                
            }   
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("Internal Error! Please try again later.");
             
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
                
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }
    
         
    }
     
     
}
