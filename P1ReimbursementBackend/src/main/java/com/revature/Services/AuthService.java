package com.revature.Services;

import com.revature.DAOs.UserDAO;
import com.revature.Models.DTOs.LoginDTO;
import com.revature.Models.DTOs.UserDTO;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserDAO userDAO;

    @Autowired
    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public UserDTO registerUser(User user) {
        Optional<User> optional = userDAO.findByUsername(user.getUsername());
        if (optional.isPresent()){
            throw new IllegalArgumentException("Username already exists");
        }
        User registered = userDAO.save(user);

        return new UserDTO(registered);
    }
    public UserDTO login(LoginDTO loginDTO) {
        User loggedInUser = userDAO.findByUsernameAndPassword(
                loginDTO.getUsername(),
                loginDTO.getPassword()).orElse(null);
        if (loggedInUser==null){
            throw new IllegalArgumentException("Invalid username or password");
        }
        return new UserDTO(loggedInUser);
    }
}
