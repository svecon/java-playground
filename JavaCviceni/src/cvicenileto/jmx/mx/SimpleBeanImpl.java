/* $Id$ */
package examples.mbeans.mx;

import javax.management.MXBean;

public class SimpleBeanImpl implements SimpleBean {
  private int state = 0;
  
  public int getState() {
    return(state);
  }

  public void setState(int s) {
    state = s;
  }

}
