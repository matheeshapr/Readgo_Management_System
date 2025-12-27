package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookRepository {

    void addBook(String id, String title, String author, String category, double price, int qty, String status);

    void deleteBook(String id);

    void updateBook(String id, String title, String author, String category, double price, int qty, String status);

    ResultSet getAllBooks() throws SQLException;

    ResultSet searchBook(String id) throws SQLException;
}