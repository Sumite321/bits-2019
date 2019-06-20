/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk;

import OODRefCwk.Helper.ITManager;

/**
 *
 * @name femi
 */
public class DemoRefDef {
    
    static Management myDemo = new ITManager("Zen",1000);
    
    
    public static void main(String[] args){
    
        task2();
  
    }
    
    public static void task2(){
        // display all staff available for hire and jobs
        System.out.println(myDemo.getAvailableStaff());
        System.out.println(myDemo.getAllJobs());
        
        System.out.println("*****************************");
        //*****************
        //hire Ceri, Fela, Ela, Zenon, Hela
        System.out.println(myDemo.hireStaff("Ceri"));
        System.out.println(myDemo.hireStaff("Fela"));
        System.out.println(myDemo.hireStaff("Ela"));
        System.out.println(myDemo.hireStaff("Zenon"));
        System.out.println(myDemo.hireStaff("Hela"));
        // display the state of the project
        System.out.println(myDemo.toString());
        // display staff available for hire
        System.out.println(myDemo.getAvailableStaff());
        
        System.out.println("**********************");
        // ************
        // project team
        System.out.println(myDemo.getTeam());
        //**********
        System.out.println("**********************");


        // do jobs 102, 103, 101, 105
        System.out.println(myDemo.doJob(102));
        
        System.out.println(myDemo.doJob(103));
       
        System.out.println(myDemo.doJob(101));
        System.out.println(myDemo.doJob(105));

        // display team, do job 101
        System.out.println(myDemo.getTeam());
        System.out.println(myDemo.doJob(101));

        // Ceri returns from holiday
        System.out.println(myDemo.staffRejoinTeam("Ceri"));
        // do job 101
        System.out.println(myDemo.doJob(101));  
    
    }
    
    public void print(String message){
        System.out.println(message);
    }
    
    
}
