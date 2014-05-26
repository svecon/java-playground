package cvicenileto.less03;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * start rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false
 * @author svecon
 */
public class Server extends UnicastRemoteObject implements IServer {

    final private Set<IClient> clients;
    final private List<String> messages;

    public Server() throws RemoteException {
        clients = Collections.synchronizedSet(new HashSet<IClient>());
        messages = Collections.synchronizedList(new ArrayList<String>());
    }

    @Override
    public void post(String msg) throws RemoteException {
        messages.add(msg);
        synchronized (clients) {
            for (IClient cl : clients) {
                cl.notifyMessage();
            }
        }
    }

    @Override
    public String[] list() throws RemoteException {
        return messages.toArray(new String[messages.size()]);
    }

    @Override
    public void register(IClient cl) throws RemoteException {
        clients.add(cl);
    }

    @Override
    public void unregister(IClient cl) throws RemoteException {
        clients.remove(cl);
    }

    public static void main(String[] args) {
        try {
            Naming.rebind("ServerBoard", new Server());
        } catch (MalformedURLException | RemoteException ex) {
            System.out.println(ex);
        }
    }

}
