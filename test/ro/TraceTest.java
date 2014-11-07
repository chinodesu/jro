package ro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TraceTest {

    Trace o;

    @Before
    public void setUp() throws Exception {
        this.o = new Trace();
    }

    @Test
    public void testCurMethName() throws Exception {
        Object r = o.curMethName();
        assertEquals(r, "testCurMethName");
    }
}
