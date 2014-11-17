package ro.file;

import ro.Pj;
import ro.junit.TestCase;

public class ReadlinesTest extends TestCase {
    Readlines o;

    @Override

    public void setUp() {
        o = new Readlines(Pj.root("ro/file/Readlines.java"));
    }

    public void testStart() throws Exception {
        Object r = o.readlines();
        assertHaveVal(r);
    }
}
