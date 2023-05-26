package presentation;

import businessLogic.ClientBusinessLogic;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientController extends JDialog{
    private JPanel clientController;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton viewClientsButton;
    private JButton addClientButton;
    private JButton editClientsButton;
    private JButton deleteClientButton;

    public ClientController(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Clients");
        setContentPane(clientController);
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(textField2.getText(),textField3.getText(),Integer.parseInt(textField4.getText()));
                ClientBusinessLogic clientBusinessLogic = new ClientBusinessLogic();
                try {
                    clientBusinessLogic.addClient(client);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        viewClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientTabel tabel = new ClientTabel(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientDelete delete = new ClientDelete(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }
}
