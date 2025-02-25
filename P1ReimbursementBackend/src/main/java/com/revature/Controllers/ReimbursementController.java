package com.revature.Controllers;

import com.revature.Models.Reimbursement;
import com.revature.Models.User;
import com.revature.Services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin()
public class ReimbursementController {
    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reimbursement>> getAllReimbursements(){
        return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }
    @GetMapping("/pending")
    public ResponseEntity<List<Reimbursement>> getAllPendingReimbursements(){
        return ResponseEntity.ok(reimbursementService.getAllPendingReimbursements());
    }
    @PostMapping("/add")
    public ResponseEntity<Reimbursement> addReimbursement(@RequestBody Reimbursement r){
        System.out.println(r);
        return ResponseEntity.ok(reimbursementService.addReimbursement(r));
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteReimbursement(@RequestBody Reimbursement r){
        reimbursementService.deleteReimbursement(r);
        return ResponseEntity.ok("Reimbursement deleted");
    }
    @PostMapping("/allbyuser")
    public ResponseEntity<List<Reimbursement>> getAllReimbursementsByUser(@RequestBody User user){
        return ResponseEntity.ok(reimbursementService.getAllReimbursementsByUser(user));
    }
    @PostMapping("/allpendingbyuser")
    public ResponseEntity<List<Reimbursement>> getAllPendingReimbursementsByUser(@RequestBody User user){
        return ResponseEntity.ok(reimbursementService.getAllPendingReimbursementsByUser(user));
    }
    @PatchMapping("/approve")
    public ResponseEntity<Reimbursement> approveReimbursement(@RequestBody Reimbursement reimbursement){
        return ResponseEntity.ok(reimbursementService.approveReimbursement(reimbursement));
    }
    @PatchMapping("/deny")
    public ResponseEntity<Reimbursement> denyReimbursement(@RequestBody Reimbursement reimbursement){
        return ResponseEntity.ok(reimbursementService.denyReimbursement(reimbursement));
    }
    @PostMapping("/deletebyuser")
    public ResponseEntity<String> deleteAllByUser(@RequestBody String username){
        reimbursementService.deleteAllByUser(username);
        return ResponseEntity.ok("All reimbursements by " + username + " deleted");
    }
}
