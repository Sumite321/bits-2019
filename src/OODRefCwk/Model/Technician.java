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
public class Technician extends Staff{
    
    private boolean _certified;

    public Technician(String UName, int experience, int retainer, double rate,boolean isC) {
        super(UName, experience, retainer, rate);
        this._certified = isC;
    }  

    public boolean isIsCiscoo() {
        return _certified;
    }

    public void setIsCiscoo(boolean certified) {
        this._certified = certified;
    }
    
    
    
}
