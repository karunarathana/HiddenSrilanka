package com.HiddenSrilanka.RestAPI.application.response.order;

import com.HiddenSrilanka.RestAPI.domain.model.OrderManagementEntity;

import java.util.List;

public class BaseOrderDetailsResponse {
    private String statusCode;
    private String message;
    private List<OrderManagementEntity> orderManagementEntities;

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

    public List<OrderManagementEntity> getOrderManagementEntities() {
        return orderManagementEntities;
    }

    public void setOrderManagementEntities(List<OrderManagementEntity> orderManagementEntities) {
        this.orderManagementEntities = orderManagementEntities;
    }
}
