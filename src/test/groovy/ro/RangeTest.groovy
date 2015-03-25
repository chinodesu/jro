package ro;

import ro.TestCase;

import static org.junit.Assert.*;

 class RangeTest extends ro.TestCase {
    Range o;



     void setUp() {
        o = new Range(1, 2);
    }

     void testForEach()  {
        for (int e : new Range(1, 2)) {
        }

        for(int e : new Range(2,1)) {

        }
    }

}
