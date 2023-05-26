package presentation;

import businessLogic.ProductBusinessLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProductDelete extends JDialog {
    private JPanel productDelete;
    private JTextField textField11;
    private JButton deleteTheProductButton;

    public ProductDelete(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Product delete");
        setContentPane(productDelete);
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        deleteTheProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductBusinessLogic productBLL=new ProductBusinessLogic();
                productBLL.deleteProduct(Integer.parseInt(textField11.getText()));
            }
        });
        setVisible(true);
    }
}
