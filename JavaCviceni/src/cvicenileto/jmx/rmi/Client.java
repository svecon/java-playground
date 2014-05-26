/* $Id$ */
package examples.mbeans.rmi;

import java.lang.management.*; 
import javax.management.*;
import javax.management.remote.*;
import java.util.*;

public class Client {

  public static void main(String[] args) throws Exception { 
    JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server"); 
    JMXConnector jmxc = JMXConnectorFactory.connect(url, null); 
    MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

    ObjectName mname = new ObjectName("example.mbeans:type=MyClass"); 
    ObjectInstance inst = null;

    Set<ObjectInstance> beans = mbsc.queryMBeans(null, null);
    for (ObjectInstance oi : beans) {
      ObjectName name = oi.getObjectName();
      System.out.println(name);
      
      if (name.equals(mname)) {
        inst = oi;
        System.out.println("OK");
      }
    }

    System.out.println(mbsc.getAttribute(mname, "State"));
    mbsc.setAttribute(mname, new Attribute("State", new Integer(100)));

    MyClassMBean proxy = (MyClassMBean) JMX.newMBeanProxy(mbsc, mname, MyClassMBean.class, true);
    System.out.println(proxy.getState());
    

    jmxc.close();
  }
}
