package repository.impl;

import db.DBConnection;
import model.dto.BookDTO;
import repository.AddBookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBookRepositoryimpl implements AddBookRepository {

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
