package bo.edu.ucb.chatbot.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransaccionDao {
    private DataSource dataSource;

    @Autowired
    public TransaccionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getLastInsertId(){
        Integer insert=-1;
        String query = "SELECT LAST_INSERT_ID()";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ) {

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            insert= rs.getInt("LAST_INSERT_ID()");


            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return insert;
    }

}
