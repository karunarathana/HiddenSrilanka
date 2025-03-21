package com.HiddenSrilanka.RestAPI.application.response.hotel;

public class BaseCreateHotelResponse {
    private String StatusCode;
    private String message;

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
