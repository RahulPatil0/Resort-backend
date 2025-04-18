package com.resortbooking.application.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResortBookingResponse extends ResponseEntity<Object> {
	
	public ResortBookingResponse(Object body, HttpStatusCode status) {
        super(body, status);
    }


}
