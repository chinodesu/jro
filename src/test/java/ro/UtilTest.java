package ro;

import ro.junit.TestCase;

import static org.junit.Assert.*;

public class UtilTest extends TestCase {
    Util o;

    @Override

    public void setUp() {
        o = new Util();
    }
    
    public void testToClip() throws Exception {
        o.toClip(Time.now());
    }
}
