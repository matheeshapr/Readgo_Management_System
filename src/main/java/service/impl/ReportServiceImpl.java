package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentDTO;
import repository.ReportRepository;
import repository.impl.ReportRepositoryImpl;
import service.ReportService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportServiceImpl implements ReportService {

    ReportRepository reportRepository = new ReportRepositoryImpl();

    @Override
    public double getTotalIncome() {
        try {
            return reportRepository.getTotalFineCollected();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    @Override
    public int getBookCount() {
        try {
            return reportRepository.getTotalBooks();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getMemberCount() {
        try {
            return reportRepository.getTotalMembers();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ObservableList<RentDTO> getOverdueRents() {
        ObservableList<RentDTO> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = reportRepository.getOverdueRents();
            while (rs.next()) {
                list.add(new RentDTO(
                        rs.getString("rent_id"),
                        rs.getString("book_id"),
                        rs.getString("cust_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}