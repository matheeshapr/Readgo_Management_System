package repository;

import model.dto.UserDTO;

public interface UserRepository {
    boolean saveUser(UserDTO user);
}