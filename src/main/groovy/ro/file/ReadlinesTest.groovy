package ro.file;

import ro.Pj;
import ro.TestCase;

 class ReadlinesTest extends ro.TestCase {
    Readlines o;



     void setUp() {
        o = new Readlines(Pj.cur("ro/file/Readlines.java"));
    }

     void testStart()  {
        Object r = o.readlines();
        asrHaveVal(r);
    }
}
