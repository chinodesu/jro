package ro;

/**
 * Created by roroco on 11/3/14.
 */
public class Trace {
    public String curMethName() {
        return new Exception().getStackTrace()[1].getMethodName();
    }

    public String lastMethName() {
        return new Exception().getStackTrace()[2].getMethodName();
    }
}
