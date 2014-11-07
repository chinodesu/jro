package ro;

import junit.framework.TestCase;
import org.junit.Test;

public class OptsTest extends TestCase {

    @Test
    public void testGet() throws Exception {
        Object r = Opts.get("separator:", " ").get("separator");
        assertEquals(" ", r);
    }
}
