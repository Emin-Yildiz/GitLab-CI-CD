package com.example.pdf.service;

import com.example.pdf.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    public void readPdfFile(MultipartFile file, int startPage, int endPage) throws IOException;

    public User addUserPdfFile(MultipartFile file) throws IOException;

    public void createPdfFile() throws IOException;

    public List<User> addUserFromPdfTabula(MultipartFile file) throws IOException;

    void imageToPdf(MultipartFile file) throws IOException;
}
