/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk;

import OODRefCwk.Helper.ITManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Provides tests for the basic BITS setup
 * @author comqaam
 */
public class ManagementGeneral {
    
    Management pr;
    
    // Just some local methods
    private boolean containsText(String text, List<String> str) {
        boolean result = true;
        for (String s : str) {
            result = result && text.toLowerCase().contains(s.toLowerCase());
        }
        return result;
    }
    
    private boolean containsText2(String text, String s1, String s2, String s3) {
        boolean result = false;
        result = text.contains(s1) && text.contains(s2) && text.contains(s3) ;
        return result;
    }
    
    public ManagementGeneral() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //create the ITManager object used before each test method
        pr = new ITManager("Olenka",1000);
    }
    
    @After
    public void tearDown() {
    }

//***** Tests for ITManager just after creation
    
    @Test
    public void checkAccountAtStart() {
        //Checks that account is set to the parameter in setUp() above
        assertEquals(1000.0, pr.getAccount(), 0.5);
    }
    
    @Test
    public void checkTeamAtStart() {
        //Checks that team is empty at the start
        assertTrue((pr.getTeam().contains("No staff hired")));
    } 
    
    @Test
    public void isNotOverdrawnAtStart() {
        assertTrue(!pr.isOverdrawn());
    }
    
// ************ Jobs **********
    
    @Test
    public void isInJobRange() {
       // Checks that jobs have been loaded and can retrieve a job
       boolean actual = true;
       for (int x=100; x<=106; x++){
           actual = actual && pr.isJob(x);
       }
       assertTrue(actual);
    }
       
    @Test
    public void isNotInJobRange() {
       // Checks correct response to out of range job numbers
       boolean actual = true;
       int x = 99;
       int y = 108;
       actual = !pr.isJob(x) && !pr.isJob(y);
       assertTrue(actual);
    }
    
    
// Just checking getAllJobs for a few of the Jobs
    @Test
    public void Job100InAllJobs() {
        String str = pr.getAllJobs();
        boolean result = containsText2(str,"Design","3","10");
        assertTrue(result);
    }
    
    @Test
    public void Job104InAllJobs() {
        String str = pr.getAllJobs();
        boolean result2 = containsText2(str,"Software","7", "15");
        assertTrue(result2);
    }
    
    @Test
    public void Job106InAllJobs() {
        String str = pr.getAllJobs();
        boolean result3 = containsText2(str,"Hardware","5","20");
        assertTrue(result3);
    }
    
//************** Staff *********************
// Checks just a few staff details 
    @Test
    public void checkDetailsOfAlan() {
        String mem = pr.getStaffMember("Alan");
        boolean result = containsText(mem,new ArrayList<>(Arrays.asList(
                        "Alan","Analyst","2", "300","30","Available", "false" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfIan() {
        String str = pr.getStaffMember("Ian");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Ian","Analyst","4", "300","60","Available", "true" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfBob() {
        String str = pr.getStaffMember("Bob");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Bob","Technician","2", "100","30","Available", "false" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfCeri() {
        String str = pr.getStaffMember("Ceri");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Ceri","Technician","4", "250","40","Available", "true" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfEla() {
        String str = pr.getStaffMember("Ela");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Ela","Programmer","2", "200","20","Available" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfNonExistantJohn() {
        // Checks for non-existent John
        String str = pr.getStaffMember("John");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "No such staff" )));
        assertTrue(result); 
    } 
    
    @Test
    public void checkAvailableStaffAtStart() {
        // Checks getAvailableStaff()
        String str = pr.getAvailableStaff();
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Alan","Bob", "Ceri","Dan","Ela","Fela","Gita", "Hela" )));
        assertTrue(result); 
    }          
}