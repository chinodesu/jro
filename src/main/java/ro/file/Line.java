package ro.file;

import ro.Str;

/**
 * Created by roroco on 11/14/14.
 */
public class Line {
    Str line;
    int no;

    public Line(String line, int no) {
        this.line = new Str(line);
        this.no = no;
    }

    public String toString() {
        return line.toString();
    }

    public Str line() {
        return line;
    }

    public int no() {
        return no;
    }

    public Str methName() {
        return  new Str(line.matcher("^\\s+(public|protected|private)\\s\\S+\\s(\\S+)").get(2));
    }
}

