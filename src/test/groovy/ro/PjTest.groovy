package ro;

import ro.TestCase;
import org.junit.Test;

 class PjTest extends ro.TestCase {

     void testCur()  {
        Object r = Pj.cur("dir", "file");
        asrMatch("dir/file", r);
    }
}
