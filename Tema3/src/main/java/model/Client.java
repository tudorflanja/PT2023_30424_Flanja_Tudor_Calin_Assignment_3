package model;
/**
 * The Client class represents a client entity.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private int age;
    /**
     * Constructs a new Client instance with the specified ID, name, email, and age.
     *
     * @param ID    The ID of the client.
     * @param name  The name of the client.
     * @param email The email of the client.
     * @param age   The age of the client.
     */
    public Client(int ID, String name, String email, int age) {
        this.id = ID;
        this.name = name;
        this.email = email;
        this.age = age;
    }
    /**
     * Constructs a new empty Client instance.
     */
    public Client(){}

    /**
     * Constructs a new Client instance with the specified name, email, and age.
     *
     * @param name  The name of the client.
     * @param email The email of the client.
     * @param age   The age of the client.
     */
    public Client(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    /**
     * Returns a string representation of the Client object.
     *
     * @return A string representation of the Client object.
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
