package com.HiddenSrilanka.RestAPI.application.response.product;

import com.HiddenSrilanka.RestAPI.application.response.place.AllPlaceResponse;

import java.util.List;

public class BaseAllProductDetails {
    private String StatusCode;
    private String message;
    private List<AllProductResponse> data;

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

    public List<AllProductResponse> getData() {
        return data;
    }

    public void setData(List<AllProductResponse> data) {
        this.data = data;
    }
}
