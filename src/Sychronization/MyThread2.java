package Sychronization;

public class MyThread2 extends Thread {

    SyncDemo s;

    MyThread2(SyncDemo s) {
        this.s = s;
    }

    public void run() {
        s.printTable(100);
    }
}
