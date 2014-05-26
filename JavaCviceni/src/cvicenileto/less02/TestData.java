package cvicenileto.less02;

/**
 *
 * @author svecon
 */
public class TestData {

    @FormField(name = "User name", kind = FieldKind.TEXT)
    String name;

    @FormField(name = "User password",
            kind = FieldKind.PASSWORD)
    String password;

    boolean active;

    TestData(String name, String password, boolean active) {
        this.name = name;
        this.password = password;
        this.active = active;
    }
}
