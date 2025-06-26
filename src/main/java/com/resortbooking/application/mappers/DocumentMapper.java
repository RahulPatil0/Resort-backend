package com.resortbooking.application.mappers;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.DocumentsDTO;
import com.resortbooking.application.models.Documents;

public class DocumentMapper {

    public static Documents toEntity(DocumentsDTO dto) {
        if (dto == null) {
            return null;
        }

        Documents entity = new Documents();
       
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static DocumentsDTO toDto(Documents entity) {
        if (entity == null) {
            return null;
        }

        DocumentsDTO dto = new DocumentsDTO();
      BeanUtils.copyProperties(entity, dto);

        return dto;
    }
}
