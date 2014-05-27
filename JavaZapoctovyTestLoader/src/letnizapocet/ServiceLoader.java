package letnizapocet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 *
 * @author sveco
 */
public class ServiceLoader<T> implements Iterable<T> {

    ArrayList<T> plugins;

    private ServiceLoader(ArrayList xs) {
        plugins = xs;
    }

    public static ServiceLoader load(Class clazz) {

        ArrayList pluginsLocal = new java.util.ArrayList();

        try {
            ClassLoader cloader = ClassLoader.getSystemClassLoader();
            Enumeration<URL> services = cloader.getResources("META-INF/services/"+ clazz.getCanonicalName());

            while (services.hasMoreElements()) {
                URL url = services.nextElement();
                
                try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {

                    String ln;
                    while ((ln = br.readLine()) != null) {

                        try {

                            Class cls = Class.forName(ln);
                            if (cls.isArray() || cls.isInterface() || cls.isPrimitive()) {
                                System.out.println(cls.getName() + " neni trida.");
                                return null;
                            }
                            if (!clazz.isAssignableFrom(cls)) {
                                System.out.println("Trida " + cls.getName() + " neimplementuje interface.");
                                return null;
                            }

                            pluginsLocal.add(cls.newInstance());
                        } catch (ClassNotFoundException e) {
                            System.out.println("Trida " + null + " neexistuje.");
                        } catch (InstantiationException e) {
                            System.out.println("Nelze vytvorit instanci od " + null);
                        } catch (IllegalAccessException e) {
                            System.out.println("Nelze vytvorit instanci od " + null);
                        }

                    }
                } catch (IOException e) {
                    System.out.println("Nelze nacist META-INF service: " + e.getMessage());
                }

            }

        } catch (IOException ex) {
            System.out.println("not found resources");
        }

        

        return new ServiceLoader(pluginsLocal);
    }

    @Override
    public Iterator<T> iterator() {
        return plugins.iterator();
    }

}
