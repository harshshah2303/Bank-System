/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankksystem2016fall;

import java.util.Scanner;

/**
 *
 * @author harshshah2303
 */
public class BankAccountCreator {
    
    public static void createNewBankAccount()
    {
        //get the input
        String ssn;
        double balance;
        
        Scanner input = new Scanner(System.in);
        //prompts the input
        System.out.println("Please enter your ssn");
        ssn = input.next();
        
        System.out.println("Please enter the initial balance");
        balance = input.nextDouble();
        
        //create the object of a bank account
        BankAccount aNew = new BankAccount(ssn, balance);
        BankkSystem2016Fall.allBankAccounts.add(aNew);
    }
    
}
