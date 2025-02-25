package com.revature.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Models.Reimbursement;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement,Integer>{

    List<Reimbursement> findByUsername(String username);
    List<Reimbursement> findByStatus(String status);
}
