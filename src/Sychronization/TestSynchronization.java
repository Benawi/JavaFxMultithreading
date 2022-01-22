package Sychronization;

public class TestSynchronization {

    public static void main(String args[]) {
        SyncDemo obj = new SyncDemo();//only one object  
        MyThread1 t1 = new MyThread1(obj);
        MyThread2 t2 = new MyThread2(obj);
        t2.start();
        t1.start();
        
    }
}
