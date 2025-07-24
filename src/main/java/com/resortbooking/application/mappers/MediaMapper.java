package com.resortbooking.application.mappers;

import org.springframework.beans.BeanUtils;

import com.resortbooking.application.dto.MediaDto;
import com.resortbooking.application.models.Media;

public class MediaMapper {

    public static MediaDto toDto(Media media) {
        
    	MediaDto dto = new MediaDto();
    	
    	BeanUtils.copyProperties(media, dto);
    	
    	return dto;
    }

    public static Media toEntity(MediaDto dto) {

        Media media = new Media();
        
        BeanUtils.copyProperties(dto, media);

        return media;
    }
}
