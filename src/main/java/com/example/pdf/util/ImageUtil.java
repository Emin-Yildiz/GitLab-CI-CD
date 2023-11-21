package com.example.pdf.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageUtil {

    private PDDocument document;

    private PDImageXObject pdImageXObject;

    public void setDocument(MultipartFile file) throws IOException {
        document = PDDocument.load(file.getBytes());
        extractImage(file);
    }

    private void extractImage(MultipartFile file) throws IOException {
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(i,300);
            ImageIO.write(image,"PNG",new File("./img/page_" + (i+1) + ".png"));
        }

    }
}
