package businessLogic;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.util.ArrayList;

import static dao.AbstractDAO.createJTable2;
/**
 * The ProductBusinessLogic class handles the business logic for managing products.
 */
public class ProductBusinessLogic {
    private ProductDAO productDAO;
    /**
     * Constructs a ProductBusinessLogic object and initializes the associated ProductDAO.
     */
    public ProductBusinessLogic(){
        productDAO = new ProductDAO();
    }
    /**
     * Adds a product to the system.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        productDAO.insert(product);
    }
    /**
     * Retrieves a JScrollPane containing a table of all products.
     *
     * @return The JScrollPane containing the product table.
     */
    public JScrollPane table(){
        ProductDAO ionut = new ProductDAO();
        ArrayList<Product> baias = new ArrayList<>();
        baias = ionut.findAll();
        JTable tableorder = createJTable2(baias);
        JScrollPane jScrollPane=new JScrollPane(tableorder);
        return jScrollPane;
    }
    /**
     * Deletes a product with the specified ID.
     *
     * @param id The ID of the product to be deleted.
     */

    public void deleteProduct(int id){
        productDAO.delete(id);
    }

}
