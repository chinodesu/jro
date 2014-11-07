package ro;

/**
 * Created by roroco on 10/25/14.
 */
public class Thr extends Thread {

    public interface Proc {
        void call();
    }

    public static Thr start(Proc l) {
        Thr t = new Thr(l);
        t.start();
        return t;
    }

    Proc l;

    public Thr(Proc l) {
        this.l = l;
    }

    public void run() {
        try {
            l.call();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
//        Thr t = new Thr(() -> {
//            while (true) {
//                System.out.println(new Date() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//                sleep(1);
//            }
//        });
//
//        t.start();
//
//        while (true) {
//            System.out.println(new Date() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//            sleep(1);
//        }

        Thr t = new Thr(() -> {
            System.out.println("system exit" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            System.exit(0);
        });

        t.start();
        try {
            sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
