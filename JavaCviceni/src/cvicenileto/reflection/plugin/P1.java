package cvicenileto.reflection.plugin;

public class P1 implements Plugin {
  
  @Override
  public void perform(String msg) {
    System.out.println("P1: "+msg);
  }
}
