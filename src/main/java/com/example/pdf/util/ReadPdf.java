package com.example.pdf.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import technology.tabula.ObjectExtractor;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.IOException;

@Component
public class ReadPdf {

    private final SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm;
    private PDDocument document;

    public ReadPdf(SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm) {
        this.spreadsheetExtractionAlgorithm = spreadsheetExtractionAlgorithm;
    }

    public void setDocument(MultipartFile file, int startPage, int endPage) throws IOException {
        PDDocument document = PDDocument.load(file.getBytes());
        this.document = document;
        readDocument(startPage,endPage);
    }

    public String setDocumentUser(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getBytes());
        this.document = document;
        return readDocumentUser();
    }

    private String readDocumentUser() throws IOException {

        PDFTextStripper textStripper = new PDFTextStripper();
        textStripper.setStartPage(1);
        textStripper.setEndPage(1);
        textStripper.setSortByPosition(true); // daha düzenli okunabilir hale geldi.
        System.out.println(textStripper.getText(document));
        return textStripper.getText(document);
    }

    private void readDocument(int startPage, int endPage) throws IOException {
        PDFTextStripper textStripper = new PDFTextStripper();
        textStripper.setStartPage(startPage);
        textStripper.setEndPage(endPage);
        textStripper.setSortByPosition(true); // daha düzenli okunabilir hale geldi.
        System.out.println(textStripper.getText(document));
    }

    public ObjectExtractor getObjectExtractor() {
        return new ObjectExtractor(document);
    }
}
