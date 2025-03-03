package com.HiddenSrilanka.RestAPI.application.response.auth;

import com.HiddenSrilanka.RestAPI.domain.model.UserManagementEntity;

import java.util.List;

public class BaseAllUserResponse {
    private String status;
    private String message;
    private List<UserManagementEntity> userData;

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

    public List<UserManagementEntity> getUserData() {
        return userData;
    }

    public void setUserData(List<UserManagementEntity> userData) {
        this.userData = userData;
    }
}
