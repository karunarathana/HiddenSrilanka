package com.HiddenSrilanka.RestAPI.application.response.place;

import java.util.List;

public class BaseAllPlacesDetails {
    private String StatusCode;
    private String message;
    private List<AllPlaceResponse> data;

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

    public List<AllPlaceResponse> getData() {
        return data;
    }

    public void setData(List<AllPlaceResponse> data) {
        this.data = data;
    }
}
