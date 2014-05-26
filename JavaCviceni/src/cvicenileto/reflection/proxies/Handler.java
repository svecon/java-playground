/* $Id$ */
package cvicenileto.reflection.proxies;

import java.lang.reflect.*;

public class Handler implements InvocationHandler {

  private Object obj;
  private Method method;

  public Handler(Object obj) throws NoSuchMethodException {
    this.obj = obj;
    Class clazz = obj.getClass();
    method = clazz.getMethod("foo", String.class);
    // test whether obj has foo(String) method
  } 

  public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
    if (m.getName().equals("foo")) {
      method.invoke(obj, args[0]);
      return null;
    } else {
      throw new NoSuchMethodException("foo");
    }
  }
}
