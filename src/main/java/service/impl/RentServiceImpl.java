package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentDTO;

import repository.RentBookRepository;
import repository.impl.RentRepositoryImpl;
import service.RentBookService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RentServiceImpl implements RentBookService {

    RentBookRepository rentRepository = new RentRepositoryImpl();


    @Override
    public void placeRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status) {
        // මෙතනදී Book එකේ Qty අඩු කරන logic එකත් අනාගතයේදී දාන්න පුළුවන්
        rentRepository.addRent(rentId, bookId, custId, issueDate, dueDate, status);
    }

    @Override
    public void deleteRent(String rentId) {
        rentRepository.deleteRent(rentId);
    }

    @Override
    public void updateRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status) {
        rentRepository.updateRent(rentId, bookId, custId, issueDate, dueDate, status);
    }

    @Override
    public ObservableList<RentDTO> getAllRents() {
        ObservableList<RentDTO> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = rentRepository.getAllRents();
            while (rs.next()) {
                list.add(new RentDTO(
                        rs.getString("rent_id"),
                        rs.getString("book_id"),
                        rs.getString("cust_id"),
                        rs.getDate("issue_date").toLocalDate(), // SQL Date -> LocalDate
                        rs.getDate("due_date").toLocalDate(),   // SQL Date -> LocalDate
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}