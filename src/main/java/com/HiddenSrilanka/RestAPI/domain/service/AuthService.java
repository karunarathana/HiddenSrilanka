package com.HiddenSrilanka.RestAPI.domain.service;

import com.HiddenSrilanka.RestAPI.application.response.auth.*;
import com.HiddenSrilanka.RestAPI.domain.dto.UserManagementDTO;

public interface AuthService {
    BaseCreateUserResponse createUser(UserManagementDTO userManagementDTO);
    BaseLoginUserResponse loginUser(String userEmail, String password);
    BasePasswordUpdateResponse updatePassword(String userEmail, String oldPassword, String newPassword);
    BaseAllUserResponse allUserDetails();
    BaseForgotOTPResponse forgotPassword(String userEmail);
    BaseForgotOTPResponse verifyOtpCode(String email,String otpCode);
    BasePasswordUpdateResponse updateForgotPassword(String email,String password);
}
