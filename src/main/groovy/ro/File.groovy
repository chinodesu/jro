package ro;

import ro.file.Line;
import ro.file.Readlines;

import java.io.*;

import static ro.helper.LightKernel.str;

import org.apache.commons.io.FilenameUtils;

/**
 * Created by roroco on 10/31/14.
 */
class File {
    static File ii(String... ss) {
        return new File(ss);
    }

    static File ii(String p) {
        return new File(p);
    }

    File(String... fs) {
        String finPath = new Str(fs, "separator:", "/").toString();
        this.f = new java.io.File(finPath);
    }

    java.io.File f;

    File(String f) {
        this.f = new java.io.File(f);
    }

    static void rm(String path) {
        System.out.println(str("rm", path) + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        new java.io.File(path).delete();
    }


    static boolean isDir(String path) {
        return new java.io.File(path).isDirectory();
    }

    static String fn(String p) {
        return FilenameUtils.getBaseName(p);
    }

    static String parent(String p) {
        return new File(p).parent();
    }

    String parent() {
        return f.getParent();
    }

    static String mkdir(String path) {
        System.out.println(str("mkdir", path).toMsg() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        java.io.File dir = new java.io.File(path);

        dir.mkdirs();

        if (dir.isDirectory()) {
            return dir.getAbsolutePath();
        }

        throw Err.ii("NotExist", "mkdir", path, "fail");
    }

    String toString() {
        return f.getAbsolutePath();
    }

    static String join(... args) {
        def r = args.flatten().join("/").replaceAll("/{2,}", '/')
        return r;
    }

    String read() {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        reader.read();
    }

    static String write(String file, String ctn) {
        return new File(file).write(ctn);
    }

    String write(String ctn) {
        System.out.println(str("write", f.toString()).toMsg() + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
        f.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        writer.write(ctn);
        writer.close();
        toString();
    }

    static AL<Line> readlines(String f) {
        return new Readlines(f).readlines();
    }

    boolean exist() {
        return f.exists();
    }
}
