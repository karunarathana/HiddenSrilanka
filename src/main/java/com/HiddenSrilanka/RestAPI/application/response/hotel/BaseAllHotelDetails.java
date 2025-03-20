package com.HiddenSrilanka.RestAPI.application.response.hotel;

import java.util.List;

public class BaseAllHotelDetails {
    private String StatusCode;
    private String message;
    private List<AllHotelResponse> data;

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

    public List<AllHotelResponse> getData() {
        return data;
    }

    public void setData(List<AllHotelResponse> data) {
        this.data = data;
    }
}
