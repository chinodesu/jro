package ro;

import org.joda.time.DateTime;

/**
 * Created by roroco on 11/17/14.
 */

public class Time {
    public static Time now() {
        return new Time(DateTime.now());
    }

//    public static void out(final double _sec, Lamb l) {
//        final Thr curThr = Thr.cur();
//        Thr t = new Thr(new Thr.Runner() {
//            @Override
//            public void run() {
//                Thr.slp(_sec);
//                curThr.kill();
//                System.out.println("run " + curThr + " " + "timeout" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//            }
//        });
//        t.start();
//        l.call();
//    }

    DateTime d;

    public Time(DateTime d) {
        this.d = d;
    }

    public Time(int year, int month, int day, int hour, int min, int sec) {
        this.d = new DateTime(year, month, day, hour, min, sec);
    }

    public long sec() {
        return d.getMillis() / 1000;
    }

    public Time() {
        this.d = DateTime.now();
    }

    public static long interval(Time from, Time to) {
        return to.sec() - from.sec();
    }
}
