package businessLogic;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.util.ArrayList;

import static dao.AbstractDAO.createJTable2;

/**
 * The ClientBusinessLogic class handles the business logic for managing clients.
 */

public class ClientBusinessLogic {
    private ClientDAO clientDAO;
    /**
     * Constructs a ClientBusinessLogic object and initializes the associated ClientDAO.
     */
    public ClientBusinessLogic() {
        clientDAO = new ClientDAO();
    }
    /**
     * Adds a client to the system.
     *
     * @param client The client to be added.
     */
    public void addClient(Client client){
        clientDAO.insert(client);
    }
    /**
     * Retrieves a JScrollPane containing a table representation of all clients.
     *
     * @return The JScrollPane containing the client table.
     */
    public JScrollPane table(){
        ClientDAO andrei = new ClientDAO();
        ArrayList<Client> balas = new ArrayList<>();
        balas = andrei.findAll();
        JTable tableclient = createJTable2(balas);
        JScrollPane jScrollPane=new JScrollPane(tableclient);
        return jScrollPane;
    }
    /**
     * Deletes a client with the specified ID.
     *
     * @param id The ID of the client to be deleted.
     */
    public void deleteClient(int id){
        clientDAO.delete(id);
    }
    /**
     * Finds a client by their ID.
     *
     * @param id The ID of the client to find.
     * @return The found client, or null if not found.
     */
   public Client findById(int id){
        clientDAO.findById(id);
        return  clientDAO.findById(id);
   }
}
