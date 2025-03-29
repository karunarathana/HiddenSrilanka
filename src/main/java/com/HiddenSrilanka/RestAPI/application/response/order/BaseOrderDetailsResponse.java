package com.HiddenSrilanka.RestAPI.application.response.order;

import com.HiddenSrilanka.RestAPI.domain.model.OrderManagementEntity;

import java.util.List;

public class BaseOrderDetailsResponse {
    private String statusCode;
    private String message;
    private List<OrderManagementEntity> data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderManagementEntity> getData() {
        return data;
    }

    public void setData(List<OrderManagementEntity> data) {
        this.data = data;
    }
}
