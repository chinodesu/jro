package ro;

import ro.TestCase;
import org.junit.Test;

import static ro.helper.Kernel.*;

 class ObjTest extends ro.TestCase {
    static class C {
        static int i = 0;
    }


     void testStaticField()  {
        Object r = obj(C.class).staticField("i");
        assertEquals(r, 0);
    }

    class TestGrep {
        String attr = "val";
        String attr2 = "val2";
    }

     void testGrep()  {
        Hash h = Hash.ii();
        h.set("k", "v");
        h.set("k2", "v2");
        Hash _r = (Hash) obj(h).grep("k2");
        Object r = _r.first().key();
        assertEq("k2", r);

        r = ((Hash) Obj.ii(new TestGrep()).grep("attr2")).first().key();
        assertEq("attr2", r);
    }
}
