package io.github.selcukes.cucumber.steps;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class ReadPDF {
    public boolean verifyPDFContent(String strURL, String reqTextInPDF) throws IOException {
        URL pdfUrl = new URL(strURL);
        BufferedInputStream TestFile = new BufferedInputStream(pdfUrl.openStream());
        PDDocument pdDoc = PDDocument.load(TestFile);
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Specify the range which will read first five pages of the PDF
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(5);
        String parsedText = pdfStripper.getText(pdDoc);
        return parsedText.contains(reqTextInPDF);
    }
}
