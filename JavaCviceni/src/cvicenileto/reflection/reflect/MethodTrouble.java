/* $Id$ */
package cvicenileto.reflection.reflect;

import java.lang.reflect.*;

public class MethodTrouble<T> {

  public void lookup(T value) {}

  public static void main(String[] argv) throws Exception {
    Class<?> c = (new MethodTrouble<Integer>()).getClass();
    Method m = c.getMethod("lookup", Integer.class);
    System.out.println(m);
  }
}
