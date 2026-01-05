package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField txtpWord;

    @FXML
    private TextField txtuName;

    @FXML
    void onbtnlogin(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if(txtuName.getText().equals("admin") && txtpWord.getText().equals("admin")) {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardPage.fxml"))));
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
                return;
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSignupOnAction(ActionEvent event) {
        try {
            // Single Window Logic එක මෙතනත් පාවිච්චි කරනවා
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignUpPage.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
