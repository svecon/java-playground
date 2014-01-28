/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less06;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sveco
 */
public class AddUser {

    private static class User {

        String name;
        String fullName;
        int uid;
        int gid;
        String home;
        String shell;
        String pwd;

        private User() {
        }

        public static User fromLine(String line) {
            User u = new User();
            String[] items = line.split(":");
            for (int i = 0; i < items.length; i++) {
                switch (i) {
                    case 0:
                        u.name = items[i];
                        break;
                    case 1:
                        u.pwd = items[i];
                        break;
                    case 2:
                        u.uid = Integer.parseInt(items[i]);
                        break;
                    case 3:
                        u.gid = Integer.parseInt(items[i]);
                        break;
                    case 4:
                        u.fullName = items[i];
                        break;
                    case 5:
                        u.home = items[i];
                        break;
                }
            }
            return u;
        }

        @Override
        public String toString() {
            return name + ":" + uid + ":" + gid + ":" + fullName + ":" + home + ":" + shell;
        }
    }

    public static void main(String[] args) {
        // Copy.copy("/etc/passwd", ".");
        File f = new File("passwd");

        ArrayList<User> users = new ArrayList<User>();
        int maxUID = 0;
        User newUser = new User();
        String input;

        try (BufferedReader passwd = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = passwd.readLine()) != null) {
                User u = User.fromLine(line);
                users.add(u);
                if (u.uid > maxUID) {
                    maxUID = u.uid;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Welcome to the user registration wizard!");
            System.out.println("Enter desired username:");

            boolean repeat = false;
            do {
                input = in.readLine();
                for (User u : users) {
                    repeat = input.equalsIgnoreCase(u.name);

                    if (repeat) {
                        System.out.println("This username already exists, enter a new one:");
                        break;
                    }
                }

            } while (repeat);
            newUser.name = input;

            System.out.println("Enter UID > " + maxUID);

            repeat = false;
            do {
                input = in.readLine();
                repeat = Integer.parseInt(input) < maxUID;

                if (repeat) {
                    System.out.println("Enter UID > " + maxUID);
                }


            } while (repeat);


            newUser.uid = Integer.parseInt(input);
            newUser.pwd = "x";
            newUser.gid = 999;
            newUser.fullName = "Pepa Maděra";
            newUser.home = "/home/xx";

            BufferedWriter writer = new BufferedWriter(new FileWriter("passwd", true));

            writer.write(newUser.toString());
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
