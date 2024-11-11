package com.pdf.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PdfServiceTests {

    @Mock
    private TemplateEngine templateEngine;

    private service pdfService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pdfService = new service(templateEngine);
    }

    @Test
    public void testGeneratePdf() throws IOException {

        String templateName = "invoice-template";
        Context context = new Context();
        context.setVariable("name", "Sheldon Cooper");
        context.setVariable("amount", 5500);
        MockHttpServletResponse response = new MockHttpServletResponse();
        String htmlContent = "<html><body><h1>Invoice</h1><p>Name: Sheldon Cooper</p><p>Amount: $5500</p></body></html>";

        when(templateEngine.process(templateName, context)).thenReturn(htmlContent);


        pdfService.generatePdf(templateName, context, response);


        verify(templateEngine, times(1)).process(templateName, context);
        assert response.getContentType().equals("application/pdf");
        assert response.getHeader("Content-Disposition").equals("inline; filename=generated.pdf");
        assert response.getContentAsByteArray().length > 0;
    }
}
