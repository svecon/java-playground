package cvicenileto.less03;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author svecon
 */
public class Client implements Remote, IClient, Serializable {

    final ClientMain ui;
    
    public Client(ClientMain ui){
        this.ui = ui;
    }
    
    @Override
    public void notifyMessage() throws RemoteException {
        ui.RefreshBoard();
    }
    
}
