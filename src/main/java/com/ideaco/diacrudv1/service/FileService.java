package com.ideaco.diacrudv1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    // direktori untuk file uploadnya
    private final Path root = Paths.get("uploads/service");
    public boolean saveFile(MultipartFile file) {
        try {
            if(!Files.exists(root)) {
                Files.createDirectories(root);
            }
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
