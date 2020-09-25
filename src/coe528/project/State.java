/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Syed Aiman Hussaini, 500902560
 */
public abstract class State {
    
    String level = "";
    public abstract void makeOnlinePurchase(double amount);
    public abstract String getLevel();
    public abstract double getBalance();
}
