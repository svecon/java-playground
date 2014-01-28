package cviceni.less08;

import java.io.*;
import java.net.*;

/**
 *
 * @author svecon
 */
public class Client {

    public static void main(String[] args) {

        try {
            InetAddress addr = InetAddress.getByName(null);
            try (Socket socket = new Socket(addr, 6666)) {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

                String input;
                String message;

                while (!(input = userIn.readLine()).equals("END")) {
                    out.println(input);
                    while (!(message = in.readLine()).equals("END")) {
                        System.out.println(message);
                    }
                }

                out.println("CLOSE");

            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (UnknownHostException e) {
            System.out.println(e);
        }

    }
}
