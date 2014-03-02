package cvicenileto.less01;

public class FilterToLower implements Filter {

    @Override
    public String menuText() {
        return "To Lower";
    }

    @Override
    public String process(String s) {
        return s.toLowerCase();
    }

}
