package letnizapocet;

/**
 *
 * @author sveco
 */
public class Letnizapocet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        ServiceLoader<Plugin> sl = ServiceLoader.load(Plugin.class);
        
        for (Plugin plugin : sl) {
            plugin.perform("AHOJ!");
        }
        
    }
    
}
