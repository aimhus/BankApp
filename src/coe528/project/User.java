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
public abstract class User {
    private User role;
    private String username, password;
    
    public User getRole() {
        return role;
    }
    
    public void setRole(User role) {
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String name) {
        this.username = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
