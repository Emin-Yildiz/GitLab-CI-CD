package com.example.pdf.util;

import com.example.pdf.domain.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TabulaUtil {

    private PDDocument document;

    private final SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm;

    private String[] parts;

    public TabulaUtil(SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm) {
        this.spreadsheetExtractionAlgorithm = spreadsheetExtractionAlgorithm;
    }

    public void setDocument(MultipartFile file) throws IOException {
        this.document = PDDocument.load(file.getBytes());
        extract();
    }

    private void extract() {
        StringBuilder rowData = new StringBuilder();
        int totalPage = document.getNumberOfPages();

        PageIterator pageIterator = getObjectExtractor().extract();

        while (pageIterator.hasNext()) { // daha sayfa var mı?
            Page page = pageIterator.next();
            List<Table> table = spreadsheetExtractionAlgorithm.extract(page);

//            for (Table table : tables) {
//                for (List<RectangularTextContainer> row : table.getRows()) {
//                    StringBuilder rowData = new StringBuilder();
//                    for (RectangularTextContainer cell : row) {
//                        rowData.append(cell.getText()).append("\t");
//                    }
//                    System.out.println(rowData.toString());
//                }
//            }
//
            table.forEach(
                    tables -> {
                        List<List<RectangularTextContainer>> rows = tables.getRows();
                        rows.forEach(
                                rectangularTextContainers -> {
                                    rectangularTextContainers.forEach(
                                            rectangularTextContainer -> {
                                                rowData.append(rectangularTextContainer.getText()).append("\t");
                                            }
                                    );

                                }
                                );
                        parts = rowData.toString().split("\t");
                    }

            );
        }
    }

    public List<User> parseUsers() {
        List<User> userList = new ArrayList<>();
        for (int i = 2; i < parts.length; i+=2) {
            User user = new User();
            user.setUsername(parts[i]);
            user.setPassword(parts[i+1]);
            userList.add(user);
        }
        return userList;
    }

    private ObjectExtractor getObjectExtractor(){
        return new ObjectExtractor(document);
    }
}
