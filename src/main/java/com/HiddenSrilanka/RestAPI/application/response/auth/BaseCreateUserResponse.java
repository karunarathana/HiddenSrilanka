package com.HiddenSrilanka.RestAPI.application.response.auth;

import com.HiddenSrilanka.RestAPI.domain.model.UserManagementEntity;

public class BaseCreateUserResponse {
    private String status;
    private String message;
    private UserManagementEntity userData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserManagementEntity getUserData() {
        return userData;
    }

    public void setUserData(UserManagementEntity userData) {
        this.userData = userData;
    }
}
