package cvicenileto.less03;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author svecon
 */
public class ClientMain extends JFrame {

    final Container container;
    final IServer server;
    final JTextArea board;
    final Client client;

    public ClientMain() {
        super("Nástěnka");

        client = new Client(this);

        IServer server_temp = null;

        try {
            server_temp = (IServer) Naming.lookup("ServerBoard");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println(ex);
        }

        server = server_temp;

        container = this.getContentPane();

        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new BorderLayout());

        board = new JTextArea();
        board.setEditable(false);
        container.add(board, BorderLayout.CENTER);

        createAndShowGUI();
    }

    public void RefreshBoard() {
        board.setText(null);
        try {
            for (String message : server.list()) {
                board.append(message + "\n");
            }
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }

    final void createAndShowGUI() {
        JToolBar bar = new JToolBar("Bar");

        JButton register = new JButton("Register");
        JButton unregister = new JButton("Unregister");
        JButton send = new JButton("Send");
        JButton reload = new JButton("Reload");

        bar.add(register);
        bar.add(unregister);
        bar.add(reload);

        container.add(bar, BorderLayout.NORTH);

        final JTextField message = new JTextField("");

        JPanel pMessage = new JPanel();
        pMessage.setLayout(new BorderLayout());

        pMessage.add(message, BorderLayout.CENTER);
        pMessage.add(send, BorderLayout.EAST);

        container.add(pMessage, BorderLayout.SOUTH);

        register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server.register(client);
                } catch (RemoteException ex) {
                    System.out.println(ex);
                }
            }
        });

        unregister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server.unregister(client);
                } catch (RemoteException ex) {
                    System.out.println(ex);
                }
            }
        });

        reload.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RefreshBoard();
            }
        });

        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server.post(message.getText());
                } catch (RemoteException ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientMain();
            }
        });

    }

}
