package bo.edu.ucb.chatbot.dao;


import bo.edu.ucb.chatbot.dto.Address;
import bo.edu.ucb.chatbot.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AddressDao {
    private DataSource dataSource;
    @Autowired
    public AddressDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public Address putAddress(Address address) {
        System.out.println("ENTRE GG 1");
        String query = "UPDATE address SET address = ?,address2=?, district=?, city_id=?," +
                "postal_code=?, phone=?, last_update=NOW()";

        System.out.println("ENTRE GG 2");
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println("ENTRE GG"+query);
            pstmt.setString(1, address.getAddress());
            pstmt.setString(2,address.getAddress2());
            pstmt.setString(3,address.getDisctrict());
            pstmt.setInt(4, address.getCity_id());
            pstmt.setString(5,address.getPostal_code());
            pstmt.setString(6,address.getPhone());


            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return address;
    }
}
