package ro.junit;

import ro.Err;

import javax.management.RuntimeErrorException;

/**
 * Created by roroco on 11/7/14.
 */
public class TestCaseTest extends TestCase {
    public void testAssertThrowErr() throws Exception {
        assertThrowErr(RuntimeErrorException.class, () -> {
            throw new RuntimeException();
        });

        assertThrowErr("NotExist", () -> {
            throw Err.instance("NotExist");
        });
    }
}
