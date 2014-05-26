package hello;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;


public class HelloImpl extends UnicastRemoteObject implements Hello {

  public HelloImpl() throws RemoteException {}

  public String sayHello() throws RemoteException {
    return "Hello, world!";
  }

  public static void main(String args[]) throws Exception {
    HelloImpl obj = new HelloImpl();
    Naming.rebind("//localhost:2000/Hello", obj);
  }
}
