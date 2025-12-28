package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDTO;
import service.CustomerService;
import service.impl.CustomerServiceImpl;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerPageController implements Initializable {

    private CustomerService customerService = new CustomerServiceImpl();
    private ObservableList<CustomerDTO> customerList = FXCollections.observableArrayList();

    @FXML private TableView<CustomerDTO> tblCustomers;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colName;
    @FXML private TableColumn<?, ?> colContact;
    @FXML private TableColumn<?, ?> colEmail;

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtContact;
    @FXML private TextField txtEmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadAllItems();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    @FXML
    void btnAddCustomer(ActionEvent event) {
        try {
            customerService.addCustomer(
                    txtId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtEmail.getText()
            );
            new Alert(Alert.AlertType.INFORMATION, "Customer Added!").show();
            clearFields();
            loadAllItems();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateCustomer(ActionEvent event) {
        try {
            customerService.updateCustomer(
                    txtId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtEmail.getText()
            );
            new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
            clearFields();
            loadAllItems();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteCustomer(ActionEvent event) {
        String id = txtId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Select a customer first!").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                customerService.deleteCustomer(id);
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                clearFields();
                loadAllItems();
            } catch (RuntimeException e) {
                new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnClearCustomer(ActionEvent event) {
        clearFields();
    }

    private void loadAllItems() {
        customerList.clear();
        customerList.addAll(customerService.getAllCustomers());
        tblCustomers.setItems(customerList);
    }

    private void setSelectedValue(CustomerDTO dto) {
        if (dto == null) {
            clearFields();
            return;
        }
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtContact.setText(dto.getContact());
        txtEmail.setText(dto.getEmail());
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtEmail.clear();
        tblCustomers.getSelectionModel().clearSelection();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

}