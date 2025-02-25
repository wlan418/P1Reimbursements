package com.revature.Services;

import com.revature.DAOs.UserDAO;
import com.revature.Models.DTOs.LoginDTO;
import com.revature.Models.DTOs.UserDTO;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public List<UserDTO> getAllUsers(){
        List<User> users = userDAO.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user:users) {
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }
    public UserDTO deleteUser(User user){
        Optional<User> u = userDAO.findByUsername(user.getUsername());
        if (u.isPresent()) {
            User user2 = u.get();
            userDAO.delete(user2);
            return new UserDTO(user2);
        } else {
            return null;
        }
    }
    public User findByUsername(LoginDTO loginDTO) {
        Optional<User> u = userDAO.findByUsername(loginDTO.getUsername());
        return u.orElse(null);
    }
}
