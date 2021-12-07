package com.example.shopmain.security.repository;


import com.example.shopmain.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findBymail(String mail);
    boolean existsBymail(String mail);
    Optional<User> findBydocument(String document);
    boolean existsBydocument(String document);
}
