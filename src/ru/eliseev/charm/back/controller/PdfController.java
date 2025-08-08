package ru.eliseev.charm.back.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class PdfController {

    public byte[] getResponse() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            PdfPTable table = new PdfPTable(2); // 2 колонки
            table.addCell("email");
            table.addCell("my email");
            table.addCell("name");
            table.addCell("my name");

            document.add(new Paragraph("Привет"));
            document.add(table);
            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании PDF", e);
        }
    }

}