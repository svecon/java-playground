package cvicenileto.less03;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author svecon
 */
interface IServer extends Remote {

    void post(String msg) throws RemoteException;

    String[] list() throws RemoteException;

    void register(IClient cl) throws RemoteException;

    void unregister(IClient cl) throws RemoteException;
}
