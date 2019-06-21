/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk.Repository;

import OODRefCwk.Model.Job;
import OODRefCwk.Model.Staff;
import java.util.HashMap;

/**
 *
 * @author 
 */
public class Collections {
    
    public static HashMap<Integer,Job> _allJobs = new HashMap<>();// one collection for all jobs
    public static HashMap<String,Staff> _staffToHire = new HashMap<>();// one collection for all hirable staff
    public static HashMap<String,Staff> _teamMembers = new HashMap<>();// one collection for all team members
    public static HashMap<Integer,Double>  _qualifiedStaff = new HashMap<>();// one collection for all jobs
    
    public static void cleanUp(){
    
        _allJobs.clear();
        _staffToHire.clear();
        _teamMembers.clear();
         _qualifiedStaff.clear();
    
    }
}
