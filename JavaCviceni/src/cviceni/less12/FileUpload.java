/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less12;

import java.io.*;
import java.net.*;

/**
 *
 * @author svecon
 */
public class FileUpload {

    public static void main(String[] args) {

        try (
                Socket client = new Socket(InetAddress.getByName(null), 4680);
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(new File("photo.jpg")));
                PrintStream out = new PrintStream(client.getOutputStream());) {

            System.out.println("Client started");
            int b;
            while ((b = fin.read()) != -1) {
                out.write(b);
            }
            System.out.println("Client finished");

        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
