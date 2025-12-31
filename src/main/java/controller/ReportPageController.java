package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.RentDTO;
import service.ReportService;
import service.impl.ReportServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportPageController implements Initializable {

    private ReportService reportService = new ReportServiceImpl();

    @FXML private Label lblTotalIncome;
    @FXML private Label lblTotalBooks;
    @FXML private Label lblTotalMembers;

    @FXML private TableView<RentDTO> tblOverdue;
    @FXML private TableColumn<?, ?> colRentId;
    @FXML private TableColumn<?, ?> colBookId;
    @FXML private TableColumn<?, ?> colCustId;
    @FXML private TableColumn<?, ?> colDueDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        loadDashboardData();
    }

    private void loadDashboardData() {
        lblTotalIncome.setText("Rs. " + reportService.getTotalIncome());
        lblTotalBooks.setText(String.valueOf(reportService.getBookCount()));
        lblTotalMembers.setText(String.valueOf(reportService.getMemberCount()));

        ObservableList<RentDTO> overdueList = reportService.getOverdueRents();
        tblOverdue.setItems(overdueList);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            // Single Window Logic එක මෙතනත් පාවිච්චි කරනවා
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardPage.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}