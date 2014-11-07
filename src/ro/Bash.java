package ro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/22/14.
 */
public class Bash {
    public static void bash(String... args) {
        String cmd = toList(args).join(" ");
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (rdr.ready()) {
                String l = rdr.readLine();
                System.out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
