/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk.Model;

import OODRefCwk.Enum.JobType;

/**
 *
 * @author admin
 */
public class Job {
    
    private int _number;
    private JobType _jobType;
    private double _hours;
    private int _level;
    private int _penalty;

    public Job(int UNumber, JobType type, double hours, int level, int penalty) {
        this._number = UNumber;
        this._jobType = type;
        this._hours = hours;
        this._level = level;
        this._penalty = penalty;
    }

    public int getNumber() {
        return _number;
    }

    public void setNumber(int UNumber) {
        this._number = UNumber;
    }

    public JobType getType() {
        return _jobType;
    }

    public void setType(JobType type) {
        this._jobType = type;
    }

    public double getHours() {
        return _hours;
    }

    public void setHours(double hours) {
        this._hours = hours;
    }

    public int getLevel() {
        return _level;
    }

    public void setLevel(int level) {
        this._level = level;
    }

    public int getPenalty() {
        return _penalty;
    }

    public void setPenalty(int penalty) {
        this._penalty = penalty;
    }
    
    
    
    
}
