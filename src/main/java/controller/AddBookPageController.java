package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.impl.AddBookServiceimpl;

public class AddBookPageController {

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

    private AddBookServiceimpl addBookService = new AddBookServiceimpl();

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
}