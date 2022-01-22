/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterComm;

import javafx.scene.control.TextArea;

public class Customer {
    private TextArea statusTxt;
    int amount = 10000;

    Customer(TextArea lbl) {
        statusTxt = lbl;

    }

    synchronized void withdraw(int amount) {
        System.out.println("going to withdraw");
        changeArea("\ngoing to withdraw");
        if (this.amount < amount) {
            System.out.println("Less balance; waiting for deposit...");
            changeArea("\nLess balance; waiting for deposit...");
            try {
                wait();
            } catch (Exception e) {
            }
        }
        this.amount -= amount;
        System.out.println("withdraw completed...");
        changeArea("\nwithdraw completed...");

    }

    synchronized void deposit(int amount) {
        System.out.println("going to deposit...");
        this.amount += amount;
        System.out.println("deposit completed... ");
        notify();
    }

    public synchronized void changeArea(String text) {
        statusTxt.appendText(text);
    }
}
