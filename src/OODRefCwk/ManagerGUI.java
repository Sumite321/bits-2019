package OODRefCwk;
import OODRefCwk.Helper.ITManager;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the simulation
 * 
 * @author A.A.Marczyk
 * @version 20/05/19
 */
public class ManagerGUI 
{
    private Management mg = new ITManager("Mary",1000);
    private JFrame myFrame = new JFrame("Game GUI");
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton viewBtn = new JButton("List all Staff");
    private JButton hireBtn = new JButton("Hire Staff");
    private JButton teamBtn = new JButton("List Team");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JPanel eastPanel = new JPanel();

    
    public ManagerGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(5,1));
        eastPanel.add(viewBtn);
        eastPanel.add(hireBtn);
        eastPanel.add(teamBtn);
        eastPanel.add(clearBtn);
        eastPanel.add(quitBtn);
       
        viewBtn.setVisible(true);
        teamBtn.setVisible(true);
        hireBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("Jobs");
        menubar.add(fileMenu);
        
        JMenuItem listJobsItem = new JMenuItem("List all Jobs");
        fileMenu.add(listJobsItem);
        listJobsItem.addActionListener(new ListJobsHandler());
        
        JMenuItem doJobItem = new JMenuItem("Do a job");
        fileMenu.add(doJobItem);
        doJobItem.addActionListener(new DoJobHandler());
        
        JMenuItem view = new JMenuItem("View State of project");
        fileMenu.add(view);    
    }

    public static void main(String[] args)
    {
        new ManagerGUI();
    }
    

// Menu item handlers
    
    private class ListJobsHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = mg.getAllJobs();
            listing.setText(xx);
            
        }
    }
    
    private class DoJobHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String inputValue = JOptionPane.showInputDialog("Job no ?: ");
            int jbNo =  Integer.parseInt(inputValue);            
            result = mg.doJob(jbNo);
            JOptionPane.showMessageDialog(myFrame,result);
        }
    }
}
   
