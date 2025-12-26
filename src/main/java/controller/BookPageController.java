package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.impl.BookServiceimpl;

import java.io.IOException;

public class BookPageController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtQnty;

    private BookServiceimpl addBookService = new BookServiceimpl();

    @FXML
    void btnAddBook(ActionEvent event) {
        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String category = txtCategory.getText();
        int qty = Integer.parseInt(txtQnty.getText());

        addBookService.addBook(id, title, author, category, qty);

        new Alert(Alert.AlertType.INFORMATION, "Book Added Successfully!").show();
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtQnty.setText("");
    }


    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    public void btnClear(ActionEvent actionEvent) {
    }

    public void btnonback(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

}