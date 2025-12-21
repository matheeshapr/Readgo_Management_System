package repository.impl;

import db.DBConnection;
import repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRepositoryimpl implements BookRepository {

    @Override
    public void addBook(String id, String title, String author, String category, int qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "INSERT INTO Book VALUES(?, ?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, id);
            pstm.setObject(2, title);
            pstm.setObject(3, author);
            pstm.setObject(4, category);
            pstm.setObject(5, qty);

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
