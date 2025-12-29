package service;

import javafx.collections.ObservableList;
import model.dto.RentDTO;
import java.time.LocalDate;

public interface RentBookService {
    void placeRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status);
    void deleteRent(String rentId);
    void updateRent(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status);
    ObservableList<RentDTO> getAllRents();
}