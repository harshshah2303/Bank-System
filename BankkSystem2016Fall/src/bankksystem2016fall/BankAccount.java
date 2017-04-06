/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankksystem2016fall;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author harshshah2303
 */
public class BankAccount {
    
    private static int nextAccountNuber = 100001;
    
    //attributes of a bank account
    private String ssn;
    private String accountNumber;
    private double balance;
    private ArrayList<String> statement;
    
    
    public BankAccount(String s, double b)
    {
        ssn =s;
        balance = b;
        
        //generate acc no
        accountNumber =  "" + nextAccountNuber;
        nextAccountNuber++;
        
        //statement
        statement = new ArrayList<String>();
        //create the statement activity
        DecimalFormat df = new DecimalFormat("##.00");
        statement.add(DateAndTime.DateTime() + ":Account opened " + 
                "with an initial balance $" + df.format(balance));
    
    }
    
    //deposit
    public void deposit(double da)
    {
        if(da > 0.0)
        {
            balance = balance + da;
            DecimalFormat df = new DecimalFormat("##.00");
            statement.add(DateAndTime.DateTime() + ": Deposit $"+ 
                    df.format(da)+ ". Balance: $" + df.format(balance));      
        }
    }
    
    //withdraw
    public void withdraw(double wa)
    {
        if(balance - wa >= 0.0)
        {
            balance = balance - wa;
            DecimalFormat df = new DecimalFormat("##.00");
            statement.add(DateAndTime.DateTime() + ": Withdraw $"+ 
                    df.format(wa)+ ". Balance: $" + df.format(balance));
            
        }
    }
    
    public void showStatement()
    {
        for(int i = statement.size() - 1; i >=0; i--)
        {
            System.out.println(statement.get(i));
        }
    }

    //below are get and set methods
    public static int getNextAccountNuber() {
        return nextAccountNuber;
    }

    public static void setNextAccountNuber(int nextAccountNuber) {
        BankAccount.nextAccountNuber = nextAccountNuber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<String> getStatement() {
        return statement;
    }

    public void setStatement(ArrayList<String> statement) {
        this.statement = statement;
    }
    
    
            
    
}
