package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.impl.CustomerServiceimpl;

public class CustomerPageController {

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private CustomerServiceimpl customerServiceimpl = new CustomerServiceimpl();

    @FXML
    void btnAddCustomer(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        customerServiceimpl.addCustomer(id, name, contact, email);

        new Alert(Alert.AlertType.INFORMATION, "Customer Added Successfully!").show();
        clearFields();

    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

}
