/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Syed Aiman Hussaini, 500902560
 */
public class Manager extends User {
    
    /**
     * OVERVIEW: A manager is a mutable class containing
     *          an ArrayList, username, password, role,
     *          File Reader and File Writer.
     * 
     *          A manager object can add and delete customers
     *          from user input or a file. Can also create 
     *          and delete files.
     */
    
    /**
     * Abstraction Function AF(c) is:
     * 
     * A Manager m such that m.username = "admin" &&
     * m.password = "admin" && role = this
     */
    
    /**
     * Rep Invariant RI(c) is:
     * 
     * c.role == Manager m &&
     * for all integers i, c.customerList[i] is a Customer &&
     * for all integers j and k, 
     * c.customerList.get(j).getUsername() != c.customerList.get(k).getUsername()
     */
    
    
    private Manager m = null;
    private final User role;
    private final String username, password;
    File file;
    ArrayList<Customer> customerList = new ArrayList<>();
    String n = "";
    
    public Manager() {
        username = "admin";
        password = "admin";
        role = this;
        
        File f = new File("Customer List");
        
        for(File x : f.listFiles()) {
           try {
                customerList.add(getCustomerFromFile(x));
            } catch (IOException ex) {
                System.out.println("An Error Occurred");
            }
        }
    }
    
    /**
     * Effects: 
     * Modifies:
     * Requires:
     * @return 
     */    
    public Manager getManager() {
        if(m == null)
            m = new Manager();
        return m;
    }
    
    /**
     * Effects: Creates new file, adds c to customerList
     * Modifies: customerList 
     * 
     * 
     * @param c
     * @throws IOException 
     */
    public void addCustomer(Customer c) throws IOException {
       if(!customerList.contains(c)) {
        customerList.add(c);
        file = new File("Customer List\\" + c.getUsername() + ".txt");           
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(c.getUsername() + "\n" + c.getPassword() + "\n" + c.getBalance());
        writer.close(); 
       }
    }
    
    /**
     * Effects: Removes c from customerList, deletes corresponding file
     * Modifies: customerList
     * 
     * @param c
     * @throws IOException 
     */
    public void deleteCustomer(Customer c) throws IOException {
        File f = new File("Customer List\\" + c.getUsername()+".txt");
        if(customerList.contains(c)) {
            customerList.remove(c); 
            f.delete();
        }
    }
    
    /**
     * Effects: Returns true if a file matches the username of a customer
     * 
     * @param name
     * @return 
     */
    public boolean doesFileExist(String name) {
        File f = new File("Customer List\\" + name + ".txt");
        
        return f.exists();
    }
    
    
    /**
     * Effects: Updates customer file with new balance amount
     * Modifies: file
     * @param c
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void updateFileBalance(Customer c) throws FileNotFoundException, IOException {
        FileWriter w = new FileWriter("Customer List\\" + c.getUsername() + ".txt");
        w.write(c.getUsername() + "\n" + c.getPassword() + "\n" + c.getBalance());
        w.close();
    }
    
    /**
     * Effects: Returns a new Customer object from a text file
     * @param x
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
   public Customer getCustomerFromFile(File x) throws FileNotFoundException, IOException {
     
        Scanner scanner;
        String username ="";
        String password = ""; 
        double balance = 0;
        try {
            scanner = new Scanner(x);
            username = scanner.nextLine();
            password = scanner.nextLine();
            balance = Double.parseDouble(scanner.nextLine());
            scanner.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        Customer c1 = new Customer(username,password,balance);
        return c1;
   }
   
   /**
    * Effects: Returns true if Rep Invariant holds true, otherwise returns false
    * @return 
    */
   public boolean repOk() {
       Manager m = new Manager();
       if(m.equals(role)) {
           for (int j = 0; j < customerList.size(); j++) {
               for (int k = 0; k < customerList.size(); k++) {
                   if(customerList.get(j).getUsername().equals(customerList.get(k).getUsername()))
                       return false;
               }
           }
       }
       return true;
   }
   
   /**
    * Effects: Returns a string that checks if username = password = "admin" and role = "manager"
    * @return 
    */
   public String toString() {
       return "Username is: " + m.getUsername() + "; Password is: " + m.getPassword() + "; "
               + "Role is: " + m.getRole();
   }
    
}
