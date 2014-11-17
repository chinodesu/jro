package ro;

/**
 * Created by roroco on 11/15/14.
 */
public class Dir extends File {
    public static boolean exist(String d) {
        java.io.File f = new java.io.File(d);
        return f.exists() && f.isDirectory();
    }
}
