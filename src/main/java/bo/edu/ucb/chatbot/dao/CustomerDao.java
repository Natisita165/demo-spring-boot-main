package bo.edu.ucb.chatbot.dao;


import bo.edu.ucb.chatbot.dto.Customer;
import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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

        System.out.println("ENTRE GG 1");
        String query = "INSERT INTO customer VALUES ("+
                "null,?,?,?,?,?,?,NOW(),NOW())";

        System.out.println("ENTRE GG 2");
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {
            System.out.println("ENTRE GG"+query);
            pstmt.setInt(1, customer.getStore_id());
            pstmt.setString(2,customer.getFirst_name());
            pstmt.setString(3,customer.getLast_name());
            pstmt.setString(4, customer.getEmail());
            pstmt.setInt(5,customer.getAddress_id());
            pstmt.setInt(6,customer.getActive());


            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return customer;
    }
//no finalizado la actualización de address


        public String getEmailCustomer(String nombre, String appel) {
            String email="";
            String query = "SELECT email " +
                    " FROM customer " +
                    " WHERE " +
                    "   (UPPER(first_name) LIKE ( ? ) or"+
                    "   UPPER(last_name) LIKE ( ? ) )" ;

            try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement pstmt =  conn.prepareStatement(query);
            ) {
                System.out.println(query);
                //pstmt.setString(0, "%"+nombre[0].toUpperCase()+ "%");
                pstmt.setString(1, "%"+nombre+ "%");
                pstmt.setString(2, "%"+appel+ "%");
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                email= rs.getString("email");


                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // TODO gestionar correctamente la excepción
            }
            return email;
        }
    }

