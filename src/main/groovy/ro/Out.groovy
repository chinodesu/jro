package ro;

/**
 * Created by roroco on 12/5/14.
 */
 class Out {
     static String out(String s) {
        System.out.println(s + "\t\t" +  ":" + new Trace().lastLoc());
        return null;
    }
}
