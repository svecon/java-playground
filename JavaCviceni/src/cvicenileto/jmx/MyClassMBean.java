/* $Id$ */
package examples.mbeans;

public interface MyClassMBean {
  public int getState();
  public void setState(int s);
  public void reset();
}
