package presentation;

import businessLogic.ProductBusinessLogic;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProductController extends JDialog {

    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton addProductButton;
    private JPanel productController;
    private JButton deleteProductButton;
    private JButton viewProductsButton;
    private JButton editProductsButton;

    public ProductController(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Products");
        setContentPane(productController);
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product(textField5.getText(), Integer.parseInt(textField6.getText()),Integer.parseInt(textField7.getText()));
                ProductBusinessLogic productBusinessLogic = new ProductBusinessLogic();
                try {
                    productBusinessLogic.addProduct(product);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductTable tabela = new ProductTable(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProductDelete sterge = new ProductDelete(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }
}
