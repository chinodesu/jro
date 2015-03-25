package ro.junit;

import ro.Err;
import ro.Proc;

import javax.management.RuntimeErrorException;

/**
 * Created by roroco on 11/7/14.
 */
class TestCaseTest extends ro.TestCase {
    void testAssertThrowErr() throws Exception {
        asrThrowErr Err.NotExist, {
            throw Err.NotExist;
        }
    }
}
