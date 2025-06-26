package com.resortbooking.application.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.resortbooking.application.dto.DocumentsDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.response.ResortBookingResponse;
import com.resortbooking.application.services.DocumentService;

@RestController
@RequestMapping("api/documents")
public class DocumentsController {

	private static final Logger logger = LoggerFactory.getLogger(DocumentsController.class);

	@Autowired
	private DocumentService documentService;

	// 🔹 CREATE document
	@PostMapping("/create")
	public ResortBookingResponse createDocument(@RequestBody DocumentsDTO documentDto) {
		String message;
		HttpStatus status = HttpStatus.BAD_REQUEST;
		try {
			message = documentService.createDocument(documentDto);
			status = HttpStatus.CREATED;
		} catch (ResortBookingException e) {
			message = e.getMessage();
			logger.error("Error creating document: {}", e.getMessage(), e);
		} catch (Exception e) {
			message = "Error creating document: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Unexpected error: {}", e.getMessage(), e);
		}
		return new ResortBookingResponse(message, status);
	}

	// 🔹 GET all documents
	@GetMapping
	public ResortBookingResponse getAllDocuments() {
		String message;
		HttpStatus status = HttpStatus.BAD_REQUEST;
		try {
			List<DocumentsDTO> documents = documentService.getAllDocuments();
			message = "Fetched " + documents.size() + " documents";
			status = HttpStatus.OK;
			logger.info(message);
			return new ResortBookingResponse(documents, status);
		} catch (ResortBookingException e) {
			message = e.getMessage();
			logger.error("Error fetching documents: {}", message, e);
		} catch (Exception e) {
			message = "Error fetching documents: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error(message, e);
		}
		return new ResortBookingResponse(message, status);
	}

	// 🔹 GET document by ID
	@GetMapping("/{id}")
	public ResortBookingResponse getDocumentById(@PathVariable Long id) {
		String message;
		HttpStatus status = HttpStatus.OK;
		try {
			DocumentsDTO document = documentService.getDocumentById(id);
			if (document != null) {
				message = "Fetched document with ID " + id;
				logger.info(message);
				return new ResortBookingResponse(document, status);
			} else {
				message = "Document not found";
				status = HttpStatus.NOT_FOUND;
				logger.warn(message);
			}
		} catch (ResortBookingException e) {
			message = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
			logger.error("Error fetching document: {}", message, e);
		} catch (Exception e) {
			message = "Error fetching document: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error(message, e);
		}
		return new ResortBookingResponse(message, status);
	}

	// 🔹 UPDATE document
	@PutMapping("/{id}")
	public ResortBookingResponse updateDocument(@PathVariable Long id, @RequestBody DocumentsDTO documentDto) {
		String message;
		HttpStatus status = HttpStatus.BAD_REQUEST;
		try {
			message = documentService.updateDocument(id, documentDto);
			status = HttpStatus.OK;
			logger.info("Updated document with ID {}", id);
		} catch (ResortBookingException e) {
			message = e.getMessage();
			logger.error("Error updating document: {}", message, e);
		} catch (Exception e) {
			message = "Error updating document: " + e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error(message, e);
		}
		return new ResortBookingResponse(message, status);
	}


}
