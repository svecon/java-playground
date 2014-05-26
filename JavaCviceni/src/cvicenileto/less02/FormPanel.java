package cvicenileto.less02;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author svecon
 */
public class FormPanel<T> extends JPanel {

    private final Class<T> clazz;
    private final LinkedHashMap<String, JComponent> jFields = new LinkedHashMap<>();

    public FormPanel(Class<T> clazz) {
        this.clazz = clazz;

        createAndShowGUI();
    }

    void setObjectValues(T obj) {

        Class c = obj.getClass();

        for (Field field : c.getDeclaredFields()) {
            
            JComponent jComp = jFields.get(field.getName());

            try {

                if (jComp instanceof JCheckBox) {
                    ((JCheckBox) jComp).setSelected(field.getBoolean(obj));
                } else {
                    ((JTextField) jComp).setText((String)field.get(obj));
                }

            } catch (IllegalAccessException e) {
                System.out.println(e.toString());
            }

//            try {
//
//                if (field.isAnnotationPresent(FormField.class)) {
//                    FormField anno = field.getAnnotation(FormField.class);
//
//                    switch (anno.kind()) {
//                        case TEXT:
//                            ((JTextField) jComp).setText(field.get(obj).toString());
//                            break;
//                        case PASSWORD:
//                            ((JPasswordField) jComp).setText(field.get(obj).toString());
//                            break;
//                        case BOOLEAN:
//                            ((JCheckBox) jComp).setSelected(field.getBoolean(obj));
//                            break;
//                    }
//                } else {
//                    if (boolean.class.equals(field.getType())) {
//                        ((JCheckBox) jComp).setSelected(field.getBoolean(obj));
//                    } else {
//                        ((JTextField) jComp).setText(field.get(obj).toString());
//                    }
//                }
//            } catch (IllegalAccessException e) {
//                System.out.println(e.toString());
//            }
        }

    }

    void getObjectValues(T obj) {
    }

    private void createAndShowGUI() {
        setLayout(new GridBagLayout());

        for (Field field : clazz.getDeclaredFields()) {

            String label;
            JComponent comp = null;

            if (field.isAnnotationPresent(FormField.class)) {
                FormField anno = field.getAnnotation(FormField.class);

                label = anno.name();

                switch (anno.kind()) {
                    case TEXT:
                        comp = new JTextField();
                        break;
                    case PASSWORD:
                        comp = new JPasswordField();
                        break;
                    case BOOLEAN:
                        comp = new JCheckBox();
                        break;
                }
            } else {
                if (boolean.class.equals(field.getType())) {
                    comp = new JCheckBox();
                } else {
//                if (String.class.equals(f.getType())) {
                    comp = new JTextField();
                }

                StringBuilder sb = new StringBuilder(field.getName());
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                label = sb.toString();
            }

            jFields.put(field.getName(), comp);

            add(new JLabel(label), gbc(0, jFields.size()));
            add(comp, gbc(1, jFields.size()));
        }
    }

    static GridBagConstraints gbc(int x, int y) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x;
        g.gridy = y;
        g.ipadx = 50;
        g.ipady = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.LINE_START;
        return g;
    }
}
