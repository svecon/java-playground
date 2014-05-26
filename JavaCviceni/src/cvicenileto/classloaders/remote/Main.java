/* $Id$ */
package cvicenileto.classloaders.remote;

public class Main {
  
  public static void main(String[] argv) throws Exception {
    ClassLoader cl = new URLClassLoader("http://localhost:8888/");
    MyInterface obj = (MyInterface) Class.forName("RemoteClassLoaderTest.MyClass", true, cl).newInstance();   
    
    obj.print();
  }
  
}
