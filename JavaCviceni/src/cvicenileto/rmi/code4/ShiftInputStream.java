/* $Id$ */
package cvicenileto.rmi.code4;

import java.io.*;

class ShiftInputStream extends FilterInputStream {
  
  private int number;
  
  public ShiftInputStream(InputStream in, int number) {
    super(in);
    this.number = number;
  }
  
  public int read() throws IOException {
    int b = in.read();
    if (b != -1)
      b = (byte) (((b - number) % 0x100) & 0xFF );
    System.out.println("ShiftInputStream read: "+b);
    return b;
  }
  
  public int read(byte b[], int off, int len) throws IOException {
    int numBytes = in.read(b, off, len);

    if (numBytes <= 0)
      return numBytes;
    for(int i = 0; i < numBytes; i++) {
      b[off + i] = (byte) (((b[off+i] - number) % 0x100) & 0xFF );
      System.out.println("ShiftInputStream read[]: "+b[off+i]);
    }
    return numBytes;
  }
}



