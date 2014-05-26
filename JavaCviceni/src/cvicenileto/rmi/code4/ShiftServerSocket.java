/* $Id$ */
package cvicenileto.rmi.code4;

import java.io.*;
import java.net.*;

class ShiftServerSocket extends ServerSocket {
  
  private int number;
  
  public ShiftServerSocket(int port, int number) throws IOException {
    super(port);
    this.number = number;
  }
  
  public Socket accept() throws IOException {
    Socket s = new ShiftSocket(number);
    implAccept(s);
    return s;
  }
}
