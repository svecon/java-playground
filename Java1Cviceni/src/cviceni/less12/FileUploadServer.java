/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less12;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author svecon
 */
public class FileUploadServer {

    private static class ServeConnection extends Thread {

        final private ServerSocket server;

        public ServeConnection(ServerSocket s) {
            this.server = s;
        }

        @Override
        public void run() {

            try (
                    Socket socket = server.accept();
                    BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                    //                    PrintStream out = new PrintStream(socket.getOutputStream());
                    FileOutputStream fout = new FileOutputStream(new File("photo_uploaded.jpg"))) {

                System.out.println("Upload started");
                int b;
                while ((b = in.read()) != -1) {
                    fout.write(b);
                }
                System.out.println("Upload finished");

            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println("End of thread");
        }

    }

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(4680)) {
            new ServeConnection(server).run();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
