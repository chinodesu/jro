package ro;

import org.junit.Test;
import ro.junit.TestCase;

public class ErrTest extends TestCase {

    @Test
    public void testInstance() throws Exception {
        assertThrowErr(Exception.class, () -> {
            throw ro.Err.instance("AErr");
        });
    }
}
