package com.revature.Models.DTOs;

import com.revature.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class UserDTO {
    @Id
    private int userId;
    private String fName,lName,email,username;
    private String role;

    public UserDTO() {
    }

    public UserDTO(int userId, String fName, String lName, String email, String username, String role) {
        this.userId=userId;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.role = role;
    }
    public UserDTO(User u){
        this.userId=u.getUserId();
        this.fName=u.getfName();
        this.lName=u.getlName();
        this.email=u.getEmail();
        this.username=u.getUsername();
        this.role=u.getRole();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
