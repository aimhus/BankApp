/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.layout.VBox;


/**
 *
 * @author Syed Aiman Hussaini, 500902560
 */
public class ApplicationGUI extends Application {
    
    Manager m = new Manager();
    StackPane root = new StackPane();
    Scene scene1, scene2, scene3;
    
    int addCheck = 0;
    int delCheck = 0;
    int listCheck1 = 0, cCheck = 0;
    int index;
    Customer d;
    Customer cust = new Customer("bob", "s", 100);
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Text cName = new Text();
        Text curBal = new Text();
        Text curLev = new Text();
        Button withdraw = new Button();
        TextField depNum = new TextField();
        
        
        
        // SCENE 1: LOGIN SCREEN
        
        Text title = new Text();
        title.setText("Banking Application");
        title.setFont(new Font(36));
        title.setTranslateX(350);
        title.setTranslateY(175);
        
        
        Button btn = new Button();
        btn.setTranslateX(500);
        btn.setTranslateY(355);
        btn.setText("Login");
        
        VBox v1 = new VBox(20);
        v1.getChildren().add(title);
        

        v1.getChildren().add(btn);
        
        Text text = new Text();
        Text password = new Text();
        text.setFont(new Font(16));
        password.setFont(new Font(16));
        
        text.setTranslateX(350);
        text.setTranslateY(200);
        password.setTranslateX(350);
        password.setTranslateY(220);
        text.setText("Username:");
        password.setText("Password:");
        v1.getChildren().add(text);
        v1.getChildren().add(password);
        
        TextField uname = new TextField();
        uname.setPromptText("Username");
        uname.setMaxWidth(150);
        uname.setTranslateX(450);
        uname.setTranslateY(115);
        
        Label message = new Label();
        message.setTranslateX(445);
        message.setTranslateY(170);
        final PasswordField pass = new PasswordField();
        pass.setPromptText("Password");
        pass.setMaxWidth(150);
        pass.setTranslateX(450);
        pass.setTranslateY(130);
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(uname.getText().equals("admin") && pass.getText().equals("admin")) {
                   primaryStage.setScene(scene2);
                }
                
                else {
                    for (int i = 0; i < m.customerList.size(); i++) {
                        if(uname.getText().equals(m.customerList.get(i).getUsername()) &&
                           pass.getText().equals(m.customerList.get(i).getPassword())) {
                            
                            cCheck = 1;
                            cust = m.customerList.get(i);
                            cName.setText("Welcome, " + cust.getUsername());
                            curBal.setText("$" + cust.getBalance());
                            curLev.setText(cust.getLevel().getLevel());
                            
                            primaryStage.setScene(scene3);
                            break;
                        }
                    }
                    
                    if(cCheck == 0)
                        message.setText("Incorrect Username/Password");
                }
                
            }
        });
        
        
        v1.getChildren().add(uname);
        v1.getChildren().addAll(pass, message);
        scene1 = new Scene(v1, 1024, 576);
        
        
                    
        
        // SCENE 2: MANAGER SCREEN 
        
        
        VBox r = new VBox(20);
        scene2 = new Scene(r, 1024, 576);
        
        Label e1 = new Label();
        e1.setTranslateX(150);
        e1.setTranslateY(-125);
        
        Label e2 = new Label();
        e2.setTranslateX(90);
        e2.setTranslateY(75);
               
        Text addC = new Text();
        addC.setText("Add Customer:");
        addC.setTranslateX(50);
        addC.setTranslateY(75);
        addC.setFont(new Font(16));
        
        TextField addU = new TextField();
        addU.setPromptText("Username");
        addU.setMaxWidth(150);
        addU.setTranslateX(80);
        addU.setTranslateY(100);
        
        TextField addP = new TextField();
        addP.setPromptText("Password");
        addP.setMaxWidth(150);
        addP.setTranslateX(240);
        addP.setTranslateY(55);
        
        ListView cList = new ListView();
        cList.setTranslateX(700);
        cList.setTranslateY(-225);
        cList.setMaxWidth(200);

        
        Button add = new Button();
        add.setText("Add");
        add.setTranslateX(450);
        add.setTranslateY(-80);
        add.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(!addU.getText().equals("") && !addP.getText().equals("") && !m.doesFileExist(addU.getText())) {
                    for (int i = 0; i < m.customerList.size(); i++) {
                        if(addU.getText().equals(m.customerList.get(i).getUsername())) {
                           
                            addCheck = 1;
                            break;          
                            
                        }
                    }
                    
                    if(addCheck == 0) {
                        Customer c = new Customer(addU.getText(), addP.getText(), 100);
                        e1.setText("");
                        try {
                            m.addCustomer(c);
                        } catch (IOException ex) {
                            e1.setText("IOException");
                        }
                    }
                    
                    else {
                        e1.setText("A Customer with that username already exists. \nPlease enter a unique name.");
                    }
                }
                
                else { e1.setText("Enter a username and password"); }
              
                    for (int i = 0; i < m.customerList.size(); i++) {
                        if(!cList.getItems().contains(m.customerList.get(i).getUsername()))
                             cList.getItems().add(m.customerList.get(i).getUsername());
                }
            }
        });
        
        
        
        Text delC = new Text();
        delC.setText("Delete Customer:");
        delC.setTranslateX(50);
        delC.setTranslateY(175);
        delC.setFont(new Font(16));
        
        TextField delU = new TextField();
        delU.setPromptText("Username");
        delU.setTranslateX(75);
        delU.setTranslateY(200);
        delU.setMaxWidth(150);
        
        Button del = new Button();
        del.setText("Delete Customer");
        del.setTranslateX(300);
        del.setTranslateY(110);
        
        
        del.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(!delU.getText().equals("")) {
                    for (int i = 0; i < m.customerList.size(); i++) {
                        if(delU.getText().equals(m.customerList.get(i).getUsername())) {
                           
                            delCheck = 1;
                            d = m.customerList.get(i);
                            break;
                        }
                    }
                    
                    if(m.doesFileExist(delU.getText())) {
                        delCheck = 1;
                    }
                    
                    if(delCheck == 1) {
                        try {
                            cList.getItems().remove(d.getUsername());
                            m.deleteCustomer(d);
                            e2.setText("");
                        } catch (IOException ex) {
                            e2.setText("IOException");
                        }
                    }
                    
                    else {
                        e2.setText("No Customers with that username exist.\nPlease enter an existing customer's name.");
                    }
                }
                
                else { e2.setText("Enter a username"); }
                
                for (int i = 0; i < m.customerList.size(); i++) {
                    if(!cList.getItems().contains(m.customerList.get(i).getUsername()))
                         cList.getItems().add(m.customerList.get(i).getUsername());
                 } 
            }
                        
        });
        
        
        Button logout = new Button();
        logout.setText("Logout");
        logout.setTranslateX(490);
        logout.setTranslateY(45);
        logout.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               primaryStage.setScene(scene1);
            }
        });
        
        
        Text list1 = new Text();
        list1.setText("Customer List:");
        list1.setFont(new Font(16));
        list1.setTranslateX(700);
        list1.setTranslateY(-400);
      
        
        
        r.getChildren().add(addC);
        r.getChildren().add(addU);
        r.getChildren().add(addP);
        r.getChildren().add(delC);
        r.getChildren().add(delU);
        r.getChildren().add(add);
        r.getChildren().add(del);
        r.getChildren().add(e1);
        r.getChildren().add(e2);
        r.getChildren().add(logout);            
        r.getChildren().add(cList);            
        r.getChildren().add(list1);        
        
        
        

        
        // SCENE 3: CUSTOMER SCREEN
        
        VBox v3 = new VBox(20);
        scene3 = new Scene(v3, 1024, 576);

        cName.setText("Welcome, " + cust.getUsername());
        cName.setFont(new Font(30));
        cName.setTranslateX(400);
        cName.setTranslateY(40);
        
        Text bal = new Text();
        bal.setText("Current Balance:");
        bal.setFont(new Font(20));
        bal.setTranslateX(675);
        bal.setTranslateY(-50);
                
        curBal.setText("$" + cust.getBalance());
        curBal.setFont(new Font(20));
        curBal.setTranslateX(715);
        curBal.setTranslateY(-60);
        
        Text dep = new Text();
        dep.setText("Deposit Funds:");
        dep.setFont(new Font(16));
        dep.setTranslateX(100);
        dep.setTranslateY(100);
        
        depNum.setMaxWidth(100);
        depNum.setTranslateX(115);
        depNum.setTranslateY(90);
        depNum.setPromptText("Amount (in $)");
                
        Button deposit = new Button();
        deposit.setText("Deposit");
        deposit.setTranslateX(230);
        deposit.setTranslateY(45);
        deposit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                cust.deposit(Double.valueOf(depNum.getText()));
                curBal.setText("$" + cust.getBalance());
                curLev.setText(cust.getLevel().getLevel());
                try {
                    m.updateFileBalance(cust);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        Text with = new Text();
        with.setText("Withdraw Funds:");
        with.setFont(new Font(16));
        with.setTranslateX(100);
        with.setTranslateY(-80);
        
        TextField withNum = new TextField();
        withNum.setMaxWidth(100);
        withNum.setTranslateX(115);
        withNum.setTranslateY(0);
        withNum.setPromptText("Amount (in $)");
        
        withdraw.setText("Withdraw");
        withdraw.setTranslateX(230);
        withdraw.setTranslateY(140);
        withdraw.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                cust.withdraw(Double.valueOf(withNum.getText()));
                curBal.setText("$" + cust.getBalance());
                curLev.setText(cust.getLevel().getLevel());
                try {
                    m.updateFileBalance(cust);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        Text on = new Text();
        on.setText("Make Online Purchase:");
        on.setFont(new Font(16));
        on.setTranslateX(100);
        on.setTranslateY(0);
        
        TextField onNum = new TextField();
        onNum.setMaxWidth(100);
        onNum.setTranslateX(115);
        onNum.setTranslateY(-10);
        onNum.setPromptText("Amount (in $)");
        
        Button online = new Button();
        online.setText("Purchase");
        online.setTranslateX(230);
        online.setTranslateY(-55);
        online.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                cust.getLevel().makeOnlinePurchase(Double.valueOf(onNum.getText()));
                curBal.setText("$" + cust.getBalance());
                curLev.setText(cust.getLevel().getLevel());
                try {
                    m.updateFileBalance(cust);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        Text lev = new Text();
        lev.setText("Current Membership Level:");
        lev.setFont(new Font(20));
        lev.setTranslateX(675);
        lev.setTranslateY(-225);
        

        curLev.setFont(new Font(20));
        curLev.setTranslateX(755);
        curLev.setTranslateY(-275);
        
        
        Button logout2 = new Button();
        logout2.setText("Logout");
        logout2.setTranslateX(450);
        logout2.setTranslateY(-115);
        logout2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               primaryStage.setScene(scene1);
            }
        });
    
      
      
        v3.getChildren().add(cName);
        v3.getChildren().add(dep);
        v3.getChildren().add(depNum);
        v3.getChildren().add(deposit);
        v3.getChildren().add(withdraw);
        v3.getChildren().add(bal);
        v3.getChildren().add(curBal);
        v3.getChildren().add(withNum);
        v3.getChildren().add(with);
        v3.getChildren().add(on);
        v3.getChildren().add(onNum);
        v3.getChildren().add(online);
        v3.getChildren().add(lev);
        v3.getChildren().add(logout2);
        v3.getChildren().addAll(curLev);
                    
        
      // STAGE SETTING ETC.              
      
        primaryStage.setTitle("Banking Application");
        primaryStage.setScene(scene1);
        primaryStage.show();               
        
    }
    
    public StackPane getRootPane() {
        return root;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
