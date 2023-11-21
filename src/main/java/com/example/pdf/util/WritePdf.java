package com.example.pdf.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.IOException;

@Component
public class WritePdf {

    private final SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm;
    private PDDocument document;

    public WritePdf(SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm) {
        this.spreadsheetExtractionAlgorithm = spreadsheetExtractionAlgorithm;
    }

    public void setDocument(PDDocument document) throws IOException {
        this.document = document;
        cratePdfFile();
    }

    private void cratePdfFile() throws IOException {
        PDPage page = new PDPage();

        PDFont font = PDType1Font.TIMES_BOLD_ITALIC;
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(font,20);

        contentStream.newLineAtOffset(100,650);

        contentStream.showText("{\"username\":\"emin\", \"password\":\"123456\"}");
        contentStream.endText();

        contentStream.close();

        document.save("C:\\Users\\eminy\\Desktop\\pdf\\User.pdf");
        document.close();
    }
}
