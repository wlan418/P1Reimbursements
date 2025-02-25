package com.revature.Controllers;

import com.revature.Models.DTOs.LoginDTO;
import com.revature.Models.DTOs.UserDTO;
import com.revature.Models.User;
import com.revature.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        UserDTO returnedUser = authService.registerUser(user);
        return ResponseEntity.ok(returnedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        UserDTO loggedInUser = authService.login(loginDTO);

        return ResponseEntity.ok(loggedInUser);
    }
}
