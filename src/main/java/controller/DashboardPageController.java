package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardPageController {

    @FXML private AnchorPane root;

    @FXML
    void btbonaddbook(ActionEvent event) throws IOException {
        // Button Click Event එක method එකට pass කරනවා
        setUi("/view/BookPage.fxml", event);
    }

    @FXML
    void btbonaddcustomer(ActionEvent event) throws IOException {
        setUi("/view/CustomerPage.fxml", event);
    }

    @FXML
    void btnonrent(ActionEvent event) throws IOException {
        setUi("/view/RentPage.fxml", event);
    }

    @FXML
    void btnonreturn(ActionEvent event) throws IOException {
        setUi("/view/ReturnPage.fxml", event);
    }

    @FXML
    void btnonreport(ActionEvent event) throws IOException {
        setUi("/view/ReportPage.fxml", event);
    }

    @FXML
    void btnOnLogout(ActionEvent event) throws IOException {
        setUi("/view/LoginPage.fxml", event);
    }

    // --- SINGLE WINDOW NAVIGATION METHOD ---
    private void setUi(String location, ActionEvent event) throws IOException {
// 1. ඔබපු Button එක හරහා දැනට තියෙන Stage (Window) එක ගන්නවා
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // 2. අලුත් FXML එක Load කරනවා
        Parent root = FXMLLoader.load(getClass().getResource(location));

        // 3. Stage එක වහන්නේ නැතුව, ඒකේ Scene එක මාරු කරනවා
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); // මැදට එන්න (කැමති නම් විතරක්)
        stage.show();
    }

    public void btnonsettings(ActionEvent actionEvent) {
    }
}