package com.resortbooking.application.services;

import java.util.List;

import com.resortbooking.application.dto.DocumentsDTO;
import com.resortbooking.application.exception.ResortBookingException;

public interface DocumentService {

    String createDocument(DocumentsDTO documentDto) throws ResortBookingException;

    List<DocumentsDTO> getAllDocuments() throws ResortBookingException;

    DocumentsDTO getDocumentById(Long id) throws ResortBookingException;
    String updateDocument(Long id, DocumentsDTO documentDto) throws ResortBookingException;
}
