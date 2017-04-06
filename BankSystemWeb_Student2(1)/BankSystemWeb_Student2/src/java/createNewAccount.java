/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Denny Desktop
 */
@ManagedBean
@RequestScoped
public class createNewAccount{

    /**
     * Creates a new instance of createNewAccount
     */
    private String ssn;
    private double balance;
   
    public String getSsn() {
        return ssn;
    }

    public double getBalance() {
        return balance;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    
    public String createAccount()
    {
        //load the driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("internalError");
        }
    
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/drlin";
       
        
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results
        
        
        String accountID = "";
        
        try 
        {    
            //connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, "DrLin", 
                    "UHCL2014");   
   
            //create a statement
            statement = connection.createStatement();
            
          resultSet = statement.executeQuery("select * from nextAccountNumber");
            
            if(resultSet.next())
            {   
                accountID = "" + resultSet.getInt(1);
                     
            }
            
            int j = statement.executeUpdate("Update nextAccountNumber set "
                        + "nextID = '" +  (resultSet.getInt(1)+1) + "'");
            
             
            
            DecimalFormat df = new DecimalFormat("##.00");
            String s = DateAndTime.DateTime() + ": Account opened "
                    + "with initial balance $" 
                    + df.format(balance) + "\n";
            
            int r = statement.executeUpdate("insert into BankAccount "
                    + "values ('" + accountID + "', '" + ssn + "', '" 
                    + balance + "', '"+ s + "')");
            
          
            
            return("Congratualtions! Your have created a new bank account. " + 
                    "The new account number is " + accountID + ".");
            
        }
        catch (SQLException e)
        {
             
            e.printStackTrace();
            return ("Internal Error. Please Try Again Later");
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
