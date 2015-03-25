package ro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

 class TraceTest extends ro.TestCase {

     void testCurMeth()  {
        Object r = new Trace().curMeth();
        assertEquals(r, "testCurMeth");
    }

     void testLastLoc()  {
        Object r = new Trace().lastLoc();
        asrHaveVal(r);
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
