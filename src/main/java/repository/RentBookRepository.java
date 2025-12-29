package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface RentBookRepository {
    void addRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status);
    void deleteRent(String rentId);
    void updateRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status);
    ResultSet getAllRents() throws SQLException;
}