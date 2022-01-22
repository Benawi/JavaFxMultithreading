package InterComm;

public class TestInterCom {

    public static void main(String args[]) {
        Customer c = new Customer();
        new Thread() {
            @Override
            public void run() {
                c.withdraw(1500000);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                c.deposit(150000);
            }
        }.start();

    }
}
