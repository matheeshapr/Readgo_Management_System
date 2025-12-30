package repository.impl;

import db.DBConnection;
import repository.ReturnRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReturnRepositoryImpl implements ReturnRepository {

    @Override
    public boolean saveReturn(String rentId, LocalDate returnDate, int lateDays, double fine) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String returnId = "RET-" + System.currentTimeMillis();

            String sql = "INSERT INTO return_record (return_id, rent_id, return_date, late_days, fine) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, returnId);
            pstm.setObject(2, rentId);
            pstm.setDate(3, Date.valueOf(returnDate));
            pstm.setInt(4, lateDays);
            pstm.setDouble(5, fine);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRentStatus(String rentId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "UPDATE rent SET status = 'Returned' WHERE rent_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, rentId);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultSet getRentDetails(String rentId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM rent WHERE rent_id = ? AND status = 'Pending'";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, rentId);
        return pstm.executeQuery();
    }

    @Override
    public ResultSet getAllReturns() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT rr.rent_id, r.book_id, rr.return_date, rr.late_days, rr.fine " +
                "FROM return_record rr " +
                "JOIN rent r ON rr.rent_id = r.rent_id";
        return connection.createStatement().executeQuery(sql);
    }
}