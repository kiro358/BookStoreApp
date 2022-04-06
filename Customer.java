/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.app;

/**
 *
 * @author lamnguyen
 */
import javafx.beans.property.SimpleStringProperty;
import java.util.*;
import javafx.beans.property.SimpleDoubleProperty;

public class Customer {

    private final SimpleStringProperty custName;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleDoubleProperty point;
    private CustomerState state;
   
    
    public Customer(String name, String username, String password, int point) {
            this.custName = new SimpleStringProperty(name);
            this.username = new SimpleStringProperty(username);
            this.password = new SimpleStringProperty(password);
            this.point = new SimpleDoubleProperty(point);
            if(point<0)
                this.point.set(0);
    }

    public String getName() {
        return custName.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    
    public double getPoint() {
        return point.get();
    }

    public CustomerState getState() {
        return state;
    }

    public void setName(String name) {
        this.custName.set(name);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setPoint(double point) {
        
        if (point>0){
            this.point.set(point);
        }else{
            this.point.set(0);
        }
        
    }

    public void setState(CustomerState state) {
        this.state = state;
    }
    
   public void buy (List<Books> listBuy){
        double total = 0;
        for (Books book: listBuy){
            total += book.getPrice();
        }
        this.point.set(total * 10);
        if(this.point.getValue()>=1000){
            this.state.buyState(this);
        }
    }
    
    public void buyNRedeem(List<Books> listBuy){
        double total = 0;
        for (Books book: listBuy){
            total += book.getPrice();
        }
        total -= (this.point.getValue()) / 100;
        this.point.set(0);
        this.state.buyNRedeemState(this);
        this.point.setValue(total * 10);
    }
}