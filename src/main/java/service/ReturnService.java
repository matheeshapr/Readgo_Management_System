package service;

import javafx.collections.ObservableList;
import model.dto.ReturnDTO;
import model.dto.RentDTO;
import java.time.LocalDate;

public interface ReturnService {
    String returnBook(String rentId, LocalDate returnDate, int lateDays, double fine);
    ObservableList<ReturnDTO> getAllReturns();
    RentDTO getRentDetails(String rentId);
    long calculateLateDays(LocalDate dueDate, LocalDate returnDate);
    double calculateFine(long lateDays);
}