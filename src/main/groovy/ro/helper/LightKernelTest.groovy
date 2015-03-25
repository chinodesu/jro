package ro.helper;

import junit.framework.TestCase;
import org.junit.Test;

import static ro.helper.Kernel.*;

 class LightKernelTest extends ro.TestCase {

    LightKernel o;



     void setUp() {
        o = new LightKernel();
    }


     void testAllTrue()  {
        Object r = all(false);
        assertEquals(false, r);
    }

     void testRange()  {
        for (Integer n : o.range(1, 2)) {
            System.out.println(n + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        }
    }
}
