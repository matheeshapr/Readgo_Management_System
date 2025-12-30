package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dto.UserDTO;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;

public class SignupPageController {

    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> cmbRole;

    private UserService userService = new UserServiceImpl();

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String userId = txtUserId.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        String role = cmbRole.getValue();

        if (role == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a Role!").show();
            return;
        }

        UserDTO userDTO = new UserDTO(userId, username, password, role);
        boolean isRegistered = userService.registerUser(userDTO);

        if (isRegistered) {
            new Alert(Alert.AlertType.INFORMATION, "Registration Successful! Please Login.").show();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Registration Failed! User ID might already exist.").show();
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event){
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    private void clearFields() {
        txtUserId.clear();
        txtUsername.clear();
        txtPassword.clear();
        cmbRole.getSelectionModel().clearSelection();
    }
}