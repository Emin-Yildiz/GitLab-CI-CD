package com.example.pdf.controller;

import com.example.pdf.domain.User;
import com.example.pdf.service.FileServiceImpl;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/add")
public class FileController {

    private final FileServiceImpl fileServiceImpl;

    public FileController(FileServiceImpl fileServiceImpl) {
        this.fileServiceImpl = fileServiceImpl;
    }


    @PostMapping("/{startPage}/{endPage}")
    public void addPdfFile(@RequestParam MultipartFile file, @PathVariable int startPage, @PathVariable int endPage) throws IOException {
        fileServiceImpl.readPdfFile(file,startPage,endPage);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUserFromPdfFile(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileServiceImpl.addUserPdfFile(file), HttpStatus.CREATED);
    }

    @PostMapping("/tabula")
    public ResponseEntity<List<User>> addUserFromPdfTabula(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileServiceImpl.addUserFromPdfTabula(file),HttpStatus.CREATED);
    }

    @PostMapping("/image")
    public void imageToPdf(@RequestParam MultipartFile file) throws IOException {
        fileServiceImpl.imageToPdf(file);
    }

    @PostMapping()
    public void createPdfFile() throws IOException {
        fileServiceImpl.createPdfFile();
    }
}
