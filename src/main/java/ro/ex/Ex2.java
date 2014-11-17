package ro.ex;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

/**
 * Created by roroco on 11/17/14.
 */
public class Ex2 {
    public static void main(String[] args) throws IOException {
        String myString = "This text will be copied into clipboard when running this code!";
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, stringSelection);
        System.in.read();
    }
}
