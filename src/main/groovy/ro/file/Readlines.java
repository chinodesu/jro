package ro.file;

import ro.AL;

import java.util.Scanner;

/**
 * Created by roroco on 11/13/14.
 */
public class Readlines {

    String f;

    public Readlines(java.io.File f) {
        this.f = f.toString();
    }

    public Readlines(String f) {
        this.f = f;
    }

    public AL<Line> readlines() {
        Scanner s = new Scanner(f);
        AL<Line> ls = new AL<Line>();
        int idx = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            idx += 1;
            ls.add(new Line(line, idx));
        }
        return ls;
    }
}
