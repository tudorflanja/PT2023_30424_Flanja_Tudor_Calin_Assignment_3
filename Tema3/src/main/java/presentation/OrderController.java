package presentation;

import businessLogic.OrderBusinessLogic;
import model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import model.Product;

public class OrderController extends JDialog {

    private JPanel orderController;
    public OrderBusinessLogic orderBusinessLogic = new OrderBusinessLogic();
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JButton placeTheOrderButton;
    private JComboBox comboBoxClient;

    public OrderController(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Orders");
        setContentPane(orderController);
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        placeTheOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idClient = (Integer.parseInt(textField16.getText()));
                int idProduct = (Integer.parseInt(textField17.getText()));
                int cantitate = (Integer.parseInt(textField15.getText()));

                Orders order = new Orders(idClient,idProduct,cantitate);
                orderBusinessLogic.addOrder(order);

            }
        });
        setVisible(true);
    }
}
