package service;

import model.dto.UserDTO;

public interface UserService {
    boolean registerUser(UserDTO userDTO);
}