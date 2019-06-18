package OODRefCwk;

import OODRefCwk.Helper.ITManager;
import java.io.*;
import java.util.*;
/**
 * provides a command line interface
 * 
 * @author A.A.Marczyk
 * @version 20/05/25 
 */
public class ManagerUI
{
    private static Management _management;
    private static Scanner scanner = new Scanner(System.in);
    
    
    public static void main(String[] args)
    {
        int choice;
        String playerName;

        try
        {
            System.out.println("Enter manager's name");
            String managerName = scanner.nextLine();
            _management = new ITManager(managerName,1000); // create
            choice = 100;
            while (choice != 0)
            {
                choice = getMenuItem();
                if (choice == 1)
                {
                    System.out.println(_management.toString());
                }
                else if (choice == 2)
                {
                    System.out.println(_management.getAvailableStaff());
                }
                else if (choice == 3)
                {
                    System.out.println("Enter Staff name");
                    String nme = (scanner.nextLine()).trim();
                    if(!_management.isOverdrawn())
                    {
                        System.out.println(_management.hireStaff(nme));
                    }
                    else
                    {
                        System.out.println("Project is overdrawn");
                    }
                } 
                else if (choice == 4)
                {
                    System.out.println(_management.getTeam());
                }
                else if (choice == 5)
                {
                    System.out.println(_management.getAllJobs());
                }
                else if (choice == 6)
                {   
                    System.out.println("Enter number of the job");
                    String jobn = (scanner.nextLine()).trim();
                    System.out.println(_management.doJob(Integer.valueOf(jobn)));
                }              
                else if (choice == 7)
                {
                    System.out.println("Enter Staff name");
                    String nme = (scanner.nextLine()).trim();
                    if(_management.isInTeam(nme))
                    {
                        System.out.println(_management.staffRejoinTeam(nme));
                    }  
                }

            }     
        }
        catch (IOException e) {System.out.println (e);}   
        System.out.println("Thank-you");
    }
    
    private static int getMenuItem()throws IOException
    {   
        int choice = 100;  
        System.out.println("Main Menu");
        System.out.println("0. quit");
        System.out.println("1. list project information");
        System.out.println("2. list staff available for hire");
        System.out.println("3. hire staff for team"); 
        System.out.println("4. list staff in the team");
        System.out.println("5. list jobs ");
        System.out.println("6. do a job");
        System.out.println("7. staff rejoin team from holiday");
        
        while (choice < 0 || choice  > 7)
        {
            System.out.println("Enter the number of your choice");
            choice =  scanner.nextInt();
            scanner.nextLine();
        }
        return choice;        
    }  
}