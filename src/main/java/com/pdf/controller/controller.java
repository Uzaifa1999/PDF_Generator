package com.pdf.controller;




import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
	import org.thymeleaf.context.Context;

import com.pdf.service.service;

import jakarta.servlet.http.HttpServletResponse;
	import java.io.IOException;
	import java.util.Map;

	@RestController
	@RequestMapping("/api/pdf")
	public class controller {

	    private final service pdfGenerationService;


	    public controller(service pdfGenerationService) {
	        this.pdfGenerationService = pdfGenerationService;
	    }

	    @PostMapping("/generate")
	    public void generatePdf(@RequestBody Map<String, Object> data, HttpServletResponse response) throws IOException {
	        Context context = new Context();
	        context.setVariables(data);
	        pdfGenerationService.generatePdf("invoice-template", context, response);
	    }
	}


