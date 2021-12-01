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

    public Address postAddress(Address address) {
        System.out.println("ENTRE GG 1");
       // String query = "UPDATE address SET address = ?,address2=?, district=?, city_id=?," +
         //       "postal_code=?, phone=?, last_update=NOW()";
        String query = "INSERT INTO address VALUES (null,?,?,?,?,?,?,NOW());";

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
    public int putAddress(Integer address) {
        int addr = -1;
        Integer id = get(Address.getCity());

        String query2 = "UPDATE address " +
                "   SET address = ? , " +
                "   address2 = ? , " +
                "   district = ? , " +
                "   city_id = ? , " +
                "   postal_code = ?, " +
                "   phone = ? " +
                "   WHERE address_id LIKE ( ? ) ";
        try(
                Connection conn = dataSource.getConnection();
                var pstmt =  conn.prepareStatement(query2);
        ){
            pstmt.setString(1, address.getAddress());
            pstmt.setString(2, address.getAddress2());
            pstmt.setString(3, address.getDistrict());
            pstmt.setInt(4, cId);
            pstmt.setInt(5, address.getPostal_code());
            pstmt.setLong(6, address.getPhone());
            pstmt.setInt(7, address.getAddress_id());
            addr = pstmt.executeUpdate();
            System.out.println("repuesta: "+addr);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return addr;
    }
}
