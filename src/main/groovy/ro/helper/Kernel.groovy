package ro.helper;

import ro.Sh;
import ro.File;
import ro.Trace;

import java.io.IOException;

/**
 * Created by roroco on 10/28/14.
 * used by ro.**\/* except ro.*
 */
 class Kernel extends LightKernel {
     static File file(String f) {
        return new File(f);
    }

     static String sh(String... args)  {
        return Sh.sh(args);
    }

     static Trace trace = new Trace();
}
