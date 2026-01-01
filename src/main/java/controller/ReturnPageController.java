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
import model.dto.ReturnDTO;
import service.ReturnService;
import service.impl.ReturnServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReturnPageController implements Initializable {

    private ReturnService returnService = new ReturnServiceImpl();
    private ObservableList<ReturnDTO> returnList = FXCollections.observableArrayList();

    @FXML private TextField txtRentId;
    @FXML private TextField txtBookId;
    @FXML private DatePicker dpReturnDate;
    @FXML private TextField txtLateDays;
    @FXML private TextField txtFine;

    @FXML private TableView<ReturnDTO> tblReturns;
    @FXML private TableColumn<?, ?> colRentId;
    @FXML private TableColumn<?, ?> colBookId;
    @FXML private TableColumn<?, ?> colReturnDate;
    @FXML private TableColumn<?, ?> colLateDay;
    @FXML private TableColumn<?, ?> colFine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. Columns Map කිරීම
        colRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colLateDay.setCellValueFactory(new PropertyValueFactory<>("lateDays"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("fine"));

        // 2. අද දිනය Default පෙන්වීම
        dpReturnDate.setValue(LocalDate.now());

        // 3. Table එක Load කිරීම
        loadTable();

        // 4. Return Date එක වෙනස් කරනකොට Fine එක Update වෙන්න Listener එකක්
        dpReturnDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !txtRentId.getText().isEmpty()) {
                calculateFineLogic(); // Date එක වෙනස් කරාම ආයේ ගණනය කරනවා
            }
        });

        // 5. Rent ID එක Type කරලා Enter ගැහුවම වැඩ කරන්න (FXML එකේ onAction නැත්නම් මේක ඕනි)
        txtRentId.setOnAction(event -> searchRent());
    }

    // Rent ID එක ගහලා Enter කරාම මේක වැඩ කරනවා
    private void searchRent() {
        String rentId = txtRentId.getText();
        if (rentId.isEmpty()) return;

        RentDTO rent = returnService.getRentDetails(rentId);

        if (rent != null) {
            txtBookId.setText(rent.getBookId());
            calculateFineLogic(rent); // Fine එක හදනවා
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Rent ID or Book already returned!").show();
            clearFields();
        }
    }

    // Fine ගණනය කරන Logic එක (Overloaded Methods)
    private void calculateFineLogic() {
        String rentId = txtRentId.getText();
        if (!rentId.isEmpty()) {
            RentDTO rent = returnService.getRentDetails(rentId);
            if(rent != null) calculateFineLogic(rent);
        }
    }

    private void calculateFineLogic(RentDTO rent) {
        LocalDate dueDate = rent.getDueDate();
        LocalDate returnDate = dpReturnDate.getValue();

        long lateDays = returnService.calculateLateDays(dueDate, returnDate);
        double fine = returnService.calculateFine(lateDays);

        txtLateDays.setText(String.valueOf(lateDays));
        txtFine.setText(String.valueOf(fine));
    }

    @FXML
    void btnReturnBook(ActionEvent event) {
        try {
            String rentId = txtRentId.getText();

            if (rentId.isEmpty() || txtBookId.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please Search Valid Rent ID first!").show();
                return;
            }

            LocalDate returnDate = dpReturnDate.getValue();
            int lateDays = Integer.parseInt(txtLateDays.getText());
            double fine = Double.parseDouble(txtFine.getText());

            String result = returnService.returnBook(rentId, returnDate, lateDays, fine);

            if (result.equals("Success")) {
                new Alert(Alert.AlertType.INFORMATION, "Book Returned Successfully!").show();
                loadTable();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Return Failed!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
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

    private void loadTable() {
        returnList.clear();
        returnList.addAll(returnService.getAllReturns());
        tblReturns.setItems(returnList);
    }

    private void clearFields() {
        txtRentId.clear();
        txtBookId.clear();
        txtLateDays.setText("0");
        txtFine.setText("0.00");
        dpReturnDate.setValue(LocalDate.now());
    }

    public void btnClearCustomer(ActionEvent actionEvent) {
        clearFields();
    }
}