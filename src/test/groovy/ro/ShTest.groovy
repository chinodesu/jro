package ro;

import ro.TestCase;

 class ShTest extends ro.TestCase {
    Sh o;



     void setUp() {
        o = new Sh();
    }

     void testXdo()  {
        Object r = o.xdo("getactivewindow getwindowname");
        asrHaveVal(r);
    }
}
