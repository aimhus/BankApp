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
public class Customer extends User {
    final private String username, password;
    private Customer c;
    private User role = c;
    private State silver, gold, platinum;
    private Account account;
    private State currentLevel;
    
    public Customer(String username, String password, double balance) {         
        if(balance < 0) {
            throw new IllegalArgumentException("Balance must be positive.");
        }
        
        else {
            this.username = username;
            this.password = password;
            account = new Account(balance, username, password);
            silver = new Silver(this);
            gold = new Gold(this);
            platinum = new Platinum(this);
            currentLevel = silver;
            if(balance >= 10000 && balance < 20000)
                currentLevel = gold;
            if(balance >= 20000)
                currentLevel = platinum;
        }
    }
    
    
    public State getSilverState() {
        return silver;
    }
    
    public State getGoldState() {
        return gold;
    }
    
    public State getPlatinumState() {
        return platinum;
    }
    
    public State getLevel() {
        return currentLevel;
    }
    
    public void setLevel(State level) {
        currentLevel = level;
    }
    
    public String getUsername() {
        return username;
    }
    
    
    public String getPassword() {
        return password;
    }   
    
        
    public void deposit(double amount) {
        account.deposit(amount); 
        System.out.println("(Customer deposit) Current Balance: " + account.getBalance() + "\n");
        if(account.getBalance() >= 10000 && account.getBalance() < 20000) {
            setLevel(gold);
            System.out.println("(Customer deposit) Congratulations! \nYou now qualify for our Gold Membership and have been automatically upgraded.\n"
                    + "All online purchases now have only a $10 surcharge.\n");
        } 
        else if(currentLevel.getBalance() >= 20000) {
            setLevel(platinum);
            System.out.println("(Customer deposit) Congratulations! \nYou now qualify for our Platinum Membership and have been automatically upgraded.\n"
                    + "All online purchases now have no extra surcharge.\n");
        }
    }
    
    public void withdraw(double amount) {
        if(currentLevel.getBalance() >= amount) {
            account.withdraw(amount);
            if(amount>0)
                System.out.println("(Customer withdraw) Transaction Complete.\nNew Balance: $" + currentLevel.getBalance());
            if(currentLevel.equals(platinum) && currentLevel.getBalance() >= 10000 && currentLevel.getBalance() < 20000) {
                setLevel(gold);
                System.out.println("(Customer Withdraw) Sorry! \nYou no longer qualify for our Platinum Membership and have been automatically changed to Gold.\n"
                        + "All online purchases now have a $10 surcharge.\n");
            }
            else if((currentLevel.equals(platinum) || currentLevel.equals(gold)) && currentLevel.getBalance() < 10000) {
                setLevel(silver);
                System.out.println("(Customer withdraw) Sorry! \nYou no longer qualify for your current Membership and have been automatically changed to Silver.\n"
                        + "All online purchases now have a $20 surcharge.\n");
            } 
        }
        else System.out.println("Sorry, you do not have enough funds to complete this transaction.\nCurrent Balance: $" + currentLevel.getBalance());
    }
    
    public double getBalance() {
        return account.getBalance();                      
    }
    
    public void setBalance(double amount) {
        account.setBalance(amount);
    }
    
    public void onlinePurchase(double amount) {
        currentLevel.makeOnlinePurchase(amount);
    }
         
}
