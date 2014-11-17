package ro;

import static org.junit.Assert.*;

public class TimeTest extends ro.junit.TestCase {
    Time o;

    @Override

    public void setUp() {
        o = new Time();
    }

    public void testNow() throws Exception {
        Object r = o.now();
        System.out.println("r:" + r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
