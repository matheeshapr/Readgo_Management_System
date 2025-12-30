package repository.impl;

import db.DBConnection;
import model.dto.UserDTO;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean saveUser(UserDTO user) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            // Query එක (Tables හදනකොට දුන්න පිළිවෙලට: id, username, password, role)
            String sql = "INSERT INTO user VALUES(?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, user.getUserId());
            pstm.setString(2, user.getUsername());
            pstm.setString(3, user.getPassword());
            pstm.setString(4, user.getRole());

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}