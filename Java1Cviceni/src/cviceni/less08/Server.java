package cviceni.less08;

import cviceni.less07.Todo;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author svecon
 */
public class Server {

    private static class ServeConnection extends Thread {

        private Socket socket;

        public ServeConnection(Socket s) {
            this.socket = s;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                )
            {

                while (true) {
                    String str;
                    str = in.readLine();
                    if (str.equals("CLOSE")) {
                        todos.save();
                        break;
                    }
                    out.println("Received: " + str);
                    out.println(command(str.split(" ")));
                }
                
            }
            catch (IOException e){
                System.out.println(e);
            }
            System.out.println("Client left");
        }

    }

    static Todo todos;

    public static void main(String[] args) throws IOException {
        todos = new Todo();

        try (ServerSocket server = new ServerSocket(6666)) {
            while (true) {
                Socket socket = server.accept();

                System.out.println("New client");
                Thread sc = new ServeConnection(socket);
                sc.run();
            }

        }

    }

    static String command(String[] args) {
        if (args.length < 1) {
            return "Not a command";
        }

        switch (args[0]) {
            case "-a":
                StringBuilder sb = new StringBuilder().append(args[2]);
                for (int i = 3; i < args.length; i++) {
                    sb.append(" ").append(args[i]);
                }
                todos.append(Integer.parseInt(args[1]), sb.toString());
                return "OK: appended\nEND";
            case "-l":
                return todos.list() + "\nEND";
            case "-r":
                return todos.reverse()+ "\nEND";
            case "-d":
                todos.delete();
                break;
            default:
                return "Unrecognized format";
        }
        
        return "End";
    }
}
