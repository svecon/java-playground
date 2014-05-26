package cvicenileto.rmi.code4;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;


public class HelloImpl extends UnicastRemoteObject implements Hello {

  public HelloImpl() throws RemoteException {
    super(0, new ShiftClientSocketFactory(20), new ShiftServerSocketFactory(20));
  }

  public String sayHello() throws RemoteException {
    return "Hello, world!";
  }

  public static void main(String args[]) throws Exception {
    HelloImpl obj = new HelloImpl();
    Naming.rebind("//localhost:2000/Hello", obj);
  }
}
