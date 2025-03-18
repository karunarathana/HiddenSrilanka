package com.HiddenSrilanka.RestAPI.application.response.place;

public class BaseCreatePlaceResponse {
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
