/* $Id$ */
package examples.mbeans.rmi;

public class MyClass implements MyClassMBean {

  private int state = 0;
  private String hidden = null;
  
  public int getState() {
    return(state);
  }

  public void setState(int s) {
    state = s;
  }

  public String getHidden() {
    return(hidden);
  }

  public void setHidden(String h) {
    hidden = h;
  }

  public void reset() {
    state = 0;
    hidden = null;
  }
}
