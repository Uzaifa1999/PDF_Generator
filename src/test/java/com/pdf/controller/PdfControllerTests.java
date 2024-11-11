package com.pdf.controller;


import com.pdf.service.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class PdfControllerTests {

    @Mock
    private service pdfGenerationService;

    @InjectMocks
    private controller pdfController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGeneratePdf() throws IOException {
      
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Sheldon Cooper");
        data.put("amount", 5500);
        MockHttpServletResponse response = new MockHttpServletResponse();
        Context context = new Context();
        context.setVariables(data);


        pdfController.generatePdf(data, response);

     
        verify(pdfGenerationService, times(1)).generatePdf(eq("invoice-template"), any(Context.class), eq(response));
    }

}
