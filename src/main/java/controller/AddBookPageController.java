package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.entity.Books;
import service.AddBookService;

public class AddBookPageController {

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQnty;

    @FXML
    private TextField txtTitle;

    private AddBookService addBookService = new AddBookService();

    @FXML
    void btnAddBook(ActionEvent event) {

        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String category = txtCategory.getText();
        int qty = Integer.parseInt(txtQnty.getText());

        Books books = new Books(id,title,author,category,qty);

        String isSaved = addBookService.saveBook(books);

    }
}
