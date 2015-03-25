package ro;

class FileTest extends ro.TestCase {

    void testConstructor() {
        def r = new ro.File("tmp/dir", "file").toString()
        asrMatch("dir/file", r);
    }

    void testMkdir() {
        String r = File.mkdir("tmp/dir");
        asrExist(r);
    }

    void testReadLines() {
        Object r = File.readlines(Pj.cur("src/ro/File.java"));
        asrHaveVal(r);
    }

    void testJoin() {
        def r = File.join('/tmp', 'smth.log')
        asrEq('/tmp/smth.log', r)
    }
}
