
import java.io.*;

public class Main {
  
  public static void main(String[] argv) throws Exception {
    //System.out.println(System.getSecurityManager());
    if (argv.length > 0) {
      System.setSecurityManager(new SecurityManager());
    }
    BufferedReader r = new BufferedReader(new FileReader("build.xml"));
    String line;
    while ((line = r.readLine()) != null) {
      System.out.println(line);
    }
    r.close();
  }

}
