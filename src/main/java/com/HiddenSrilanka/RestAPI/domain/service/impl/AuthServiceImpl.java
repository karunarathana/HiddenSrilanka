package com.HiddenSrilanka.RestAPI.domain.service.impl;

import com.HiddenSrilanka.RestAPI.application.response.auth.*;
import com.HiddenSrilanka.RestAPI.domain.dto.UserManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.model.UserManagementEntity;
import com.HiddenSrilanka.RestAPI.domain.repo.AuthManagementRepo;
import com.HiddenSrilanka.RestAPI.domain.service.AuthService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthManagementRepo authManagementRepo;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, AuthManagementRepo authManagementRepo, JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authManagementRepo = authManagementRepo;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public BaseCreateUserResponse createUser(UserManagementDTO userManagementDTO){
        logger.info("Method Execution Start In createUser |Data={}",userManagementDTO);
        BaseCreateUserResponse baseCreateUserResponse = new BaseCreateUserResponse();
        Long existsByEmail = authManagementRepo.existsByEmailCount(userManagementDTO.getEmail());
        if(existsByEmail!=1){
            UserManagementEntity userManagementEntity = setDataUserManagementEntity(userManagementDTO);
            authManagementRepo.save(userManagementEntity);
            baseCreateUserResponse.setStatus("201");
            baseCreateUserResponse.setMessage("Operation Successfully");
            baseCreateUserResponse.setUserData(userManagementEntity);
            return baseCreateUserResponse;
        }
        baseCreateUserResponse.setStatus("400");
        baseCreateUserResponse.setMessage("User Already Existing");
        baseCreateUserResponse.setUserData(null);
        return baseCreateUserResponse;
    }
    public UserManagementEntity setDataUserManagementEntity(UserManagementDTO userManagementDTO){
        logger.info("Method Execution Start In setDataUserManagementEntity |Data={}",userManagementDTO);
        UserManagementEntity userManagementEntity = new UserManagementEntity();
        userManagementEntity.setUserName(userManagementDTO.getUserName());
        userManagementEntity.setEmail(userManagementDTO.getEmail());
        userManagementEntity.setPassword(bCryptPasswordEncoder.encode(userManagementDTO.getPassword()));
        userManagementEntity.setEmail(userManagementDTO.getEmail());
        userManagementEntity.setAddress(userManagementDTO.getAddress());
        userManagementEntity.setTell_number(userManagementDTO.getTell_number());
        userManagementEntity.setGender(userManagementDTO.getGender());
        userManagementEntity.setDate_of_birthday(userManagementDTO.getDate_of_birthday());
        userManagementEntity.setStatus(userManagementDTO.getStatus());
        userManagementEntity.setLogin_date(userManagementDTO.getLogin_date());
        userManagementEntity.setNic_number(userManagementDTO.getNic_number());
        userManagementEntity.setRole(userManagementDTO.getRole());
        userManagementEntity.setVerified(userManagementDTO.getVerified());
        userManagementDTO.setOtpCode(userManagementDTO.getOtpCode());
        logger.info("Method Execution Completed In setDataUserManagementEntity |Data={}",userManagementEntity);
        return userManagementEntity;
    }
    public BaseLoginUserResponse loginUser(String userEmail,String password){
        logger.info("Method Execution Start In loginUser |UserEmail={} |UserPassword={}",userEmail,password);
        BaseLoginUserResponse baseLoginUserResponse = new BaseLoginUserResponse();
        if(authManagementRepo.getSingleUser(userEmail).isPresent()){
            UserManagementEntity userDetails = authManagementRepo.getSingleUser(userEmail).get();
            if(bCryptPasswordEncoder.matches(password,userDetails.getPassword())){
                baseLoginUserResponse.setStatus("200");
                baseLoginUserResponse.setMessage("Operation Successfully");
                baseLoginUserResponse.setUserData(userDetails);
                return baseLoginUserResponse;
            }
            baseLoginUserResponse.setStatus("200");
            baseLoginUserResponse.setMessage("Password Doesn't Match");
            baseLoginUserResponse.setUserData(null);
            return baseLoginUserResponse;
        }
        baseLoginUserResponse.setStatus("200");
        baseLoginUserResponse.setMessage("UserEmail Doesn't Match");
        baseLoginUserResponse.setUserData(null);
        return baseLoginUserResponse;
    }
    public BasePasswordUpdateResponse updatePassword(String userEmail,String oldPassword,String newPassword){
        logger.info("Method Execution Start In updatePassword |OldPassword={} |NewPassword={}",oldPassword,newPassword);
        BasePasswordUpdateResponse basePasswordUpdateResponse = new BasePasswordUpdateResponse();
        if(authManagementRepo.getSingleUser(userEmail).isPresent()){
            UserManagementEntity userDetails = authManagementRepo.getSingleUser(userEmail).get();
            if(bCryptPasswordEncoder.matches(oldPassword,userDetails.getPassword())){
                int passwordUpdate = authManagementRepo.updatePasswordByUserEmail(bCryptPasswordEncoder.encode(newPassword), userEmail);
                if(passwordUpdate!=0){
                    basePasswordUpdateResponse.setStatus("200");
                    basePasswordUpdateResponse.setMessage("Password Update Successfully");
                    return basePasswordUpdateResponse;
                }
                basePasswordUpdateResponse.setStatus("200");
                basePasswordUpdateResponse.setMessage("Password Update Unsuccessfully");
                return basePasswordUpdateResponse;
            }
            basePasswordUpdateResponse.setStatus("200");
            basePasswordUpdateResponse.setMessage("Old Password Doesn't Match");
            return basePasswordUpdateResponse;
        }
        basePasswordUpdateResponse.setStatus("200");
        basePasswordUpdateResponse.setMessage("UserEmail Doesn't Match");
        return basePasswordUpdateResponse;
    }
    public BaseAllUserResponse allUserDetails(){
        logger.info("Method Execution Start In allUserDetails");
        List<UserManagementEntity> allUsers = authManagementRepo.findAll();
        BaseAllUserResponse baseAllUserResponse = new BaseAllUserResponse();
        baseAllUserResponse.setStatus("200");
        baseAllUserResponse.setMessage("Operation Successfully");
        baseAllUserResponse.setUserData(allUsers);
        logger.info("Method Execution Completed In allUserDetails");
        return baseAllUserResponse;
    }
    public BaseForgotOTPResponse forgotPassword(String userEmail){
        logger.info("Method Execution Start In forgotPassword");
        BaseForgotOTPResponse baseForgotOTPResponse = new BaseForgotOTPResponse();
        Long existsByEmail = authManagementRepo.existsByEmailCount(userEmail);
        if(existsByEmail!=0){
            Random random = new Random();
            double otpCode = Math.random()*(900000-100000+1)+100000;
            String setOpt = String.valueOf(random.nextInt((int) otpCode));
            int saveOtp = authManagementRepo.updateOTPByUserEmail(setOpt, userEmail);
            if(saveOtp !=0){
//                sendHtmlEmail(userEmail,"","");
                baseForgotOTPResponse.setStatus("201");
                baseForgotOTPResponse.setMessage("Otp send successfully");
                return baseForgotOTPResponse;
            }
            baseForgotOTPResponse.setStatus("400");
            baseForgotOTPResponse.setMessage("Operation fail");
            return baseForgotOTPResponse;
        }
        baseForgotOTPResponse.setStatus("201");
        baseForgotOTPResponse.setMessage("No Existing Email");
        return baseForgotOTPResponse;
    }
    public BaseForgotOTPResponse verifyOtpCode(String email,String otpCode){
        logger.info("Method Execution Start In verifyOtpCode");
        BaseForgotOTPResponse baseForgotOTPResponse = new BaseForgotOTPResponse();
        String otp = authManagementRepo.getOtp(email);
        if(otp.equals(otpCode)){
            baseForgotOTPResponse.setStatus("200");
            baseForgotOTPResponse.setMessage("Operation Successfully");
            return baseForgotOTPResponse;
        }
        baseForgotOTPResponse.setStatus("200");
        baseForgotOTPResponse.setMessage("OTP Code Not Match");
        return baseForgotOTPResponse;
    }
    public boolean sendHtmlEmail(String to,String subject,String body){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            Context context = new Context();
            //context.setVariable("subject",subject);
            context.setVariable("message",body);
            String htmlBody = templateEngine.process("otpTemplate",context);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody,true);

            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
