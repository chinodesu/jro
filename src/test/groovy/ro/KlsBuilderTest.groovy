package ro

/**
 * Created by zenghuiying on 2015/3/24.
 */
class KlsBuilderTest extends TestCase {
    void testBuild() {
        def e = Err.ii("NotExist")
        asrEq e.class, "NotExist"
    }
}
