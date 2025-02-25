package com.revature.Controllers;

import com.revature.Models.DTOs.LoginDTO;
import com.revature.Models.DTOs.UserDTO;
import com.revature.Models.User;
import com.revature.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getall")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        UserDTO userDTO = userService.deleteUser(user);
        if (userDTO==null){
            return ResponseEntity.status(401).body("Cannot find user "+user.getUsername());
        } else {
            return ResponseEntity.ok().body(userDTO.getUsername()+" deleted");
        }
    }
    @PostMapping("/finduser")
    public ResponseEntity<User> findUser(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(userService.findByUsername(loginDTO));
    }
}
