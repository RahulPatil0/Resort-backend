package com.resortbooking.application.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resortbooking.application.dto.DocumentsDTO;
import com.resortbooking.application.exception.ResortBookingException;
import com.resortbooking.application.mappers.DocumentMapper;
import com.resortbooking.application.models.Documents;
import com.resortbooking.application.dao.DocumentsRepository;
import com.resortbooking.application.services.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	private DocumentsRepository documentRepository;

	@Override
	public String createDocument(DocumentsDTO documentDto) throws ResortBookingException {
		try {
			if (documentDto == null) {
				throw new ResortBookingException("Document URL must not be empty.");
			}

			Documents document = DocumentMapper.toEntity(documentDto);
			document.setCreatedAt(LocalDateTime.now());

			document = documentRepository.save(document);
			logger.info("Document created successfully with ID {}", document.getId());
			return "Document created successfully with ID " + document.getId();
		} catch (Exception e) {
			logger.error("Error creating document: {}", e.getMessage(), e);
			throw new ResortBookingException("Error creating document: " + e.getMessage());
		}
	}

	@Override
	public List<DocumentsDTO> getAllDocuments() throws ResortBookingException {
		try {
			List<Documents> documents = documentRepository.findAll();
			logger.info("Fetched {} documents", documents.size());
			return documents.stream().map(DocumentMapper::toDto).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Error fetching all documents: {}", e.getMessage(), e);
			throw new ResortBookingException("Error fetching all documents: " + e.getMessage());
		}
	}

	@Override
	public DocumentsDTO getDocumentById(Long id) throws ResortBookingException {
		try {
			return documentRepository.findById(id).map(DocumentMapper::toDto).orElse(null);
		} catch (Exception e) {
			logger.error("Error fetching document by ID {}: {}", id, e.getMessage(), e);
			throw new ResortBookingException("Error fetching document by ID: " + e.getMessage());
		}
	}

	@Override
	public String updateDocument(Long id, DocumentsDTO documentDto) throws ResortBookingException {
		// TODO Auto-generated method stub
		return null;
	}
}
