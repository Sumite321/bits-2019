package OODRefCwk.Helper;
import OODRefCwk.Helper.Loader;
import OODRefCwk.Enum.JobType;
import OODRefCwk.Enum.StaffState;
import OODRefCwk.Logic.JobLogic;
import OODRefCwk.Management;
import OODRefCwk.Model.Analyst;
import OODRefCwk.Model.Programmer;
import OODRefCwk.Model.Technician;
import OODRefCwk.Model.Staff;
import OODRefCwk.Model.Job;
import java.util.*;
import java.io.*;
import static OODRefCwk.Repository.Collections._allJobs;
import static OODRefCwk.Repository.Collections._staffToHire;
import static OODRefCwk.Repository.Collections._bestChoice;
import static OODRefCwk.Repository.Collections._teamMembers;
import static OODRefCwk.Repository.Collections.cleanUp;
/**
 * This class implements the behaviour expected from the BITS system
 * as required for 6COM1037 Referred Cwk - June 2019 and 
 * specified in the Management interface
 * 
 * @author - add your name and SRN here
 * @author - if working as a pair, add your partner's name & SRN here
 *           (or leave blank, if working individually)
 * @version - add the submission date here
 * @author A.A.Marczyk  
 * @version 24/05/2019
 */
public class ITManager  implements Management 
{
// Declare fields
    private String _managerName;
    public static double _budget;
    private Loader loader;
    private JobLogic _jobLogic;


//**************** BITS ************************** 
    /** Constructor requires the name of the trainee manager and initial _budget. Staff
     * and jobs are also set up,  with all staff set to "available" for hire.
     * @param trainee the name of the trainee manager running the simulation
     * @param _budget the initial _budget allocated to the project account
     */
     public ITManager(String trainee, int _budget)
     {
        this._managerName = trainee;
        this._budget = _budget;
        this._jobLogic = new JobLogic();
        cleanUp();
        loader = new Loader();
        setupTasks();
        setupStaff();
        
     }
    
    
    /**Returns a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, the staff in the team (or, "No staff" 
     * if team is empty)
     * @return a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, and the staff currently in the 
     * team,(or, "No staff" if team is empty)
     **/
    public String toString(){
        
        String isOverdrawn = "Project is not overdrawn";
        
        if(isOverdrawn()){
            isOverdrawn = "Project is overdrawn";
        }
        
        return "Manager name: " + _managerName + "\n" +
                "Available funds: " + _budget + "\n" +
                "Team members: \n" + getTeam() + "\n" + 
                isOverdrawn;
    }
    
    
    /** Returns the amount of money in the account
     * @returns the amount of money in the account
     */
    public double getAccount(){
        return _budget;
    }
    
    /** Returns true if project account <=0 and the team has no staff 
     * who can leave. 
     * @returns true if project account <=0 and the team has no staff 
     * who can leave. 
     */
    public boolean isOverdrawn(){
        
        if(getAccount()<=0 && _teamMembers.isEmpty()){return true;}
        
        return false;
    }

    //********************** All Jobs************************* 
    /** Returns true if the number represents a job
     * @param num is the reference number of the job
     * @returns true if the reference number represents a job
     **/
    public boolean isJob(int num) {

        return _jobLogic.jobExists(num);
    }

    
    /** Returns a String representation of all jobs 
     * @return returns a String representation of all jobs
     **/
    public String getAllJobs() {

        return _jobLogic.getAllJobs();
    }
       
    public Job getJobReference(int jobNo) {
        return _jobLogic.getJobReference(jobNo);
    }
    
    
//*********************** All Staff *************************    
    
    /** Returns details of a staff member with the given name, 
     * (staff may be in or out of the team)
     * @param name the name of the required staff member
     * @return details of a staff member with the name specified 
     * in the parameter
     **/
    public String getStaffMember(String name){
        
        StringBuilder staffDetails = new StringBuilder();

        for (Staff s : _staffToHire.values()) {
            if (staffExists(name)) {
                staffDetails.append("Staff name: " + s.getUName() + " " + 
                        "Experience level: " + s.getExperience() + " " +
                        "Retainer: " + s.getRetainer() + " " + 
                        "Hourly rate " + s.getRate()+ " " + 
                        "State:" + s.getState());
                if (s instanceof Analyst ){staffDetails.append(" " + ((Analyst) s).isProgrammer() + " " + "Analyst");}
                if (s instanceof Technician ){staffDetails.append(" " + ((Technician) s).isIsCiscoo() + " " + "Technician");}
                if (s instanceof Programmer ){staffDetails.append( " " + "Programmer");}

            } else {
                staffDetails.append("No such staff");
            }
        }

        return staffDetails.toString();
    }
    
    private boolean staffExists(String name) {

        boolean found = false;

        for (Staff s : _staffToHire.values()) {
            if (s.getUName().equals(name)) {
                found = true;
            }
        }
        return found;

    }
    
    private Staff getStaffReference(String name) {

        Staff toReturn = null;
        
        if (staffExists(name)) {
            for (Staff s : _staffToHire.values()) {
                if (s.getUName().equals(name)) {
                    toReturn = s;
                }
            }
        }
        return toReturn;
    }
    
       /**Returns a String representation of all staff available for hire
     * @return a String representation of all staff available for hire
     **/
    public String getAvailableStaff(){
        
        StringBuilder availableStaff = new StringBuilder();
        
        for(Staff s: _staffToHire.values()){
            availableStaff.append("Staff name: " + s.getUName() + " " + 
                        "Experience level: " + s.getExperience() + " " +
                        "Retainer: " + s.getRetainer() + " " + 
                        "Hourly rate " + s.getRate() + " " +
                        "State: " + s.getState().toString() + "\n");
        }
        
        return availableStaff.toString();
    }
    
    
 // ***************** Team Staff ************************   

    /** Allows staff to be added to the team, if there is enough  
     * money in the account for the retainer.The hired staff member's 
     * state is set to "working" and their retainer is deducted from
     * the project account. Return the result of the hire; all messages 
     * should include the staff name and state of the project account
     * @param name is the name of the staff member
     * @return "Not found" if staff not found, "Already hired" if staff 
     * is already hired, "Not enough money" if not enough money in the 
     * account,"Hired" if staff are hired.All messages should include 
     * the staff name and state of the project account
     **/        
    public String hireStaff(String name) {

        String message = "No staff found";

        if (staffExists(name)) { // check if the staff exists
            Staff toHire = getStaffReference(name); // get the object reference to variable
            if (toHire.getRetainer() <= getAccount()) { //check if there is enough money
                _budget = _budget - toHire.getRetainer(); // deduct the retainer from account
                toHire.setState(StaffState.WORKING);
                _teamMembers.put(toHire.getUName(), toHire);
                message = toHire.getUName() + "has been hired for " + toHire.getRetainer() + "\n" + "Current account balance: " + getAccount();
                _staffToHire.remove(toHire.getUName());
                //_staffToHire.r
            }else{
            message = "Not enough money";
            }
        }

        return message;
    }
    
        
   /** Returns true if the staff with the specified name 
     * is in the team, false otherwise.
     * @param name is the name of the staff
     * @return true if the staff with the name is in the team, 
     * false otherwise.
     **/
    public boolean isInTeam(String name){
      
        boolean found = false;
        
        for (Staff s : _teamMembers.values()) {
            if (s.getUName().equals(name)) {
                found = true;
            } 
        }
        return found;
    }

    private Staff getStaffTeamReference(String name) {

        Staff toReturn = null;
        
        if (isInTeam(name)) {
            for (Staff s : _teamMembers.values()) {
                if (s.getUName().equals(name)) {
                    toReturn = s;
                }
            }
        }
        return toReturn;
    }
    /**Returns a String representation of the staff in the project team
     * (including those on holiday), or the message "No staff hired"
     * @return a String representation of the staff in the project team
     **/
    public String getTeam() {

        StringBuilder showTeam = new StringBuilder();

        if (_teamMembers.isEmpty()) {
            showTeam.append("No staff hired");
        } else {

            for (Staff s : _teamMembers.values()) {
                showTeam.append("Staff name: " + s.getUName() + " "
                        + "Experience level: " + s.getExperience() + " "
                        + "Retainer: " + s.getRetainer() + " "
                        + "Hourly rate: " + s.getRate() + " "
                        + "State: " + s.getState().toString() + "\n");
            }
        }
        return showTeam.toString();
    }

// ***************** Simulation ************************ 
    /** Retrieves the job with the job reference number, or returns "No 
     * such job".If job exists, finds a staff member in the team who can 
     * do the job.The results of doing a job will be one of the following:
     * "No such Job" - with no further action taken." Job completed by..." 
     * - add the cost of the job to account and include name of staff,  
     * - Job not completed as no staff available 
     * - deduct job penalty from account,
     * -Job not completed due to staff inexperience 
     * - deduct penalty from the account.
     * If a job is not completed and the project account becomes negative,
     * add "Project is overdrawn"  to the output. 
     * @param jbNo is the reference number of the job
     * @return a String showing the result of doing the job(as above)
     */
    public String doJob(int jbNo) {

        return _jobLogic.completeJob(jbNo);
    }

  
    /**Staff rejoin the team after holiday by setting state to "working" 
     * @param the name of the staff rejoining the team after holiday
     */
    public String staffRejoinTeam(String name){
        
        String message = "";
        if(isInTeam(name)){
          getStaffTeamReference(name).setState(StaffState.WORKING);
          message = "Status changed to Working";
       }else{
          message = "Staff does not exist";
        
        }
        
        return message;
    }
         //****************** private methods for Task 6.1 functionality*******************
    //*******************************************************************************
    private void setupStaff() {
        // add all from the specs
        loader.setupStaff();

    }

    private void setupTasks()
    {
        // add all from the specs
        loader.setupTasks();

    }
    

}
