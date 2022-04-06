/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.app;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author marvyshaker
 */
public class Books {
    
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty price;
    /*String name;
    String price;*/

    /**
     *
     * @param name
     * @param price
     */
    public Books(String name, double price){
        
        this.name= new SimpleStringProperty(name);
        this.price= new SimpleDoubleProperty(price);
    }
    
    public String getName(){
    
        return name.get();
    }
    
    public void setName(String name){
        this.name.set(name);
    }
    
    public double getPrice(){
        return price.get(); 
    }
    
    public void setPrice(double price){
        this.price.set(price);    
    }
    
    
}
