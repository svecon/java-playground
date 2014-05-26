package cvicenileto.reflection.plugin;

public class Main {
  
  public static class Internal implements Plugin {
    @Override
    public void perform(String msg) {
      System.out.println("Internal: "+msg);
    }
  }
  
  public static void main(String[] args) {
    java.util.ArrayList<Plugin> plugins = new java.util.ArrayList<>();
    Class<Plugin> pluginIface = Plugin.class;
    plugins.add(new Internal());
    
    for (int i=0; i<args.length; i++) {
      try {
        Class<?> cls = Class.forName(args[i]);
        if (cls.isArray() || cls.isInterface() || cls.isPrimitive() || cls.isEnum() || cls.isAnnotation()) {
          System.out.println(cls.getName()+" neni trida.");
          continue;
        }
        if (!pluginIface.isAssignableFrom(cls)) {
          System.out.println("Trida " + cls.getName() + " neimplementuje interface Plugin.");
          continue;
        }
        plugins.add((Plugin) cls.newInstance());
      } catch (ClassNotFoundException e) {
        System.out.println("Trida " + args[i] + " neexistuje.");
      } catch (InstantiationException e) {
        System.out.println("Nelze vytvorit instanci od " + args[i]);
      } catch (IllegalAccessException e) {
        System.out.println("Nelze vytvorit instanci od " + args[i]);
      }
    }

    for (Plugin pl : plugins) {
      pl.perform("Ahoj");
    }
  }
}
