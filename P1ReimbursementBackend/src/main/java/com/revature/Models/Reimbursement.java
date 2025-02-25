package com.revature.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "reimbursements")
public class Reimbursement{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementId;

    private String username;

    private String request, notes;
    private String status = "Pending";
    private String amount;
    public Reimbursement() {
    }
    public Reimbursement(int reimbursementId, String username, String request, String notes, String status, String amount) {
        this.reimbursementId = reimbursementId;
        this.username = username;
        this.request = request;
        this.notes = notes;
        this.status = status;
        this.amount = amount;
    }
    public int getReimbursementId() {
        return reimbursementId;
    }
    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", username='" + username + '\'' +
                ", request='" + request + '\'' +
                ", notes='" + notes + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}