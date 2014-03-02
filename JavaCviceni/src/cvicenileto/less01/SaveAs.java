package cvicenileto.less01;

import java.io.File;
import java.io.IOException;

interface SaveAs {

    String menuText();

    String extension();

    void save(File f, String s) throws IOException;
}
