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
public class BankkSystem2016Fall {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList<BankAccount> allBankAccounts
            = new ArrayList<BankAccount>();
    
    public static ArrayList<OnlineAccount> allOnlineAccounts
            = new ArrayList<OnlineAccount>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc =  new Scanner(System.in);
        String selection = "";
        
        //menu
        while(!selection.equals("x"))
        {
            System.out.println();
            System.out.println("Please make your selection");
            System.out.println("1: Open a new Bank Account");
            System.out.println("2: Go to online system");
            System.out.println("3: Display all bank accounts");
            System.out.println("x: Exit the simulation");
            
            //get the input from user
            selection = sc.next();
            System.out.println();
             
            if(selection.equals("1"))
            {
                //create a  bank account
                BankAccountCreator.createNewBankAccount();
            }
            else if(selection.equals("2"))
            {
                //go to online system
                new OnlineSystem().showMainPage();
            }
            else if(selection.equals("x"))
            {
                ;
            }
            else if(selection.equals("3"))
            {
                //Display all bank accounts
                for(BankAccount display : BankkSystem2016Fall.allBankAccounts)
                {
                    System.out.println(display.getAccountNumber());
                    System.out.println(display.getSsn());
                    System.out.println(display.getBalance());
                    System.out.println(display.getStatement());
                    System.out.println();
                           
                }
            }
                
        }
        
        
    }
    
}
