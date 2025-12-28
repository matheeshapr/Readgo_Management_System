package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.BookDTO;
import repository.BookRepository;
import repository.impl.BookRepositoryImpl;
import service.BookService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookServiceImpl implements BookService {

    BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public void addBook(String id, String title, String author, String category, double price, int qty, String status) {
        bookRepository.addBook(id, title, author, category, price, qty, status);
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteBook(id);
    }

    @Override
    public void updateBook(String id, String title, String author, String category, double price, int qty, String status) {
        bookRepository.updateBook(id, title, author, category, price, qty, status);
    }

    @Override
    public ObservableList<BookDTO> getAllBooks() {
        ObservableList<BookDTO> allBooks = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = bookRepository.getAllBooks();

            while (resultSet.next()) {
                allBooks.add(new BookDTO(
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("qty"),
                        resultSet.getString("status")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allBooks;
    }

    @Override
    public BookDTO searchBook(String id) {
        try {
            ResultSet resultSet = bookRepository.searchBook(id);
            if (resultSet.next()) {
                return new BookDTO(
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("qty"),
                        resultSet.getString("status")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}