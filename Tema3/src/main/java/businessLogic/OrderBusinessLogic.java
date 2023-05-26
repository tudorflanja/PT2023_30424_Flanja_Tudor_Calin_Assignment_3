package businessLogic;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Orders;
import model.Product;

/**
 * The OrderBusinessLogic class handles the business logic for managing orders.
 */
public class OrderBusinessLogic {
    private OrderDAO orderDAO;
    /**
     * Constructs an OrderBusinessLogic object and initializes the associated OrderDAO.
     */
    public OrderBusinessLogic(){
        orderDAO = new OrderDAO();
    }
    /**
     * Adds an order to the system.
     *
     * @param order The order to be added.
     */
    public void addOrder(Orders order) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(order.getproduct_id());
        orderDAO.insert(order);
        int cantitatea = product.getQuantity();
        cantitatea = cantitatea - order.getnumber_of_products();
        product.setQuantity(cantitatea);
        productDAO.update(product,product.getId());
    }
}
