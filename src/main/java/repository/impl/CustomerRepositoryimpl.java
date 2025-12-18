package repository.impl;

import db.DBConnection;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepositoryimpl implements CustomerRepository {

    @Override
    public void addCustomer(String id, String name, String contact, String email) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "INSERT INTO Customer VALUES(?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, contact);
            pstm.setObject(4, email);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
