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
public class OnlineSystem {
    //this is the account to login
    private OnlineAccount theLoginAccount;
    
    public OnlineSystem()
    {
        theLoginAccount = null;
    }
    
    //main menu
    public void showMainPage()
    {
        Scanner sc = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            //options
            System.out.println();
            System.out.println("Welcome to UHCL Bank");
            System.out.println("1: Create a new online access ID");
            System.out.println("2: Login your online account");
            System.out.println("x: Leave the Online System");
            System.out.println();
            
            //get input
            selection = sc.next();
            if(selection.equals("1"))
            {
                //sign up
                signUp();
            }
            else if(selection.equals("2"))
            {
                //login
                login();
            }
            else
            {
                ;
            }
            
        }
    }
    
    public void signUp()
    {
        Scanner sc = new Scanner(System.in);
        //get ssn,id,psw
        System.out.println("Please enter your ssn");
        String ssn = sc.next();
        System.out.println("Please enter your new ID");
        String id = sc.next();
        System.out.println("Please enter your selected password");
        String psw = sc.next();
        
        //boolean variables for conditions
        boolean idUsed = false;
        boolean ssnUsed = false;
        
        //we check each onlineaccount in the arraylist
        for(OnlineAccount one : BankkSystem2016Fall.allOnlineAccounts)
        {
            if(id.equals(one.getId()))
            {
                idUsed = true;
                break;
            }
            
            if(ssn.equals(one.getSsn()))
            {
                ssnUsed = true;
                break;
            }
        }
        
        if(idUsed == true)
        {
            //error msg
            System.out.println("*** The Login ID is used! Please select another one!***");
        }
        else if(ssnUsed == true)
        {
            System.out.println("****You have an ID already.Please contact customer service");
        }
        else
        {
            
            //Create new online account
            OnlineAccount aNew = new OnlineAccount(psw, id, psw);
            BankkSystem2016Fall.allOnlineAccounts.add(aNew);
        }
        
        
    }
    
    public void login()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your ID");
        String id = sc.next();
        System.out.println("Please enter your password");
        String password = sc.next();
        
        //id found boolean
        boolean idFound = false;
        
        //search all online account
        for(OnlineAccount one : BankkSystem2016Fall.allOnlineAccounts)
        {
            if(id.equals(one.getId()))
            {
                idFound = true;
                if(password.equals(one.getPsw()))
                {
                    theLoginAccount = one;
                    //go to main menu of the onlineaccount
                    theLoginAccount.welcome();
                    break;
                }
                else
                {
                    System.out.println("**** Your password was incorrect ****");
                }
            }
        }
        
        if(idFound == false)
        {
            System.out.println("*** Your ID was not found");
        }
    }
    
}
