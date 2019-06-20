/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk.Model;

/**
 *
 * @author 
 */
public class Designer extends Staff{
    
    private boolean _isProgrammer;
    
    public Designer(String UName, int experience, int retainer, double rate, boolean program) {
        super(UName, experience, retainer, rate);
        this._isProgrammer = program;
    }
    
    public boolean isProgrammer() {
        return _isProgrammer;
    }

    public void setIsProgrammer(boolean canProgram) {
        this._isProgrammer = canProgram;
    }
    
    
}
