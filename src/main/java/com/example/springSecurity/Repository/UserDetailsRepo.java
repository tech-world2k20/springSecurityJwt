package com.example.springSecurity.Repository;

import com.example.springSecurity.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<UserInfo, Integer> {
     Optional<UserInfo> findByName(String username);
}
