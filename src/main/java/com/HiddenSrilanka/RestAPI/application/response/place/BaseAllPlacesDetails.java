package com.HiddenSrilanka.RestAPI.application.response.place;

import java.util.List;

public class BaseAllPlacesDetails {
    private String StatusCode;
    private String message;
    private List<AllPlacesResponse> data;

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

    public List<AllPlacesResponse> getData() {
        return data;
    }

    public void setData(List<AllPlacesResponse> data) {
        this.data = data;
    }
}
