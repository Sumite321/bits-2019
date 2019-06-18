/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk.Model;

import OODRefCwk.Model.Staff;

/**
 *
 * @author admin
 */
public class Analyst extends Staff{

    private boolean _isProgrammer;
    
    public Analyst(String Name, int experience, int retainer, double rate, boolean program) {
        super(Name, experience, retainer, rate);
        this._isProgrammer = program;
    }

    public boolean isProgrammer() {
        return _isProgrammer;
    }

    public void setIsProgrammer(boolean canProgram) {
        this._isProgrammer = canProgram;
    }
    
}
