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
import model.dto.BookDTO;
import service.BookService;
import service.impl.BookServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookPageController implements Initializable {

    private BookService bookService = new BookServiceImpl();
    private ObservableList<BookDTO> bookList = FXCollections.observableArrayList();

    @FXML private TableView<BookDTO> tblBooks;

    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colTitle;
    @FXML private TableColumn<?, ?> colAuthor;
    @FXML private TableColumn<?, ?> colCategory;
    @FXML private TableColumn<?, ?> colPrice;
    @FXML private TableColumn<?, ?> colQty;
    @FXML private TableColumn<?, ?> colStatus;

    @FXML private TextField txtId;
    @FXML private TextField txtTitle;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtCategory;
    @FXML private TextField txtPrice;
    @FXML private TextField txtQnty;

    @FXML private TextField txtStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadAllItems();

        tblBooks.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    @FXML
    void btnAddBook(ActionEvent event) {
        try {
            String id = txtId.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String category = txtCategory.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int qty = Integer.parseInt(txtQnty.getText());

            String status = txtStatus.getText();

            bookService.addBook(id, title, author, category, price, qty, status);

            new Alert(Alert.AlertType.INFORMATION, "Book Added Successfully!").show();
            clearFields();
            loadAllItems();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Input for Price or Qty").show();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        try {
            String id = txtId.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String category = txtCategory.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int qty = Integer.parseInt(txtQnty.getText());

            String status = txtStatus.getText();

            bookService.updateBook(id, title, author, category, price, qty, status);

            new Alert(Alert.AlertType.INFORMATION, "Book Updated Successfully!").show();
            clearFields();
            loadAllItems();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Input!").show();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Update Failed: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnDelete(ActionEvent event) {
        String id = txtId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a book first!").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            try {
                bookService.deleteBook(id);
                new Alert(Alert.AlertType.INFORMATION, "Book Deleted!").show();
                clearFields();
                loadAllItems();
            } catch (RuntimeException e) {
                new Alert(Alert.AlertType.ERROR, "Delete Failed: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnClear(ActionEvent event) {
        clearFields();
    }

    private void loadAllItems() {
        bookList.clear();
        bookList.addAll(bookService.getAllBooks());
        tblBooks.setItems(bookList);
    }

    private void setSelectedValue(BookDTO selectedValue) {
        if (selectedValue == null) {
            clearFields();
            return;
        }
        txtId.setText(selectedValue.getId());
        txtTitle.setText(selectedValue.getTitle());
        txtAuthor.setText(selectedValue.getAuthor());
        txtCategory.setText(selectedValue.getCategory());
        txtPrice.setText(String.valueOf(selectedValue.getPrice()));
        txtQnty.setText(String.valueOf(selectedValue.getQty()));

        txtStatus.setText(selectedValue.getStatus());
    }

    private void clearFields() {
        txtId.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtCategory.clear();
        txtPrice.clear();
        txtQnty.clear();

        txtStatus.clear();

        tblBooks.getSelectionModel().clearSelection();
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
}