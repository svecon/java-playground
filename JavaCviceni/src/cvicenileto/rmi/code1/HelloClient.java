package hello;

import java.rmi.*;

public class HelloClient {
  public static void main(String[] args) throws Exception {
    Hello robj = (Hello) Naming.lookup("Hello");
    String mesg = robj.sayHello();
    System.out.println(mesg);
  }
}
