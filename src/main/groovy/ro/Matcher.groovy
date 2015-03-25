package ro;

import java.util.regex.Pattern;

/**
 * Created by roroco on 10/28/14.
 */
 class Matcher {
    java.util.regex.Matcher m;

     Matcher(java.util.regex.Matcher m) {
        this.m = m;
    }

     boolean find() {
        return m.find();
    }

     String toString() {
        try {
            return m.group(0);
        } catch (IllegalStateException e) {
            if (e.getMessage() == "No match found") {
                return null;
            }
            throw e;
        }
    }

     String get(int i) {
        return m.group(i);
    }

     static void main(String[] args) {
        Pattern p = Pattern.compile("j(a)");
        java.util.regex.Matcher m = p.matcher("java");
        m.find();
        Object r = new Matcher(m).toString();
        System.out.println(r + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }

}
