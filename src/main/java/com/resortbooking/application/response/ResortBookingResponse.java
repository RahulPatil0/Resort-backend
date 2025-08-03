package com.resortbooking.application.response;

import org.springframework.http.HttpStatus;

public class ResortBookingResponse<T> {

    private String status;
    private T data;
    
    public ResortBookingResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status.toString();
    }

   

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

   

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }
}
