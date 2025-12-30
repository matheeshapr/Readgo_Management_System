package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentDTO;
import model.dto.ReturnDTO;
import repository.ReturnRepository;
import repository.impl.ReturnRepositoryImpl;
import service.ReturnService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnServiceImpl implements ReturnService {

    ReturnRepository returnRepository = new ReturnRepositoryImpl();

    @Override
    public String returnBook(String rentId, LocalDate returnDate, int lateDays, double fine) {

        boolean isSaved = returnRepository.saveReturn(rentId, returnDate, lateDays, fine);
        if (isSaved) {
            boolean isUpdated = returnRepository.updateRentStatus(rentId);
            if (isUpdated) {
                return "Success";
            }
        }
        return "Failed";
    }

    @Override
    public ObservableList<ReturnDTO> getAllReturns() {
        ObservableList<ReturnDTO> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = returnRepository.getAllReturns();
            while (rs.next()) {
                list.add(new ReturnDTO(
                        rs.getString("rent_id"),
                        rs.getString("book_id"),
                        rs.getDate("return_date").toLocalDate(),
                        rs.getInt("late_days"),
                        rs.getDouble("fine")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public RentDTO getRentDetails(String rentId) {
        try {
            ResultSet rs = returnRepository.getRentDetails(rentId);
            if (rs.next()) {
                return new RentDTO(
                        rs.getString("rent_id"),
                        rs.getString("book_id"),
                        rs.getString("cust_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long calculateLateDays(LocalDate dueDate, LocalDate returnDate) {
        long days = ChronoUnit.DAYS.between(dueDate, returnDate);
        return days > 0 ? days : 0;
    }

    @Override
    public double calculateFine(long lateDays) {
        return lateDays * 50.0;
    }
}