package cvicenileto.less04;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class JavaBeansProperties {

    public static void main(String[] args) {
        // java.swing.JButton
        try {
            Class<?> clazz = Class.forName(args[0]);
            BeanInfo bi = Introspector.getBeanInfo(clazz);
            
            System.out.println("JavaBean " + clazz.getName());
            for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
                Class pt = pd.getPropertyType();
                System.out.println("Property " + (pt == null ? "null" : pt.getName() + " " + pd.getName()));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JavaBean not found.");
        } catch (IntrospectionException e) {
            System.out.println("JavaBean info cannot be obtained.");
        }
    }

}
