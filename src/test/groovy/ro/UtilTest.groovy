package ro;

class UtilTest extends ro.TestCase {
    Util o;

    void setUp() {
        o = new Util();
    }

    void testToClip() {
        isNix {
            o.toClip(Time.now());
        }
    }
}
