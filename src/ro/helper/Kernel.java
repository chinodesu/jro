package ro.helper;

import ro.File;
import ro.Trace;

/**
 * Created by roroco on 10/28/14.
 * used by ro.**\/* except ro.*
 */
public class Kernel extends LightKernel {
    public static File file(String f) {
        return new File(f);
    }


    public static Trace roTrace = new Trace();
}
