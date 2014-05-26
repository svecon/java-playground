/* $Id$ */
package hello;


import java.io.*;

class ShiftOutputStream extends FilterOutputStream {
  
  private int number;
  
  public ShiftOutputStream(OutputStream out, int number) {
    super(out);
    this.number = number;
  }
  
  public void write(int b) throws IOException {
    out.write( (byte)(((b + number) % 0x100) & 0xff));
    System.out.println("ShiftOutputStream write: "+b);
  }
}
