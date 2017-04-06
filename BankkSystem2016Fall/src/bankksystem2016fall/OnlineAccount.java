/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankksystem2016fall;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author harshshah2303
 */
public class OnlineAccount {
    
    //attributes
    private String ssn;
    private String id;
    private String psw;
    
    //an arraylist for bank accounts
    private ArrayList<BankAccount> managedAccounts
           = new ArrayList<BankAccount>();
    
    public OnlineAccount(String s,String d, String p)
    {
        ssn = s;
        id = d;
        psw = p;
    }
    
    public void welcome()
    {
       // System.out.println("Welcome!");
       //add related bank accounts
        
        for(BankAccount one: BankkSystem2016Fall.allBankAccounts)
        {
            
            if(ssn.equals(one.getSsn()))
            {
                managedAccounts.add(one);
            }
        }
        
        //menu
        Scanner sc = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            System.out.println("Welcome to your online account");
            for(int i = 0; i < managedAccounts.size(); i++)
            {
                System.out.printf("%s: select Account %s to view\n",
                        i+1, managedAccounts.get(i).getAccountNumber());
            }
            System.out.println("t: Account transfer");
            System.out.println("r: Reset Password");
            System.out.println("x: Sign out");
            System.out.println();
            
            //get the input
            selection = sc.next();
            if(isInteger(selection))
            {
                int intSelection = Integer.parseInt(selection);
                
                if(intSelection >=1 && intSelection <= managedAccounts.size())
                {
                    managedAccounts.get(intSelection-1).showStatement();
                }
            }
            else if(selection.equals("t"))
            {
               //account transfer
                transfer();
            }
            else if(selection.equals("r"))
            {
                //reset password
            }
            else;
            
        }
        
    }
    
    public void transfer()
    {
        Scanner sc = new Scanner(System.in);
        String accountFrom, accountTo;
        double amount;
        
        if(managedAccounts.size() >= 2)
        {
            
            for(int i = 0; i < managedAccounts.size(); i++)
            {
                System.out.printf("%s: select Account %s\n", i+1,managedAccounts.get(i).getAccountNumber());
            }
            System.out.println("Please select the account from");
            accountFrom = sc.next();
            System.out.println("Please select the account to");
            accountTo = sc.next();
            System.out.println("Please indicate the amount of the transfer");
            amount = sc.nextDouble();
            
            if(!accountFrom.equals(accountTo))
            {
                if(isInteger(accountFrom) && isInteger(accountTo))
                {
                    //convert input into integer
                    int intFrom = Integer.parseInt(accountFrom);
                    int intTo = Integer.parseInt(accountTo);
                    
                    if(intFrom >=1 && intFrom <= managedAccounts.size()
                            && intTo >= 1 && intTo <= managedAccounts.size())
                    {
                        
                        if(managedAccounts.get(intFrom - 1).getBalance() >= amount)
                        {
                          managedAccounts.get(intFrom - 1).withdraw(amount);
                          managedAccounts.get(intTo - 1).deposit(amount);
                          System.out.println("The transfer is successful!");
                          System.out.println();
                        }
                        else
                        {
                            System.out.println("****You do not have enough balance");
                        }
                    }
                    else
                    {
                        System.out.println("***Your input is not valid");
                    }
                        
                }
                else
                {
                    System.out.println("***You need to use integer input to select account ");
                }
            }
            else
            {
                System.out.println("****Your input is not valid!");
            }
        }
        else
        {
            System.out.println("***You need to have atleast two bank accounts to do a transfer");
        }
    }
    
    private boolean isInteger(String a)
    {
        try
        {
            int b = Integer.parseInt(a);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public ArrayList<BankAccount> getManagedAccounts() {
        return managedAccounts;
    }

    public void setManagedAccounts(ArrayList<BankAccount> managedAccounts) {
        this.managedAccounts = managedAccounts;
    }
    
    
            
    
}
