package ro;

import ro.TestCase;

 class StrTest extends ro.TestCase {
     void testConstructor()  {
        Object r = new Str("dir", "file", "separator:", "/").toString();
        assertEq("dir/file", r);
    }
}
