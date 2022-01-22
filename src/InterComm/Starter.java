package InterComm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;


public class Starter {
    @FXML
    private TextField withdrawTxt;
    @FXML
    private  TextField depositTxt;
    @FXML
    public TextArea statusTxt;
    public void StarterMethod() {
        Customer c = new Customer(statusTxt);
        new Thread() {
            @Override
            public void run() {
                c.withdraw(Integer.parseInt(withdrawTxt.getText()));
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                c.deposit(Integer.parseInt(depositTxt.getText()));

            }
        }.start();
    }


}
