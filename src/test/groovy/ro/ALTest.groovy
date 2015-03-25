package ro;

import org.junit.Test;
import ro.file.Line;

class ALTest extends ro.TestCase {
    AL o;

    void setUp() {
        o = new AL();
    }

    void testGrep() {
        AL<Line> a = new AL<Line>();
        a.add(new Line("file", 1));
        a.grep("file").first().no();
    }

    void testSelect() {
        o = new AL([1, 2]);

        def finA = o.select { e ->
            o == 1;
        }

        assert (finA.first() == 1 && finA.length() == 1)
    }
}
