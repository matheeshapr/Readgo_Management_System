package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface ReturnRepository {
    boolean saveReturn(String rentId, LocalDate returnDate, int lateDays, double fine);
    boolean updateRentStatus(String rentId);
    ResultSet getRentDetails(String rentId) throws SQLException;
    ResultSet getAllReturns() throws SQLException;
}