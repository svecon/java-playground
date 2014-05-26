package cvicenileto.rmi.code1;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;


public class HelloImpl extends UnicastRemoteObject implements Hello {

  public HelloImpl() throws RemoteException {}

  public String sayHello() throws RemoteException {
    System.out.println("sayHello called");
    return "Hello, world!";
  }

  public static void main(String args[]) throws Exception {
    HelloImpl obj = new HelloImpl();
    System.out.println("Hello object created.");
    Naming.rebind("Hello", obj);
  }
}
