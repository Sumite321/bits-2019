/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk.Logic;

import OODRefCwk.Enum.JobType;
import OODRefCwk.Enum.StaffState;
import OODRefCwk.Model.Analyst;
import OODRefCwk.Model.Job;
import OODRefCwk.Model.Programmer;
import OODRefCwk.Model.Staff;
import OODRefCwk.Model.Technician;
import static OODRefCwk.Repository.Collections._allJobs;
import static OODRefCwk.Repository.Collections._bestChoice;
import static OODRefCwk.Repository.Collections._teamMembers;
import static OODRefCwk.Helper.ITManager._budget;

/**
 *
 * @author 
 */
public class JobLogic {

    private Double bill = 0.0;
    private String message = "";
    private Staff removeStaff = null;

    public JobLogic() {
    }

    public boolean jobExists(int num) {

        boolean found = false;

        for (Job job : _allJobs.values()) {
            if (job.getNumber() == num) {
                found = true;
            }
        }
        return found;
    }

    public String getAllJobs() {
        StringBuilder jobDetails = new StringBuilder();

        for (Job j : _allJobs.values()) {
            {
                jobDetails.append("Job #: " + j.getNumber() + " "
                        + "Job type: " + j.getType() + " "
                        + "Job difficulty: " + j.getLevel() + " "
                        + "Job Penalty: " + j.getPenalty() + " "
                        + "Hours : " + j.getHours() + "\n"
                );
            }
        }
        return jobDetails.toString();
    }

    public Job getJobReference(int jobNo) {
        Job found = null;

        if (jobExists(jobNo)) {

            for (Job job : _allJobs.values()) {

                if (job.getNumber() == jobNo) {

                    found = job;
                }
            }

        }

        return found;
    }

    public String completeJob(int jobNo) {

        bill = 0.0;
        _bestChoice.clear();
        if (jobExists(jobNo)) {
            // Get the job reference
            Job jobRef = getJobReference(jobNo);
            // Get the job type
            JobType type = jobRef.getType();
            // Go through the collection
            for (Staff staff : _teamMembers.values()) {
                // check if staff is not in Holidays
                if (staff.getState() != StaffState.ONHOLIDAY) {
                    // if the type is Software OR Design
                    if (type == JobType.SOFTWARE || type == JobType.DESIGN) {

                        // We check if a programmer exists
                        // and test their level of experience
                        if (staff instanceof Programmer) {
                            // staff not qualified
                            if (jobRef.getLevel() >= staff.getExperience()) {
                                message = "not completed due to staff inexperience";
                                //get the penalty as a negative integer
                                bill = jobRef.getPenalty() * -1.0;

                                // add into the collection
                                _bestChoice.put(2, bill);
                            } else {
                                message = "Job completed by" + staff.getUName(); // job completed

                                // add the reward
                                bill = staff.getRate() * jobRef.getHours();
                                _bestChoice.put(1, bill);
                            }
                        }

                        // We check if a Analyst exists
                        // test their level of experience
                        // and eligibility of the job type
                        if (staff instanceof Analyst) {

                            if (jobRef.getLevel() >= staff.getExperience()) {
                                message = "not completed due to staff inexperience";
                                bill = jobRef.getPenalty() * -1.0;
                                _bestChoice.put(2, bill);
                            } else {

                                // if the Analyst can program and job type is Software
                                if (((Analyst) staff).isProgrammer() && type == JobType.SOFTWARE) {
                                    message = "Job completed by" + staff.getUName();
                                    bill = staff.getRate() * jobRef.getHours();
                                    _bestChoice.put(1, bill);

                                } else {
                                    message = "Job completed by" + staff.getUName();
                                    bill = staff.getRate() * jobRef.getHours();
                                    _bestChoice.put(1, bill);
                                }
                            }
                        }

                        // technicians cannot do Software or Design job
                        if (staff instanceof Technician) {
                            message = "No staff available";
                            bill = jobRef.getPenalty() * -1.0;
                            _bestChoice.put(3, bill);
                        }

                    } // jobtype is hardware, only Technician can do
                    else if (type == JobType.HARDWARE && staff instanceof Technician) {
                        if (jobRef.getLevel() >= staff.getExperience()) {
                            message = "not completed due to staff inexperience";
                            bill = jobRef.getPenalty() * -1.0;
                            _bestChoice.put(2, bill);
                        } else {
                            message = "Job completed by" + staff.getUName();
                            bill += staff.getRate() * jobRef.getHours();
                            _bestChoice.put(1, bill);
                            removeStaff = staff;
                            staff.setState(StaffState.ONHOLIDAY);
                        }
                    } // for any other cases
                    else {
                        message = "No staff available";
                        bill = jobRef.getPenalty() * -1.0;
                        _bestChoice.put(3, bill);
                    }
                } else {
                    message = "No staff available";
                    bill = jobRef.getPenalty() * -1.0;
                    _bestChoice.put(3, bill);
                }
            }
        } else {
            message = "No such Job";
        }
        
        pickBestChoice();
                        System.out.println(_teamMembers);


        return message;
    }

    private void pickBestChoice() {
        /* */
        if (_teamMembers.size() > 1) {
            if (_bestChoice.containsKey(1)) {
                _budget = _budget + _bestChoice.get(1);
                _teamMembers.remove(removeStaff.getUName());
                removeStaff.setState(StaffState.ONHOLIDAY);
                message = "Job Completed by" + removeStaff.getUName();
            } else if (_bestChoice.containsKey(2)) {
                _budget = _budget + _bestChoice.get(2);
                message = "not completed due to staff inexperience";
            } else if (_bestChoice.containsKey(3)) {
                _budget = _budget + _bestChoice.get(3);
                message = "not completed due to staff inexperience";
            }

        } else {
            _budget = _budget + bill;
        }

    }

}
