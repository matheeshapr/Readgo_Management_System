package repository.impl;

import db.DBConnection;
import repository.RentBookRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RentRepositoryImpl implements RentBookRepository {

    @Override
    public void addRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO rent VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, rentId);
            pstm.setObject(2, bookId);
            pstm.setObject(3, custId);
            pstm.setDate(4, Date.valueOf(issueDate)); // LocalDate -> SQL Date
            pstm.setDate(5, Date.valueOf(dueDate));   // LocalDate -> SQL Date
            pstm.setObject(6, status);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRent(String rentId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM rent WHERE rent_id = ?";
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, rentId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE rent SET book_id=?, cust_id=?, issue_date=?, due_date=?, status=? WHERE rent_id=?";

            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, bookId);
            pstm.setObject(2, custId);
            pstm.setDate(3, Date.valueOf(issueDate));
            pstm.setDate(4, Date.valueOf(dueDate));
            pstm.setObject(5, status);
            pstm.setObject(6, rentId);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllRents() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeQuery("SELECT * FROM rent");
    }

}