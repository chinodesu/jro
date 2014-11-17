package ro;


public class BashTest extends ro.junit.TestCase {
    Bash o;

    @Override

    public void setUp() {
        o = new Bash();
    }

    public void testBash() throws Exception {
        Object r = o.bash("echo 'smth'");
        assertHaveVal(r);
        r = o.bash("ls -al ~|grep Downloads");
        assertHaveVal(r);
    }
}
