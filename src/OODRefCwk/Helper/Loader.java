/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk.Helper;

import OODRefCwk.Enum.JobType;
import OODRefCwk.Enum.StaffState;
import OODRefCwk.Model.Analyst;
import OODRefCwk.Model.Designer;
import OODRefCwk.Model.Programmer;
import OODRefCwk.Model.Technician;
import OODRefCwk.Model.Staff;
import OODRefCwk.Model.Job;
import static OODRefCwk.Repository.Collections._allJobs;
import static OODRefCwk.Repository.Collections._staffToHire;


/**
 *
 * @author 
 */
public class Loader {
    
        public void setupStaff() {
        // add all from the specs

        _staffToHire.put("Alan", new Analyst("Alan", 2, 300, 30, false));
        _staffToHire.put("Bob", new Technician("Bob", 2, 100, 30, false));
        _staffToHire.put("Ceri", new Technician("Ceri", 4, 250, 40, true));
        _staffToHire.put("Dan", new Programmer("Dan", 2, 200, 20));
        _staffToHire.put("Ela", new Programmer("Ela", 7, 200, 20));
        _staffToHire.put("Fela", new Analyst("Fela", 6, 300, 90, false));
        _staffToHire.put("Gita", new Programmer("Gita", 2, 200, 20));
        _staffToHire.put("Hela", new Technician("Hela", 8, 450, 40, false));
        _staffToHire.put("Ian", new Analyst("Ian", 4, 300, 60, false));
        
        
        /* Demo Work */ 
        
        _staffToHire.put("Mary", new Analyst("Mary", 5, 300, 75, true));
        _staffToHire.put("Arty", new Designer("Arty", 7, 150, 30, true));

        for (Staff makeAvailable : _staffToHire.values()) {
            makeAvailable.setState(StaffState.AVAILABLE);
        }

    }

    public void setupTasks()
    {
        // add all from the specs
               
        Job job1 = new Job(100,JobType.DESIGN,10,3,200);
        Job job2 = new Job(101,JobType.HARDWARE,20,3,150);
        Job job3 = new Job(102,JobType.SOFTWARE,30,3,100);
        Job job4 = new Job(103,JobType.DESIGN,25,9,250);
        Job job5 = new Job(104,JobType.SOFTWARE,15,7,350);
        Job job6 = new Job(105,JobType.HARDWARE,35,8,300);
        Job job7 = new Job(106,JobType.HARDWARE,20,5,400);
        /* Demo Work */ 
        Job job8 = new Job(107,JobType.DESIGN,25,4,175);

        
        _allJobs.put(job1.getNumber(),job1);
        _allJobs.put(job2.getNumber(),job2);
        _allJobs.put(job3.getNumber(),job3);
        _allJobs.put(job4.getNumber(),job4);
        _allJobs.put(job5.getNumber(),job5);
        _allJobs.put(job6.getNumber(),job6);
        _allJobs.put(job7.getNumber(),job7);
        /* Demo Work */ 
        _allJobs.put(job8.getNumber(),job8);



    }
}
