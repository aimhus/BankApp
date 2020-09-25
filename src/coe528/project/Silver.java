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
public class Silver extends State {
    
    private String level = "Silver";
    Customer c;
    
    public Silver(Customer c) {
        this.c = c;
    }
    
    public void makeOnlinePurchase(double amount) {
        if(amount >= 50) {
            c.withdraw(amount + 20);
        }
        else System.out.println("(Silver makeOnline) Purchase must be more than $50. No changes made.\n");        
    }
    
    public String getLevel() {
        return level;
    }
    
    public double getBalance() {
        return c.getBalance();
    }
}
