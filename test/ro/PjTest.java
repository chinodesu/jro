package ro;

import ro.junit.TestCase;
import org.junit.Test;

public class PjTest extends TestCase {
    @Test
    public void testCurRoot() throws Exception {
        Object r = Pj.curRoot("dir", "file");
        assertMatch("dir/file", r);
    }
}
