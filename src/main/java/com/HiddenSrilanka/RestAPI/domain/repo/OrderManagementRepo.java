package com.HiddenSrilanka.RestAPI.domain.repo;

import com.HiddenSrilanka.RestAPI.domain.model.OrderManagementEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderManagementRepo extends JpaRepository<OrderManagementEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE t_order SET status = :status WHERE order_id = :orderId", nativeQuery = true)
    int updateStatusByOrderId(String status, String orderId);

    @Query(value = "SELECT * FROM t_order WHERE broker_id = :brokerId", nativeQuery = true)
    Optional <List<OrderManagementEntity>> getOrderDetailsByBrokerId(String brokerId);

    @Query(value = "SELECT * FROM t_order WHERE user_id = :userId", nativeQuery = true)
    Optional <List<OrderManagementEntity>> getOrderDetailsByUserId(String userId);
}
