package presentation;

import businessLogic.ClientBusinessLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientDelete extends JDialog {
    private JTextField textField10;
    private JButton deleteButton;
    private JPanel clientDelete;

    public ClientDelete(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Client delete");
        setContentPane(clientDelete);
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientBusinessLogic clientBLL=new ClientBusinessLogic();
                clientBLL.deleteClient(Integer.parseInt(textField10.getText()));
            }
        });
        setVisible(true);
    }
}
