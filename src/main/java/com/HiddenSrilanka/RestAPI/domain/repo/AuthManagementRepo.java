package com.HiddenSrilanka.RestAPI.domain.repo;


import com.HiddenSrilanka.RestAPI.domain.model.UserManagementEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthManagementRepo extends JpaRepository<UserManagementEntity,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_user SET password = :password WHERE email = :email", nativeQuery = true)
    int updatePasswordByUserEmail(String password, String email);

    @Query(value = "SELECT COUNT(*) > 0 FROM t_user WHERE email = :email", nativeQuery = true)
    Long existsByEmailCount(String email);

    @Query(value = "SELECT * FROM t_user WHERE email = :email LIMIT 1", nativeQuery = true)
    Optional<UserManagementEntity> getSingleUser(String email);

    @Query(value = "SELECT otp_code FROM t_user WHERE email = :email", nativeQuery = true)
    String getOtp(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_user SET otp_code = :otpCode WHERE email = :email", nativeQuery = true)
    int updateOTPByUserEmail(String otpCode, String email);
}
