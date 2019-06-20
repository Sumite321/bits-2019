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
    
  
    }
    
    public void task2(){
        // display all staff available for hire and jobs
        myDemo.getAvailableStaff();
        myDemo.getAllJobs();
        System.out.println("*****************************");
        //*****************
        //hire Ceri, Fela, Ela, Zenon, Hela
        myDemo.hireStaff("Ceri");
        myDemo.hireStaff("Fela");
        myDemo.hireStaff("Ela");
        myDemo.hireStaff("Zenon");
        myDemo.hireStaff("Hela");
        // display the state of the project
        System.out.println(myDemo.toString());
        // display staff available for hire
        myDemo.getAvailableStaff();
        System.out.println("**********************");
        // ************
        // project team
        myDemo.getTeam();
        //**********
        System.out.println("**********************");


        // do jobs 102, 103, 101, 105
        myDemo.doJob(102);
        myDemo.doJob(103);
        myDemo.doJob(101);
        myDemo.doJob(105);

        // display team, do job 101
        myDemo.getTeam();
        myDemo.doJob(101);

        // Ceri returns from holiday
        myDemo.staffRejoinTeam("Ceri");
        // do job 101
        myDemo.doJob(101);  
    
    }
    
    
}
