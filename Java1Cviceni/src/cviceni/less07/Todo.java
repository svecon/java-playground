/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sveco
 */
public class Todo implements Closeable {

    public Todo(){
        load(ftodos);
    }
    
    @Override
    public void close() throws IOException {
        save(ftodos);
    }

    static class TodoItem implements Comparable<TodoItem> {

        @Override
        public int compareTo(TodoItem o) {
            if (priority < o.priority) {
                return -1;
            } else if (priority > o.priority) {
                return 1;
            }

            return message.compareTo(o.message);
        }
        int priority;
        String message;

        public TodoItem(int priority, String message) {
            this.priority = priority;
            this.message = message;
        }

        public TodoItem(String line) {
            String[] items = line.split("::");

            try {
                priority = Integer.parseInt(items[0]);
                message = items[1];
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new IllegalArgumentException("Corrupted line");
            }
        }

        @Override
        public String toString() {
            return priority + "::" + message;
        }
    }
    
    private File ftodos = new File("C:\\java\\src\\todos.txt"); //System.getProperty("user.home"), ".javatodo"

    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Not a command.");
//            return;
//        }
//
//        load(ftodos);
//
//        switch (args[0]) {
//            case "-a":
//                StringBuilder sb = new StringBuilder().append(args[2]);
//                for (int i = 3; i < args.length; i++) {
//                    sb.append(" ").append(args[i]);
//                }
//                append(Integer.parseInt(args[1]), sb.toString());
//                break;
//            case "-l":
//                list();
//                break;
//            case "-r":
//                reverse();
//                break;
//            case "-d":
//                delete();
//                break;
//            default:
//                System.out.println("Unrecognized format.");
//                return;
//        }
//        save(ftodos);
    }
    
    static ArrayList<TodoItem> items = new ArrayList<>();

    static void load(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String ln;
            while ((ln = br.readLine()) != null) {
                items.add(new TodoItem(ln));
            }
            Collections.sort(items);

        } catch (IOException e) {
            System.out.println("There was an error while loading the file.");
            System.out.println(e);
        }
    }

    public void append(int priority, String message) {
        items.add(new TodoItem(priority, message));
        Collections.sort(items);
    }

    public String list() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(i).append("::");
            sb.append(items.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String reverse() {
        StringBuilder sb = new StringBuilder();
        for (int i = items.size() - 1; i >= 0; i--) {
            sb.append(items.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void delete() {
        int selected = -2;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            do {
                list();
                
                System.out.print("Insert ID which you want to delete: ");
                selected = Integer.parseInt(in.readLine());
                
                if (selected >= 0 && selected < items.size() ) {
                    items.remove(selected);
                }
            } while (selected != -1);

        } catch (IOException e) {
        }



    }

    public void save() {
        save(ftodos);
    }
    
    void save(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            for (int i = 0; i < items.size(); i++) {
                bw.write(items.get(i).toString());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("There was an error while saving the file.");
        }
    }
}
