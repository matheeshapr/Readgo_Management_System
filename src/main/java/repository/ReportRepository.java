package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReportRepository {
    double getTotalFineCollected() throws SQLException;
    int getTotalBooks() throws SQLException;
    int getTotalMembers() throws SQLException;
    ResultSet getOverdueRents() throws SQLException;
}