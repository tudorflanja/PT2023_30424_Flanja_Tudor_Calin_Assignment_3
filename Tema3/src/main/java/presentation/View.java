package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class View extends JDialog {
    private JButton clientsButton;
    private JButton ordersButton;
    private JButton productsButton;
    private JPanel view;

    public View(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Orders Management");
        setContentPane(view);
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientController clientController = new ClientController(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductController productController = new ProductController(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OrderController orderController = new OrderController(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }
}
