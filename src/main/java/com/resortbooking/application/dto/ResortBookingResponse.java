package com.resortbooking.application.dto;

import org.springframework.http.HttpStatus;

public class ResortBookingResponse<T> {

    private String message;
    private String status;
    private T data;

    public ResortBookingResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.toString();
    }

    public ResortBookingResponse(String message, HttpStatus status, T data) {
        this.message = message;
        this.status = status.toString();
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }
}
