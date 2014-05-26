/* $Id$ */
package examples.mbeans.not;

public interface MyClassMBean {
  public int getState();
  public void setState(int s);
  public void reset();
}
