/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvicenileto.less05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author svecon
 */
public class HttpClient {

    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getByName(null);
            try (Socket socket = new Socket(addr, 6666)) {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

                out.println("GET /x.txt HTTP/1.0");
                out.println("Host: www.web.org");
                out.println("adsa dkalsh lahsd kla");
                out.println();
                
                System.out.println("finished sending headers");

                String rcv;
                while ((rcv = in.readLine()) != null) {
                    System.out.println(rcv);
                }
                
                System.out.println("finished receiving");

            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (UnknownHostException e) {
            System.out.println(e);
        }

    }
}
