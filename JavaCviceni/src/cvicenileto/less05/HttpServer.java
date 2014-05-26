/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenileto.less05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MXBean;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;

/**
 *
 * @author svecon
 */
public class HttpServer extends NotificationBroadcasterSupport {

    private static File webDir;

    private static class ServeConnection extends Thread {

        private final Socket socket;

        public ServeConnection(Socket s) {
            this.socket = s;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {

                System.out.println("reading from client");

                String head[] = in.readLine().split(" ");

                while (in.readLine().equals("")) {
                }

                System.out.println("finished reading from client, sending headers");

                try (BufferedReader fileReader = new BufferedReader(new FileReader(new File(webDir, head[1])))) {

                    out.println("HTTP/1.0 200 OK");
                    out.println();

                    String file;
                    while ((file = fileReader.readLine()) != null) {
                        out.println(file);
                    }

                    System.out.println("done sending headers");
                } catch (IOException e) {
                    System.out.println(e);
                    out.println("HTTP/1.0 404 OK\n\nNo File.");
                    out.println();
                } finally {
                    socket.close();
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("cvicenileto.less05:type=Server");
            Management mbean = new ManagementBean();
            mbs.registerMBean(mbean, name);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            System.out.println(e);
        }

        webDir = new File(".");

        try (ServerSocket server = new ServerSocket(6666)) {
            while (true) {
                Socket socket = server.accept();

                System.out.println("New client");
                Thread sc = new HttpServer.ServeConnection(socket);
                sc.run();
            }
        }
    }

    
    static class ManagementBean implements Management {

        @Override
        public String getDirectory() {
            return webDir.toString();
        }

        public void setDirectory(String dir) {
            webDir = new File(dir);
        }

    }

    @MXBean
    public interface Management {

        String getDirectory();

        void setDirectory(String dir);

    }

}
