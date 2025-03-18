package com.HiddenSrilanka.RestAPI.application.controller;

import com.HiddenSrilanka.RestAPI.application.response.auth.*;
import com.HiddenSrilanka.RestAPI.constant.Constant;
import com.HiddenSrilanka.RestAPI.domain.dto.UserManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(Constant.API_ROOT)
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = Constant.CREATE_USER ,method = RequestMethod.POST)
    public ResponseEntity<BaseCreateUserResponse> createAuthUser(@RequestBody UserManagementDTO userManagementDTO){
        logger.info("Request Started IN createAuthUser |Request={}",userManagementDTO);
        BaseCreateUserResponse response = authService.createUser(userManagementDTO);
        logger.info("Request Completed IN createAuthUser |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value = Constant.LOGIN_USER ,method = RequestMethod.GET)
    public ResponseEntity<BaseLoginUserResponse> authLoginUser(@RequestParam("email") String email,@RequestParam("password")String password){
        logger.info("Request Started IN authLoginUser |Email={} |Password{}",email,password);
        BaseLoginUserResponse response = authService.loginUser(email, password);
        logger.info("Request Completed IN authLoginUser |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = Constant.UPDATE_USER_PASSWORD ,method = RequestMethod.POST)
    public ResponseEntity<BasePasswordUpdateResponse> authUpdatePassword(@RequestParam("email") String email,@RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword){
        logger.info("Request Started IN authUpdatePassword |Email={} |OldPassword{} |NewPassword{}",email,oldPassword,newPassword);
        BasePasswordUpdateResponse response = authService.updatePassword(email, oldPassword, newPassword);
        logger.info("Request Completed IN authUpdatePassword |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = Constant.FORGOT_USER_PASSWORD_UPDATE ,method = RequestMethod.GET)
    public ResponseEntity<BasePasswordUpdateResponse> forgotUpdatePassword(@RequestParam("email") String email,@RequestParam("password")String password){
        logger.info("Request Started IN forgotUpdatePassword |Email={} |Password{}",email,password);
        BasePasswordUpdateResponse response = authService.updateForgotPassword(email,password);
        logger.info("Request Completed IN forgotUpdatePassword |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = Constant.GET_ALL_USERS ,method = RequestMethod.GET)
    public ResponseEntity<BaseAllUserResponse> allUsers(){
        logger.info("Request Started IN allUsers");
        BaseAllUserResponse response = authService.allUserDetails();
        logger.info("Request Completed IN allUsers |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = Constant.FORGOT_USER_PASSWORD ,method = RequestMethod.GET)
    public ResponseEntity<BaseForgotOTPResponse> forgotPassword(@RequestParam("email") String email){
        logger.info("Request Started IN forgotPassword");
        BaseForgotOTPResponse response = authService.forgotPassword(email);
        logger.info("Request Completed IN forgotPassword |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = Constant.VERIFY_OTP_CODE ,method = RequestMethod.GET)
    public ResponseEntity<BaseForgotOTPResponse> validOTP(@RequestParam("email") String email,@RequestParam("otp") String otp){
        logger.info("Request Started IN validOTP");
        BaseForgotOTPResponse response = authService.verifyOtpCode(email,otp);
        logger.info("Request Completed IN validOTP |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
