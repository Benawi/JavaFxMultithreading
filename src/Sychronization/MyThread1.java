package Sychronization;

public class MyThread1 extends Thread {

    SyncDemo s;

    MyThread1(SyncDemo s) {
        this.s = s;
    }

    public void run() {
        s.printTable(5);
    }

}
