package ro;

import org.junit.Test;
import ro.junit.TestCase;

public class FileTest extends TestCase {
    @Test
    public void testConstructor() throws Exception {
        Object r = new ro.File("tmp/dir", "file").toString();
        assertMatch("dir/file", r);
    }

    @Test
    public void testMkdir() throws Exception {
        String r = File.mkdir("tmp/dir");
        assertExist(r);
    }
}
