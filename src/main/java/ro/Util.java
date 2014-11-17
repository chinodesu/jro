package ro;

import static ro.helper.Kernel.*;

/**
 * Created by roroco on 11/17/14.
 */
public class Util {
    public static void toClip(Object o) {
        String f = "/tmp/clipboard";
        File.write(f, o.toString());
        bash("xclip -selection clipboard", f);
    }
}
