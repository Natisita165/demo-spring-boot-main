package bo.edu.ucb.chatbot.dao;


import bo.edu.ucb.chatbot.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class PaymentDao {
    private DataSource dataSource;
    
    @Autowired
    public PaymentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void postPayment(Payment payment) {
        String query = "INSERT INTO payment VALUES ("+
                "null,?,?,?,?,NOW(),NOW())";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println("ENTRE GG"+query);
            pstmt.setInt(1, payment.getCustomer_id());
            pstmt.setInt(2,payment.getStaff_id());
            pstmt.setInt(3, payment.getRental_id());
            pstmt.setDouble(4,payment.getAmount());


            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        //return inventory;
    }
}
