package cvicenileto.rmi.code2;

import java.rmi.*;

public class HelloClient {
  public static void main(String[] args) throws Exception {
    Hello robj = (Hello) Naming.lookup("//localhost:2000/Hello");
    String mesg = robj.sayHello();
    System.out.println(mesg);
  }
}
