package service.impl;

import model.dto.UserDTO;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean registerUser(UserDTO userDTO) {
        return userRepository.saveUser(userDTO);
    }
}