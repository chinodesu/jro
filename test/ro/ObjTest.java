package ro;

import junit.framework.TestCase;
import org.junit.Test;
import static ro.helper.Kernel.*;

public class ObjTest extends TestCase {
    static class C {
        static int i = 0;
    }

    @Test
    public void testStaticField() throws Exception {
        Object r = obj(C.class).staticField("i");
        assertEquals(r, 0);
    }
}
