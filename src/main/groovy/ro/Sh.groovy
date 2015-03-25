package ro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ro.helper.LightKernel.*;

/**
 * Created by roroco on 10/22/14.
 */

class ShErr extends ro.Err {

     ShErr(String message) {
        super(message);
    }
}

 class Sh {


     static void notify(Object o)  {
        sh("notify-send", "\"" + o.toString() + "\"");
    }

     static String xdo(String cmd)  {
        return sh("xdotool", cmd, "keyup alt_Left alt_Right Control_Left Control_Right Shift_Left Shift_Right");
    }

     static String sh(String... args)  {
        return String(args);
    }

    private static String String(String[] args)  {
        String cmd = new AL<String>(args).toCmd();

        AL ls = new AL();
        System.out.println(str(cmd).toMsg() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        String[] finCmd = ["/bin/sh", "-c", cmd];
        Process p = Runtime.getRuntime().exec(finCmd);
        BufferedReader rdr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        int exitCode = 0;

        exitCode = p.waitFor();

        if (exitCode != 0) {
            System.out.println("exitCode: " + exitCode + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
            throw Err.ii("'", cmd, "'", "fail");
        }

        while (rdr.ready()) {
            String l = rdr.readLine();
            ls.add(l);
            System.out.println(l);
        }

        return ls.join("\n");
    }
}
