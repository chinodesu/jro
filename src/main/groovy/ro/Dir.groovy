package ro;

/**
 * Created by roroco on 11/15/14.
 */
 class Dir extends File {
     static boolean exist(String d) {
        java.io.File f = new java.io.File(d);
        return f.exists() && f.isDirectory();
    }
}
