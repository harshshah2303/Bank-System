/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Denny Desktop
 */
import java.sql.*;
import java.util.ArrayList;
import java.text.*;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author LinJian
 */



public class OnlineAccount {
    
    private String id;   //account ID
    private String password;   //saved password
    private ArrayList<BankAccount> managedAccounts;  //the accounts under this ID;
    private String ssNumber;
    private List<String> managedAccountsID; //The bank accounts #s under this ID;
    private String selectedAccountToView; //the account number selected by user for viewing a statement
    
    //used for reset password
    private String oldPsw;  
    private String newPsw1;
    private String newPsw2;
    //used for account transfer
    private String fromAccount;
    private String toAccount;
    private double transferAmount;
    
    
    //constructor
    public OnlineAccount(String selectedID, String aSSN, String selectedPassword)
    {
        id = selectedID;
        password = selectedPassword;
        ssNumber = aSSN;
        selectedAccountToView = "";
        oldPsw = "";
        newPsw1 = "";
        newPsw2 = "";
        
        managedAccounts = new ArrayList<BankAccount> ();
        managedAccountsID = new ArrayList<String> ();
        
       
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/drlin";
            
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, "DrLin", "UHCL2014");   
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select * from bankAccount where ssn = '" + ssNumber + "'" );
            //add the bank accounts under this SSN
            while(resultSet.next())
            {
                managedAccounts.add(new BankAccount(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), 
                        resultSet.getString(4)));
                //add the account IDs
                managedAccountsID.add(resultSet.getString(1));
            }
        }
        catch (SQLException e)
        {           
            e.printStackTrace();
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

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    
    
    public void setOldPsw(String oldPsw) {
        this.oldPsw = oldPsw;
    }

    public void setNewPsw1(String newPsw1) {
        this.newPsw1 = newPsw1;
    }

    public void setNewPsw2(String newPsw2) {
        this.newPsw2 = newPsw2;
    }
    
    //return the online Account ID
    public String getID()
    {
        return id;
    }
    
    //return the password saved
    public String getPassword()
    {
        return password;
    }

    public String getOldPsw() {
        return oldPsw;
    }

    public String getNewPsw1() {
        return newPsw1;
    }

    public String getNewPsw2() {
        return newPsw2;
    }
    
    public String getSSN()
    {
        return ssNumber;
    }

    public String getSelectedAccountToView() {
        return selectedAccountToView;
    }

    public ArrayList<BankAccount> getManagedAccounts() {
        return managedAccounts;
    }
    

    public List<String> getManagedAccountsID() {
        return managedAccountsID;
    } 
    
    //reset the password
    public void setPassword(String thePassword)
    {
        password = thePassword;
    }

    public void setSelectedAccountToView(String selectedAccountToView) {
        this.selectedAccountToView = selectedAccountToView;
    }

    //return a List of Strings which contains the activities of the account number selected by user
    public List<String> showSelectedStatement()
    {
        int index = -1;
        for(int i=0; i<managedAccounts.size(); i++)
        {
            if(managedAccounts.get(i).getAccountNumber().equals(selectedAccountToView))
            {
                index = i;
                break;
            }
        }
        
        if(index < 0)
        {
            return null;
        }
        else
        {
            return managedAccounts.get(index).showStatement();
        }
        
    }
    //reset password
    public String resetPassword()
    {
         
        boolean newPswOK = false; 
        boolean matchOldPsw = false;
        
        
        
        if(newPsw1.equals(newPsw2))
        {
            newPswOK = true;
        }
        
        if(oldPsw.equals(password))
        {
            matchOldPsw = true;
        }
        
        if(!newPswOK)
        {
            return ("confirmationResetWrong.xhtml");
        }
        else if(!matchOldPsw)
        {
             
            return ("confirmationResetWrong.xhtml");
            
        }
        else
        {
             
               
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/drlin";
            
            Connection connection = null;  //a connection to the database
            Statement statement = null;    //execution of a statement
            ResultSet resultSet = null;   //set of results
            
            try 
            {
                password = newPsw1;
                //connect to the database
                connection = DriverManager.getConnection(DATABASE_URL, "DrLin", "UHCL2014");
                
                //create a statement
                statement = connection.createStatement();
                
                int r = statement.executeUpdate("Update onlineaccount set "
                        + "psw = '" +  password + "' where id= '" + id + "'");
                return("confirmationResetOK");
                
            }
            catch (SQLException e)
            {
                      
                e.printStackTrace();
                return("internalError");
             }
             finally
             {
                try
                {
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
    
    public String accountTransfer()
    {
         
        int toIndex = 0; 
        int fromIndex = 0;
        for(int i=0; i<managedAccounts.size(); i++)
        {
            if(managedAccounts.get(i).getAccountNumber().equals(toAccount))
            {
                toIndex = i;
            }
            
            if(managedAccounts.get(i).getAccountNumber().equals(fromAccount))
            {
                fromIndex = i;
            }
            
        }
            
            
            
        if(managedAccounts.size() < 2)
        {
            fromAccount = managedAccounts.get(0).getAccountNumber();
            toAccount = managedAccounts.get(0).getAccountNumber();
            transferAmount = 0.0;
             return("You must have at least two different online accounts to do account transfer");
        }
        else
        { 
             if(!fromAccount.equals(toAccount))
             {
                 if(managedAccounts.get(fromIndex).getBalance() < transferAmount)
                 {
                     fromAccount = managedAccounts.get(0).getAccountNumber();
                     toAccount = managedAccounts.get(0).getAccountNumber();
                     transferAmount = 0.0;
                     
                     return("The account used to transfer the money from has no enough fund!");
                 }
                 else
                 {
                     managedAccounts.get(toIndex).deposit(transferAmount);
                     managedAccounts.get(fromIndex).withdraw(transferAmount);
                          
                     fromAccount = managedAccounts.get(0).getAccountNumber();
                     toAccount = managedAccounts.get(0).getAccountNumber();
                     transferAmount = 0.0;
                     return("The transfer of money was successful!");
                 }
             }
             else
             {
                 fromAccount = managedAccounts.get(0).getAccountNumber();
                 toAccount = managedAccounts.get(0).getAccountNumber();
                 transferAmount = 0.0;
                 return ("You must select two different accounts");
                             
             }
        }
 
    }
    
    //log out, kill the session and return to the main page
    public String signOut()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";

        
    }
    
}

