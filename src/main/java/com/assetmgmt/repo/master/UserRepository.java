package com.assetmgmt.repo.master;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assetmgmt.modal.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findBySerno(String serno);
    
    List<User> findBySernoAndPassword(String serno, String password);
    
    @Query("from User u where u.serno=?1")
    List<User> findUserBySerno(String serno);
    
    
   
}