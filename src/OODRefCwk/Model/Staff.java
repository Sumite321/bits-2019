/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk.Model;

import OODRefCwk.Enum.StaffState;

/**
 *
 * @author admin
 */
public abstract class Staff {
    
    private String _name;
    private int _experience;
    private int _retainer;
    private double _rate;
    private StaffState _state;

    public Staff(String UName, int experience, int retainer, double rate) {
        this._name = UName;
        this._experience = experience;
        this._retainer = retainer;
        this._rate = rate;
    }

    public String getUName() {
        return _name;
    }

    public int getExperience() {
        return _experience;
    }

    public void setUName(String UName) {
        this._name = UName;
    }

    public void setExperience(int experience) {
        this._experience = experience;
    }

    public void setRetainer(int retainer) {
        this._retainer = retainer;
    }

    public void setRate(double rate) {
        this._rate = rate;
    }

    public int getRetainer() {
        return _retainer;
    }

    public double getRate() {
        return _rate;
    }

    public StaffState getState() {
        return _state;
    }

    public void setState(StaffState state) {
        this._state = state;
    }

}
