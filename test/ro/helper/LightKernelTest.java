package ro.helper;

import junit.framework.TestCase;
import org.junit.Test;

import static ro.helper.Kernel.*;

public class LightKernelTest extends TestCase {

    @Test
    public void testAllTrue() throws Exception {
        Object r = all(null);
        assertEquals(false, r);
    }
}
