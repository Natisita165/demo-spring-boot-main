package bo.edu.ucb.chatbot.dao;


import bo.edu.ucb.chatbot.dto.Customer;
import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@Component
public class CustomerDao {
    private DataSource dataSource;

    @Autowired
    public CustomerDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Customer postCustomer(Customer customer) {
        List<Film> result = new ArrayList<>();
        String query = "INSERT INTO customer VALUES ("+
                "null,?,?,?,?,?,?,NOW(),NOW())";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println(query);
            pstmt.setInt(1, customer.getStore_id());
            pstmt.setString(2,customer.getFirst_name());
            pstmt.setString(3,customer.getLast_name());
            pstmt.setString(4, customer.getEmail());
            pstmt.setInt(5,customer.getAddress_id());
            pstmt.setBoolean(6,customer.getActive());


            ResultSet rs = pstmt.executeQuery();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return customer;
    }
}
