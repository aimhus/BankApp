/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.text.DecimalFormat;

/**
 *
 * @author Syed Aiman Hussaini, 500902560
 */
public class Account {
    
    private double balance = 0;             
    private String username, password;
    DecimalFormat dec = new DecimalFormat("#.00");
    
    public Account(double balance, String username, String password) {
        this.balance = balance;
        this.username = username;
        this.password = password;
    }
    
    public void deposit(double amount) {
        if(amount > 0)
            balance+=amount;
        else System.out.println("Sorry, you must deposit a positive amount.\n");
    }
    
    public void withdraw(double amount) {
        if(amount > 0)
            balance-=amount;
        else System.out.println("Sorry, you must withdraw a positive amount.\n");
    }
    
    public double getBalance() {
        return Double.valueOf(dec.format(balance));
    }
    
    public void setBalance(double amount) {
        balance = amount;
    }
    
    public void onlinePurchase(double amount) {
        if(amount >= 50)
            balance = balance;          
    }
}
