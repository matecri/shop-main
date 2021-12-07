package com.example.shopmain.security.service;

import com.example.shopmain.security.entity.User;
import com.example.shopmain.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    public Optional<User> getOne(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> findByMail(String mail){
        return userRepository.findBymail(mail);
    }
    public boolean existsByMail(String mail){
        return userRepository.existsBymail(mail);
    }
    public User findByDocument(String document){
        return userRepository.findBydocument(document).orElse(null);
    }
    public boolean existsByDocument(String document){
        return  userRepository.existsBydocument(document);
    }
    public void save(User user){
        userRepository.save(user);
    }
}
