package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.RentDTO;
import service.RentBookService;
import service.impl.RentServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class RentPageController implements Initializable {

    private RentBookService rentService = new RentServiceImpl();
    private ObservableList<RentDTO> rentList = FXCollections.observableArrayList();

    @FXML private TableView<RentDTO> tblRent;
    @FXML private TableColumn<?, ?> colRentId;
    @FXML private TableColumn<?, ?> colCustId;
    @FXML private TableColumn<?, ?> colBookId;
    @FXML private TableColumn<?, ?> colIssueDate;
    @FXML private TableColumn<?, ?> colDueDate;
    @FXML private TableColumn<?, ?> colStatus;

    @FXML private TextField txtRentId;
    @FXML private TextField txtCustId;
    @FXML private TextField txtBookId;
    @FXML private DatePicker dpIssueDate;
    @FXML private DatePicker dpDueDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. Column Mapping (DTO එකේ field names වලට සමාන විය යුතුයි)
        colRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 2. Load Data
        loadAllRents();

        // 3. Selection Listener
        tblRent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {
        try {
            String rentId = txtRentId.getText();
            String custId = txtCustId.getText();
            String bookId = txtBookId.getText();
            LocalDate issueDate = dpIssueDate.getValue();
            LocalDate dueDate = dpDueDate.getValue();
            String status = "Pending"; // අලුතෙන් issue කරන නිසා Default Pending

            if (issueDate == null || dueDate == null) {
                new Alert(Alert.AlertType.WARNING, "Please select dates!").show();
                return;
            }

            rentService.placeRent(rentId, bookId, custId, issueDate, dueDate, status);

            new Alert(Alert.AlertType.INFORMATION, "Book Issued Successfully!").show();
            clearFields();
            loadAllRents();

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    // Back Button Logic
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

    private void loadAllRents() {
        rentList.clear();
        rentList.addAll(rentService.getAllRents());
        tblRent.setItems(rentList);
    }

    private void setSelectedValue(RentDTO dto) {
        if (dto == null) {
            clearFields();
            return;
        }
        txtRentId.setText(dto.getRentId());
        txtCustId.setText(dto.getCustId());
        txtBookId.setText(dto.getBookId());
        dpIssueDate.setValue(dto.getIssueDate());
        dpDueDate.setValue(dto.getDueDate());
    }

    private void clearFields() {

        txtRentId.clear();
        txtCustId.clear();
        txtBookId.clear();
        dpIssueDate.setValue(null);
        dpDueDate.setValue(null);
        tblRent.getSelectionModel().clearSelection();
    }

    public void btnClearCustomer(ActionEvent actionEvent) {
        clearFields();
    }
}