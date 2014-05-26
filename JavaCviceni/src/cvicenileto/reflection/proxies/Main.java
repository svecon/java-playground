/* $Id$ */
package cvicenileto.reflection.proxies;

import java.lang.reflect.*;
import java.util.*;

public class Main {

  public static void main(String[] argv) throws Exception {
    AClass1 cl1 = new AClass1();
    AClass2 cl2 = new AClass2();

    List<AnInterface> lst = new LinkedList<>();
    lst.add((AnInterface) Proxy.newProxyInstance(AnInterface.class.getClassLoader(), new Class[] { AnInterface.class }, new Handler(cl1)));
    lst.add((AnInterface) Proxy.newProxyInstance(AnInterface.class.getClassLoader(), new Class[] { AnInterface.class }, new Handler(cl2)));

    for (AnInterface i : lst) {
      i.foo("message");
    }
  }
}
