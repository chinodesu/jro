package ro;

import ro.junit.TestCase;

import static org.junit.Assert.*;

public class RangeTest extends TestCase {
    Range o;

    @Override

    public void setUp() {
        o = new Range(1, 2);
    }

    public void testForEach() throws Exception {
        for (int e : new Range(1, 2)) {
        }

        for(int e : new Range(2,1)) {

        }
    }

}
