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


    public Rental postRental(Rental rental) {
        String query = "INSERT INTO rental VALUES ("+
                "null,?,?,?,?,?,NOW())";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            pstmt.setDate(1, rental.getRental_date());
            pstmt.setInt(2, rental.getInventory_id());
            pstmt.setInt(3,rental.getCustomer_id());
            pstmt.setDate(4, rental.getReturn_date());
            pstmt.setInt(5,rental.getStaff_id());


            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return rental;
    }
}
