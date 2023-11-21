package com.example.pdf.service;

import com.example.pdf.domain.User;
import com.example.pdf.repositories.DbRepository;
import com.example.pdf.util.ImageUtil;
import com.example.pdf.util.ReadPdf;
import com.example.pdf.util.TabulaUtil;
import com.example.pdf.util.WritePdf;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final TabulaUtil tabulaUtil;
    private final ReadPdf readPdf;
    private final WritePdf writePdf;
    private final ObjectMapper objectMapper;
    private final DbRepository dbRepository;
    private final ImageUtil imageUtil;

    public FileServiceImpl(TabulaUtil tabulaUtil, ReadPdf readPdf, WritePdf writePdf, ObjectMapper objectMapper, DbRepository dbRepository, ImageUtil imageUtil) {
        this.tabulaUtil = tabulaUtil;
        this.readPdf = readPdf;
        this.writePdf = writePdf;
        this.objectMapper = objectMapper;
        this.dbRepository = dbRepository;
        this.imageUtil = imageUtil;
    }

    @Override
    public User addUserPdfFile(MultipartFile file) throws IOException {
        String userJson = readPdf.setDocumentUser(file);
        User user = objectMapper.readValue(userJson,User.class);
        return dbRepository.save(user);
    }

    @Override
    public void readPdfFile(MultipartFile file, int startPage, int endPage) throws IOException {
        readPdf.setDocument(file, startPage, endPage);
    }

    @Override
    public void createPdfFile() throws IOException {
        writePdf.setDocument(new PDDocument());
    }

    @Override
    public List<User> addUserFromPdfTabula(MultipartFile file) throws IOException {
        tabulaUtil.setDocument(file);
        return dbRepository.saveAll(tabulaUtil.parseUsers());
    }

    @Override
    public void imageToPdf(MultipartFile file) throws IOException {
        imageUtil.setDocument(file);
    }
}