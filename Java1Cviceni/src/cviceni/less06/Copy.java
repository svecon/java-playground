/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 *
 * @author sveco
 */
public class Copy {

    public static void main(String[] args) {

        copy(args[0], args[1]);

    }

    static void printInfo(File fin, File fout) {
        System.out.println("From: " + fin.getAbsolutePath());
        System.out.println("  To: " + fout.getAbsolutePath());
    }

    public static void copy(String from, String to) {
        File fin = new File(from); File fout = new File(to);
        if (fout.isDirectory()) fout = new File(fout, fin.getName()); 

        printInfo(fin, fout);

        try {
            Files.copy(fin.toPath(), fout.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void copyStreamed(String from, String to) {
        File fin = new File(from); File fout = new File(to);
        if (fout.isDirectory()) fout = new File(fout, fin.getName()); 

        printInfo(fin, fout);

        try (
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(fin));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fout))) {

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void copyTransfered(String from, String to) {
        File fin = new File(from); File fout = new File(to);
        if (fout.isDirectory()) fout = new File(fout, fin.getName()); 

        printInfo(fin, fout);

        try {

            FileChannel in = new FileInputStream(fin).getChannel(),
                    out = new FileOutputStream(fout).getChannel();

            in.transferTo(0, in.size(), out);
//            out.transferFrom(in, 0, in.size());
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
