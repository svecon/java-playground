/* $Id$ */
package examples.mbeans.rmi;

import java.lang.management.*; 
import javax.management.*;
import javax.management.remote.*;

public class MyClassMain {
  public static void main(String[] args) throws Exception { 
 
    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
 
    ObjectName name = new ObjectName("example.mbeans:type=MyClass"); 
 
    MyClass mbean = new MyClass(); 
    mbs.registerMBean(mbean, name); 

    JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server"); 
    JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs); 
    
    cs.start();
 
    System.out.println("Waiting forever..."); 
    Thread.sleep(Long.MAX_VALUE); 
  } 
}
