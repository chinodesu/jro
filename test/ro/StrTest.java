package ro;

import junit.framework.TestCase;

public class StrTest extends TestCase {
    public void testConstructor() throws Exception {
        Object r = new Str("dir", "file", "separator:", "\\/").toString();
        assertEquals("dir/file", r);
    }
}
