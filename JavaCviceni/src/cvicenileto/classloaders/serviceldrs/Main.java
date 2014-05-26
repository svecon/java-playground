package cvicenileto.classloaders.serviceldrs;

import java.util.*;

public class Main {
  
/*
  public static class Internal implements Plugin {
    public void perform(String msg) {
      System.out.println("Internal: "+msg);
    }
  }*/
  
  public static void main(String[] args) {
    /*
    java.util.ArrayList plugins = new java.util.ArrayList();
    plugins.add(new Internal());
    
    for (int i=0; i<args.length; i++) {
      Class pluginIface = Plugin.class;
      try {
        Class cls = Class.forName(args[i]);
        if (cls.isArray() || cls.isInterface() || cls.isPrimitive()) {
          System.out.println(cls.getName()+" neni trida.");
          continue;
        }
        if (!pluginIface.isAssignableFrom(cls)) {
          System.out.println("Trida "+cls.getName()+" neimplementuje interface Plugin.");
          continue;
        }
        plugins.add(cls.newInstance());
      } catch (ClassNotFoundException e) {
        System.out.println("Trida "+args[i]+" neexistuje.");
      } catch (InstantiationException e) {
        System.out.println("Nelze vytvorit instanci od "+args[i]);
      } catch (IllegalAccessException e) {
        System.out.println("Nelze vytvorit instanci od "+args[i]);
      }
    } */

    ServiceLoader<Plugin> sl = ServiceLoader.load(Plugin.class);

    for (Plugin pl : sl) {
      pl.perform("Ahoj");
    }
  }
}
