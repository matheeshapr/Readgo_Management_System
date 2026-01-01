package service;

import javafx.collections.ObservableList;
import model.dto.RentDTO;

public interface ReportService {
    double getTotalIncome();
    int getBookCount();
    int getMemberCount();
    ObservableList<RentDTO> getOverdueRents();
}