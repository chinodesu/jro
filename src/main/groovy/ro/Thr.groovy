package ro;

/**
 * Created by roroco on 10/25/14.
 */
class Thr extends Thread {

    interface Runner extends java.lang.Runnable {
        void run();
    }

//     void kill() {
//        stop();
//    }

    static void slp(double sec) {
        try {
            sleep((long) sec * 1000);
        } catch (InterruptedException e) {
        }
    }

    static void slp(long sec) {
        try {
            sleep(sec * 1000);
        } catch (InterruptedException e) {
        }
    }

    static void slp() {
        while (true) {

        }
    }

    static Thr start(Runner r) {
        Thr t = new Thr(r);
        t.start();
        return t;
    }

    static Thr cur() {
        return new Thr(Thread.currentThread());
    }


    Runner r;
    Thread t;

    void setup() {
        if (t == null) {
            t = Thread.currentThread();
        }
    }

    Thr(Runner _r) {
        r = _r;
        setup();
    }

    Thr(Thread _t) {
        t = _t;
    }

    Thr() {
        setup();
    }

    void run() {
        r.run();
    }

    static void main(String[] args) {
//        Thr t = new Thr(() -> {
//            while (true) {
//                System.out.println(new Date() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//                sleep(1);
//            }
//        });
//
//        t.readlines();
//
//        while (true) {
//            System.out.println(new Date() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//            sleep(1);
//        }

        Thr t = new Thr(new Runner() {
            void run() {
                throw new RuntimeException();
            }
        });

        t.start();
        sleep(Long.MAX_VALUE);
    }
}
