package ro

import org.junit.Assert
import org.mockito.internal.matchers.InstanceOf

import static ro.helper.LightKernel.*

class TestCase extends GroovyTestCase {
    void assertEq(Object expected, Object actual) {
        assertEquals(expected, actual);
    }

    def isNix(Closure c) {
        if (Pj.os == 'linux' || Pj.os == 'osx') {
            c.call()
        }
    }

    def asrIns(o, kls) {
        if (o.getClass() != kls) {
            fail("$o is not instance of $kls")
        }
    }

    void asrThrowErr(Object expected, Proc p) {
        try {
            p.call();
            fail("asr throw", expected, "but no any err is thrown");
        } catch (Exception e) {
            if (expected instanceof Class) {
                obj(exc).isIns((Class) expected);
            } else if (exc instanceof ro.Err && expected instanceof String) {
                ((ro.Err) exc).is((String) expected);
            }
            throw e
        }
    }

    void asrHaveVal(Object o) {
        if (!obj(o).haveVal()) {
            if (o instanceof String && ((String) o).length() == 0) {
                o = str("\"", o, "\"");
            }
            fail("asr", o, "have val");
        }
    }

    void asrHaveVal(Object o, Object val) {
        if (!obj(o).haveVal(val)) {
            fail("asr", o, "have val");
        }
    }

    static void asrEq(o, o2) {
        assertEquals(o, o2)
    }

    static void fail(Object... args) {
        Assert.fail(str(args).toMsg());
    }

    void asrMatch(String re, Object actual) {
        if (all(str(actual).matcher(re))) {
            return;
        }

        fail("asr", re, ".matcher", actual);
    }

    void asrExist(String path) {
        if (ro.File.isDir(path)) {
            return;
        }

        fail("asr", path, "exist");
    }
}
