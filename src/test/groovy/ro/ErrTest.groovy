package ro;

import org.junit.Test;

 class ErrTest extends ro.TestCase {


     void testInstance()  {
        asrThrowErr(Exception.class, new Proc() {

             void call()  {
                throw ro.Err.ii("AErr");
            }
        });
    }
}
