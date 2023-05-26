package model;
/**
 * The Product class represents a product entity.
 */
public class Product {
    private int id;
    private String name;
    private int quantity;
    private int price;
    /**
     * Constructs a new Product instance with the specified ID, name, quantity, and price.
     *
     * @param id       The ID of the product.
     * @param name     The name of the product.
     * @param quantity The quantity of the product.
     * @param price    The price of the product.
     */
    public Product(int id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    /**
     * Constructs a new empty Product instance.
     */
    public Product(){}
    /**
     * Constructs a new Product instance with the specified name, quantity, and price.
     *
     * @param name     The name of the product.
     * @param quantity The quantity of the product.
     * @param price    The price of the product.
     */
    public Product(String name, int quantity, int price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
