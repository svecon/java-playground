package cvicenileto.rmi.code4;

import java.io.*;
import java.net.*;
import java.rmi.server.*;

public class ShiftClientSocketFactory implements RMIClientSocketFactory, Serializable {

  private int number;

  public ShiftClientSocketFactory(int number) {
    this.number = number;
  }
    
  public Socket createSocket(String host, int port) throws IOException {
    return new ShiftSocket(host, port, number);
  }
    
  public int hashCode() {
    return number;
  }

  public boolean equals(Object obj) {
    return (getClass() == obj.getClass() && number == ((ShiftClientSocketFactory) obj).number);
  }
}

