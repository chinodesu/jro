package ro;

import org.junit.Test;
import ro.file.Line;
import ro.junit.TestCase;

import static org.junit.Assert.*;

public class ALTest extends TestCase {

    @Test
    public void testGrep() throws Exception {
        AL<Line> a = new AL<Line>();
        a.add(new Line("file", 1));
        a.grep("file").first().no();
    }
}
