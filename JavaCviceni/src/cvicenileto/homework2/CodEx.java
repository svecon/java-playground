package cvicenileto.homework2;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;

/**
 * DU 2.
 *
 * @author Ondrej Svec
 */
public class CodEx {

    public static void main(String[] argv) {

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String l;

//        l = "javax.swing.JComponent";
//        try {
//            Class<?> clazz = Class.forName(l);
//            writeJavaBeanInfo(clazz);
//        } catch (ClassNotFoundException e) {
//            System.out.println("\"" + l + "\" does not exist");
//        }
            while ((l = input.readLine()) != null) {
                l = l.trim();

                try {
                    Class<?> clazz = Class.forName(l);
                    writeJavaBeanInfo(clazz);
                } catch (ClassNotFoundException e) {
                    System.out.println("\"" + l + "\" does not exist");
                }
                
                System.out.println();
            }
        } catch (IOException ex) {
            System.out.println("Nastala IOException");
        }
    }

    static void writeJavaBeanInfo(Class<?> clazz) {

        try {
            BeanInfo bi = Introspector.getBeanInfo(clazz);

            System.out.println("JavaBean Name: " + clazz.getName());

            /// PROPERTIES
            for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {

                String write = "";
                if (pd.getWriteMethod() == null) {
                    write += "readonly ";
                }
                if (pd.isBound()) {
                    write += "bound ";
                }

                Class pt = pd.getPropertyType();
                if (pt == null) {
                    continue;
                }

                System.out.println(write + "property " + pt.getName() + " " + pd.getName());
            }

            /// LISTENERS
            for (EventSetDescriptor pd : bi.getEventSetDescriptors()) {
                Class pt = pd.getListenerType();
                if (pt == null) {
                    continue;
                }

                System.out.println("listener " + pt.getName());

                for (Method method : pt.getMethods()) {
                    System.out.println("  " + method.getName());
                }

            }

            /// METHODS
            for (MethodDescriptor pd : bi.getMethodDescriptors()) {
                System.out.println("method " + pd.getName());
            }

        } catch (IntrospectionException e) {
        }
    }
}
