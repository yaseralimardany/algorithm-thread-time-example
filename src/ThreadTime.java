import java.util.*;

public class ThreadTime {
    public static void runAndWait(List<Runnable> runnables) {
        try {
            Thread[] listOfThreads = new Thread[runnables.size()];
            for(int i = 0; i < runnables.size(); i++) {
                Thread thread = new Thread(runnables.get(i));
                thread.start();
                listOfThreads[i] = thread;
            }
            for (int i = 0; i< listOfThreads.length; i++){
                listOfThreads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Runnable> runnables = Arrays.asList(() -> {
            try {
                Thread.sleep(800);
                System.out.println("Slow function");
            } catch(Exception e) {}
        }, () -> {
            try {
                Thread.sleep(100);
                System.out.println("Fast function");
            } catch(Exception e) {}
        });

        // Expected output:
        // Fast function
        // Slow function
        // Returned from the method!
        runAndWait(runnables);
        System.out.println("Returned from the method!");
    }
}