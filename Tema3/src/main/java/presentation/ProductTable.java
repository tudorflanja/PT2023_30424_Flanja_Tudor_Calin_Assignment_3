package presentation;

import businessLogic.ProductBusinessLogic;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ProductTable extends JDialog{
    private JPanel productPanel;

    public ProductTable(JFrame parent) throws SQLException{
        super(parent);
        setTitle("Products Table");
        setMinimumSize(new Dimension(800,400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ProductBusinessLogic productBusinessLogic = new ProductBusinessLogic();
        ProductTable.this.add(productBusinessLogic.table());
        setVisible(true);
    }
}
