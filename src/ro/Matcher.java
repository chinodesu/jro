package ro;

import java.util.regex.Pattern;

/**
 * Created by roroco on 10/28/14.
 */
public class Matcher {
    java.util.regex.Matcher m;

    public Matcher(java.util.regex.Matcher m) {
        this.m = m;
    }

    public boolean find() {
        return m.find();
    }

    public String toString() {
        try {
            return m.group(0);
        } catch (IllegalStateException e) {
            if (e.getMessage() == "No match found") {
                return null;
            }
            throw e;
        }
    }

    public static void main(String[] args) {
        Pattern p = Pattern.compile("j");
        java.util.regex.Matcher m = p.matcher("java");
        m.find();
        Object r = new Matcher(m).toString();
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
