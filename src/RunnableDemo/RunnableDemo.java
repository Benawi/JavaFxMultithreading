package RunnableDemo;

public class RunnableDemo implements Runnable {

    private Thread t;
    private String threadName;

    RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creating thread "
                + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running "
                + threadName);
        try {
            for (int i = 1; i < 3; i++) {
                System.out.println("Thread: "
                        + threadName + ", " + i);

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void startPt() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
 public static void main(String args[]) {

        RunnableDemo R1 = new RunnableDemo("Thread-11");
        R1.startPt();

        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.startPt();
    }
}
