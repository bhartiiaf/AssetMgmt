package com.unifiedweb360.userrepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unifiedweb360.usermodal.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    List<User> findByUsernameAndPassword(String username, String password);
    
    @Query("from User u where u.username=?1")
    List<User> findUserByUsername(String username);
   
}