package ro.junit;

import org.junit.Assert;

import static ro.helper.Kernel.*;


/**
 * Created by roroco on 11/6/14.
 */
public class TestCase extends junit.framework.TestCase {
    public void assertEq(Object expected, Object actual) {
        assertEquals(expected, actual);
    }

    public interface _Lamb {
        void call() throws Exception;
    }

    public void assertThrowErr(Object expected, _Lamb l) {
        try {
            l.call();
            fail("assert throw", expected, "but no any err is thrown");
        } catch (Exception exc) {
            if (expected instanceof Class) {
                obj(exc).isInsOf((Class) expected);
                return;
            } else if (exc instanceof ro.Err && expected instanceof String) {
                ((ro.Err) exc).is((String) expected);
                return ;
            }

            exc.printStackTrace();
            System.exit(-1);
        }
    }

    public static void fail(Object... args) {
        Assert.fail(str(args).toMsg());
    }

    public void assertMatch(String re, Object actual) {
        if (all(str(actual).match(re))) {
            return;
        }

        fail("assert", re, ".match", actual);
    }

    public void assertExist(String path) {
        if (ro.File.isDir(path)) {
            return;
        }

        fail("assert", path, "exist");
    }
}
