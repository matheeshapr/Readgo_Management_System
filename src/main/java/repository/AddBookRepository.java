package repository;

import db.DBConnection;
import model.entity.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBookRepository {

    public boolean save(Books books) {
        try {
            // නිවැරදි කිරීම 1: Column නම් ටික හරියටම DB එකේ විදියට දැම්මා (qty_on_hand)
            // නිවැරදි කිරීම 2: Status අයින් කළා. දැන් තියෙන්නේ values 5යි.
            String sql = "INSERT INTO Book (book_id, title, author, category, qty_on_hand) VALUES (?,?,?,?,?)";

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, books.getId());
            pstm.setString(2, books.getTitle());
            pstm.setString(3, books.getAuthor());
            pstm.setString(4, books.getCategory());
            pstm.setInt(5, books.getQty());

            // 6 වෙනි පේළියක් දැන් ඕන නෑ.

            return pstm.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}