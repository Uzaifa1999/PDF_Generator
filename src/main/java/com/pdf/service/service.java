package com.pdf.service;


import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class service {

    private final TemplateEngine templateEngine;


    public service(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void generatePdf(String templateName, Context context, HttpServletResponse response) throws IOException {

        String htmlContent = templateEngine.process(templateName, context);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(htmlContent, outputStream, converterProperties);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=generated.pdf");
        response.getOutputStream().write(outputStream.toByteArray());
        Path path = Paths.get("generated.pdf");
        Files.write(path, outputStream.toByteArray());
    }
}

