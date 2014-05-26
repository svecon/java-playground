/* $Id$ */
package examples.mbeans.rmi;

public interface MyClassMBean {
  public int getState();
  public void setState(int s);
  public void reset();
}
