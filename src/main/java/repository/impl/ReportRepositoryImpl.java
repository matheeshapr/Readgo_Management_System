package repository.impl;

import db.DBConnection;
import repository.ReportRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportRepositoryImpl implements ReportRepository {

    @Override
    public double getTotalFineCollected() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rs = connection.createStatement().executeQuery("SELECT SUM(fine) FROM return_record");
        if (rs.next()) {
            return rs.getDouble(1);
        }
        return 0.0;
    }

    @Override
    public int getTotalBooks() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(id) FROM book");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public int getTotalMembers() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(id) FROM customer");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public ResultSet getOverdueRents() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM rent WHERE due_date < CURDATE() AND status = 'Pending'";
        return connection.createStatement().executeQuery(sql);
    }
}