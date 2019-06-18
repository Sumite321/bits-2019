package OODRefCwk; 

import OODRefCwk.Helper.ITManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManagementHiring
{
    Management pr;
    public ManagementHiring() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pr = new ITManager("Olenka",1000);
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void hireStaff(){
        int expected = 700;
        pr.hireStaff("Alan");
        assertTrue(expected == pr.getAccount());
    }
    
    @Test
    public void hiredStaffInTeam() {
        int expected = 600;
        pr.hireStaff("Alan");
        pr.hireStaff("Bob");
        boolean budgetOK = (expected == pr.getAccount());
        assertTrue(pr.isInTeam("Alan") && pr.isInTeam("Bob")&& budgetOK);
    }
    
    @Test
    public void hiredStaffNotAvailable() {
        boolean result = true;
        pr.hireStaff("Alan");
        pr.hireStaff("Bob");
        String actual = pr.getAvailableStaff();
        result = !(actual.contains("Alan") || actual.contains("Bob"));
        assertTrue(result);
    }
    
    @Test 
    public void notEnoughMoney() {
        pr.hireStaff("Alan");
        pr.hireStaff("Dan");
        pr.hireStaff("Hela");
        pr.hireStaff("Fela");
        boolean result = (pr.getAccount()==50);
        result = result && !pr.isInTeam("Fela");
        assertTrue(result);
    }
    
    @Test 
    public void notSuchStaff() {
        pr.hireStaff("John");
        boolean result = (pr.getAccount()==1000);
        result = result && !pr.isInTeam("John");
        assertTrue(result);
    }
}
