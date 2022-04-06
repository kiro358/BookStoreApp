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

public abstract class CustomerState {
    public abstract void buyState(Customer customer);
    public abstract void buyNRedeemState(Customer customer);

}