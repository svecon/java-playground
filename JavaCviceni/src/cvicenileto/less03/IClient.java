package cvicenileto.less03;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author svecon
 */
interface IClient extends Remote {

    void notifyMessage() throws RemoteException;
}
