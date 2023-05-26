package model;
/**
 * The Orders class represents an order entity.
 */
public class Orders {
    private int id;
    private int client_id;
    private int product_id;
    private int number_of_products;
    /**
     * Constructs a new Orders instance with the specified order ID, client ID, product ID, and number of products.
     *
     * @param order_id           The ID of the order.
     * @param client_id          The ID of the client associated with the order.
     * @param product_id         The ID of the product associated with the order.
     * @param number_of_products The number of products in the order.
     */
    public Orders(int order_id, int client_id, int product_id, int number_of_products) {
        this.id = order_id;
        this.client_id = client_id;
        this.product_id = product_id;
        this.number_of_products = number_of_products;
    }
    /**
     * Constructs a new empty Orders instance.
     */
    public Orders(){}
    /**
     * Constructs a new Orders instance with the specified client ID, product ID, and number of products.
     *
     * @param client_id          The ID of the client associated with the order.
     * @param product_id         The ID of the product associated with the order.
     * @param number_of_products The number of products in the order.
     */
    public Orders(int client_id, int product_id, int number_of_products) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.number_of_products = number_of_products;
    }

    public int getId() {return id;}
    public void setId(int order_id) {this.id = order_id;}
    public int getclient_id() {return client_id;}
    public void setclient_id(int client_id) {this.client_id = client_id;}
    public int getproduct_id() {return product_id;}
    public void setproduct_id(int product_id) {this.product_id = product_id;}
    public int getnumber_of_products() {return number_of_products;}
    public void setnumber_of_products(int number_of_products) {this.number_of_products = number_of_products;}
}
