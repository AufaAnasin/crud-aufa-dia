package com.ideaco.diacrudv1.service;


import com.ideaco.diacrudv1.model.UserModel;
import com.ideaco.diacrudv1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // nandakan kalau ini class/package service
public class UserService {
    @Autowired // kalau ada kelas lain seperti dibawah, pakai ini
    private UserRepository userRepository;

    public List<UserModel> getAllUserData(){
        List<UserModel> data = userRepository.findAllUserData();
        return data;
    }

    public Optional<UserModel> getUserById(int userId) {
        Optional<UserModel> data = userRepository.findByUserId(userId);
        return data;
    }

}
