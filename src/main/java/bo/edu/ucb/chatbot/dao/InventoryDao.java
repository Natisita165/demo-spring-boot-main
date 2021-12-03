package bo.edu.ucb.chatbot.dao;


import bo.edu.ucb.chatbot.dto.Customer;
import bo.edu.ucb.chatbot.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class InventoryDao {
    private DataSource dataSource;

    @Autowired
    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void postInventory(Inventory inventory) {

            String query = "INSERT INTO inventory VALUES ("+
                    "null,?,?,NOW())";

            try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement pstmt =  conn.prepareStatement(query);
            ) {
                System.out.println("ENTRE GG"+query);
                pstmt.setInt(1, inventory.getFilm_id());
                pstmt.setInt(2,inventory.getStore_id());


                pstmt.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
                // TODO gestionar correctamente la excepción
            }
            //return inventory;

    }

    public Integer getInventoryIdFilm(Integer idFilm) {
        Integer insert=-1;
        String query = "SELECT a.inventory_id FROM inventory a, film f WHERE a.film_id = f.film_id AND f.film_id = ?";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            pstmt.setInt(1,idFilm);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            insert= rs.getInt("inventory_id");



            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return insert;
    }
}
