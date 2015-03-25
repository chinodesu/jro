package ro;

 class TimeTest extends ro.TestCase {
    Time o;



     void setUp() {
        o = new Time();
    }

     void testInterval()  {
        Object r = o.interval(new Time(2014, 11, 11, 0, 0, 0), Time.now());
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }

     void testNow()  {
        Object r = o.now();
        System.out.println("r:" + r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }

//
//     void testOut()  {
//        asrThrowErr(ro.Err.ii("TimeOut"), new Proc() {
//             void call() {
//                o.out(0.05, new Lamb() {
//
//                     void call() {
//                        Thr.slp();
//                    }
//                });
//            }
//        });
//
//    }
}
