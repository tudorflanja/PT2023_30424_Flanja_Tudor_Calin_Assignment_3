package presentation;

import businessLogic.ClientBusinessLogic;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ClientTabel extends JDialog {
    private JPanel clientPanel;

    public ClientTabel(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Clients Table");
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ClientBusinessLogic clientBusinessLogic = new ClientBusinessLogic();
        ClientTabel.this.add(clientBusinessLogic.table());
        setVisible(true);
    }
}
