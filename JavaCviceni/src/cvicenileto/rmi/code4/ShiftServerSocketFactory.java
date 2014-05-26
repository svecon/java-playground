/* $Id$ */
package cvicenileto.rmi.code4;

import java.io.*;
import java.net.*;
import java.rmi.server.*;

public class ShiftServerSocketFactory implements RMIServerSocketFactory {

  private int number;

  public ShiftServerSocketFactory(int number) {
    this.number = number;
  }
    
  public ServerSocket createServerSocket(int port) throws IOException {
    return new ShiftServerSocket(port, number);
  }
    
  public int hashCode() {
    return number;
  }

  public boolean equals(Object obj) {
    return (getClass() == obj.getClass() && number == ((ShiftServerSocketFactory) obj).number);
  }

}

