package com.ideaco.diacrudv1.service;


import com.ideaco.diacrudv1.dto.UserNameAndResumeDTO;
import com.ideaco.diacrudv1.model.UserModel;
import com.ideaco.diacrudv1.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public List<UserModel> getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean isUsernameTaken(String userName) {
        return !getByUserName(userName).isEmpty();
    }

    // bikin user tapi hanya bisa satu input

    public void saveUser(UserModel user) {
        userRepository.save(user);
    }

    // bikin user tapi bisa banyak input sekali post
    public List<UserModel> saveMultipleUser(List<UserModel> userModel) {
        return userRepository.saveAll(userModel);
    }

    // delete user
    public boolean deleteUser(int userId) {
        Optional<UserModel> data = userRepository.findByUserId(userId);
        if (data.isEmpty()) {
            return false;
        }
        userRepository.deleteById(userId);
        return true;
    }

    // edit with put method
    public UserModel putUserService(UserModel userModel) {
        int userId = userModel.getUserId();
        Optional<UserModel> data = userRepository.findByUserId(userId);
        if (data.isEmpty()) {
            return null;
        }
        return userRepository.save(userModel);
    }

    // edit user with patch method

    public UserModel patchUserService(int userId, String userResume) {
        Optional<UserModel> data = userRepository.findByUserId(userId);
        UserModel userModel = data.get();
        userModel.setUserResume(userResume);
        return userRepository.save(userModel);
    }

    // data transfer object, i will present for username and resume
    // bikin converter nya dulu, baru service
    // terus untuk ambil type data yang mau di present, bikin class, nama dan type file di dalam package DTO dan setter getternya
    private UserNameAndResumeDTO convertDTOUsernameAndResume(UserModel user){
        UserNameAndResumeDTO userNameAndResumeDTO = new UserNameAndResumeDTO();
        userNameAndResumeDTO.setUserName(user.getUserName());
        userNameAndResumeDTO.setUserResume(user.getUserResume());
        return userNameAndResumeDTO;
    }

    public UserNameAndResumeDTO dataUsernameResumeDTO(int userId) {
        Optional<UserModel> data = userRepository.findByUserId(userId);
        if(data.isEmpty()) {
            return null;
        }
        return convertDTOUsernameAndResume(data.get());
    }

}
