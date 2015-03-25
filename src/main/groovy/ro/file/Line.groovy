package ro.file;

import ro.Str;

/**
 * Created by roroco on 11/14/14.
 */
 class Line {
    Str line;
    int no;

     Line(String line, int no) {
        this.line = new Str(line);
        this.no = no;
    }

     String toString() {
        return line.toString();
    }

     Str line() {
        return line;
    }

     int no() {
        return no;
    }

     Str methName() {
        return  new Str(line.matcher("^\\sec+(|protected|private)\\sec\\S+\\sec(\\S+)").get(2));
    }
}

