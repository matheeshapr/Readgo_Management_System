package repository.impl;

import db.DBConnection;
import repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public void addBook(String id, String title, String author, String category, double price, int qty, String status) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, id);
            pstm.setObject(2, title);
            pstm.setObject(3, author);
            pstm.setObject(4, category);
            pstm.setObject(5, price);
            pstm.setObject(6, qty);
            pstm.setObject(7, status);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM book WHERE id = ?";

            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, id);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(String id, String title, String author, String category, double price, int qty, String status) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "UPDATE book SET title=?, author=?, category=?, price=?, qty=?, status=? WHERE id=?";

            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, title);
            pstm.setObject(2, author);
            pstm.setObject(3, category);
            pstm.setObject(4, price);
            pstm.setObject(5, qty);
            pstm.setObject(6, status);
            pstm.setObject(7, id);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllBooks() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM book";
        PreparedStatement pstm = connection.prepareStatement(SQL);
        return pstm.executeQuery();
    }

    @Override
    public ResultSet searchBook(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM book WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1, id);
        return pstm.executeQuery();
    }
}