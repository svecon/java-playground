package cvicenileto.less01;

public class FilterToUpper implements Filter {

    @Override
    public String menuText() {
        return "To Upper";
    }

    @Override
    public String process(String s) {
        return s.toUpperCase();
    }

}
