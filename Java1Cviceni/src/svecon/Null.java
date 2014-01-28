package svecon;

public class Null {
  public static void main(String[] argv) {
    ((Null) null).hello();
  }
  public static void hello() {
    System.out.println("Hello world!");
  }
}
