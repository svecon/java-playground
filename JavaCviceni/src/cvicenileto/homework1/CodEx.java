package cvicenileto.homework1;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * DU 1.
 *
 * @author Ondrej Svec
 */
public class CodEx {

    private static String stringType(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        } else if (cls.isArray()) {
            return "array";
        } else if (cls.isInterface()) {
            return "interface";
        } else if (cls.isEnum()) {
            return "enum";
        } else {
            return "class";
        }
    }

    public static void main(String[] argv) {

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String ln;
            while ((ln = input.readLine()) != null) {

                try {
                    Class<?> cls = Class.forName(ln);

                    System.out.println(cls.getName());

                    System.out.println(stringType(cls));

                    if (cls.getSuperclass() == null) {
                        System.out.println("null");
                    } else {
                        System.out.println(cls.getSuperclass().getCanonicalName());
                    }

                    Class<?>[] interfaces = cls.getInterfaces();

                    System.out.println(interfaces.length);

                    for (Class<?> iFace : interfaces) {
                        System.out.println(iFace.getCanonicalName());
                    }

                    Method[] methods = cls.getMethods();

                    int staticC = 0;
                    for (Method method : methods) {
                        if (Modifier.isStatic(method.getModifiers())) {
                            staticC++;
                        }
                    }

                    System.out.println(methods.length);

                    System.out.println(staticC);

                    Class<?>[] inners = cls.getClasses();

                    System.out.println(inners.length);

                    for (Class<?> inner : inners) {
                        System.out.println(inner.getName());
                    }

                } catch (ClassNotFoundException ex) {
                    System.out.println(ex.getMessage() + " does not exist");
                }

                System.out.println();

            }
        } catch (IOException ex) {

        }

    }
}
