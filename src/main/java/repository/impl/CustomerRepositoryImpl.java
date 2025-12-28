package repository.impl;

import db.DBConnection;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void addCustomer(String id, String name, String contact, String email) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO customer VALUES(?, ?, ?, ?)";
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

    @Override
    public void updateCustomer(String id, String name, String contact, String email) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE customer SET name=?, contact=?, email=? WHERE id=?";
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, name);
            pstm.setObject(2, contact);
            pstm.setObject(3, email);
            pstm.setObject(4, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM customer WHERE id=?";
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllCustomers() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeQuery("SELECT * FROM customer");
    }

    @Override
    public ResultSet searchCustomer(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
        pstm.setObject(1, id);
        return pstm.executeQuery();
    }
}