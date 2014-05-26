/* $Id$ */
package cvicenileto.security;
import java.io.Console;

public class MainExit {

  public static void main(String[] argv) {
    if (argv.length > 0) {
      System.setSecurityManager(new ExitSecurityManager());
    }
    Console cons = System.console();
    cons.printf("call Bad Exit? [y/n]\n");
    if (cons.readLine().equals("y")) {
      new BadExit().exit();
    }
    System.exit(0);
  }

  private static class ExitSecurityManager extends SecurityManager {
    @Override
    public void checkExit(int status) throws SecurityException {
      super.checkExit(status);
      Class[] context = getClassContext();
      for (Class clazz : context) {
        String name = clazz.getName();
        if (!name.startsWith("java.") && !name.startsWith("MainExit")) {
          System.out.println("No exit allowed from " + name);
          throw new SecurityException();
        }
      }
    }
  }
}
