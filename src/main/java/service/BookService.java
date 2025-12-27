package service;

import javafx.collections.ObservableList;
import model.dto.BookDTO;

public interface BookService {

    void addBook(String id, String title, String author, String category, double price, int qty, String status);

    void deleteBook(String id);

    void updateBook(String id, String title, String author, String category, double price, int qty, String status);

    ObservableList<BookDTO> getAllBooks();

    BookDTO searchBook(String id);

}