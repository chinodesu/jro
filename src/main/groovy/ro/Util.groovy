package ro;

import static ro.helper.Kernel.*;

/**
 * Created by roroco on 11/17/14.
 */
 class Util {
     static void toClip(Object o)  {
        String f = "/tmp/clipboard";
        File.write(f, o.toString());
        sh("xclip -selection clipboard", f);
    }
}
