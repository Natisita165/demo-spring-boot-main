package bo.edu.ucb.chatbot.dao;


import bo.edu.ucb.chatbot.dto.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class RentalDao {
    private DataSource dataSource;

    @Autowired
    public RentalDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void postRental(Rental rental) {
        String query = "INSERT INTO rental VALUES ("+
                "null,NOW(),?,?,?,?,NOW())";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println("ENTRE GG"+query);
            pstmt.setInt(1, rental.getInventory_id());
            pstmt.setInt(2,rental.getCustomer_id());
            pstmt.setObject(3, rental.getReturn_date());
            pstmt.setInt(4,rental.getStaff_id());


            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        //return inventory;
    }
}
