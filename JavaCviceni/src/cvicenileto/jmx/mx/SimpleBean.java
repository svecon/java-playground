/* $Id$ */
package examples.mbeans.mx;

import javax.management.MXBean;

@MXBean
public interface SimpleBean {
  
  public int getState();
  public void setState(int s);

}
