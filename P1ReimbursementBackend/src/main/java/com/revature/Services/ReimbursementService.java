package com.revature.Services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.Models.Reimbursement;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public List<Reimbursement> getAllReimbursements(){
        return reimbursementDAO.findAll();
    }

    public List<Reimbursement> getAllPendingReimbursements(){
        List<Reimbursement> reimbursements = getAllReimbursements();
        List<Reimbursement> r = new ArrayList<>();
        for (Reimbursement reimbursement : reimbursements) {
            if (reimbursement.getStatus().equals("Pending"))
                r.add(reimbursement);
        }
        return r;
    }

    public List<Reimbursement> getAllReimbursementsByUser(User user){
        List<Reimbursement> reimbursements = getAllReimbursements();
        List<Reimbursement> r = new ArrayList<>();
        System.out.println(user);
        for (Reimbursement reimbursement : reimbursements) {
            System.out.println(reimbursement);
            if (reimbursement.getUsername().equals(user.getUsername()))
                r.add(reimbursement);
        }
        return r;
    }

    public List<Reimbursement> getAllPendingReimbursementsByUser(User user){
        List<Reimbursement> reimbursements = getAllReimbursementsByUser(user);
        List<Reimbursement> pending = new ArrayList<>();
        for (Reimbursement reimbursement : reimbursements) {
            System.out.println(reimbursement.getStatus());
            if (reimbursement.getStatus().equals("Pending")) {
                pending.add(reimbursement);
            }
        }
        return pending;
    }

    public Reimbursement addReimbursement(Reimbursement r){
        System.out.println(r);
        return reimbursementDAO.save(r);
    }
    public void deleteReimbursement(Reimbursement r){
        reimbursementDAO.delete(r);
    }
    public Reimbursement approveReimbursement(Reimbursement r){
        r.setStatus("Approved");
        return reimbursementDAO.save(r);
    }
    public Reimbursement denyReimbursement(Reimbursement r){
        r.setStatus("Denied");
        return reimbursementDAO.save(r);
    }
    public void deleteAllByUser(String username){
        List<Reimbursement> list = reimbursementDAO.findAll();
        for (Reimbursement r : list) {
            if (r.getUsername().equals(username)) {
                reimbursementDAO.delete(r);
            }
        }
    }
}
