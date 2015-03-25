package ro;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

 class OptsTest extends ro.TestCase {


     void testGet()  {
        Object r = Opts.get("separator:", " ").get("separator");
        assertEquals(" ", r);
    }


     void testForEach()  {
        Opts<String, String> opts = Opts.get("k:", "v", "k2:", "v2");
        for (Map.Entry e : opts) {

        }
    }
}
