package com.HiddenSrilanka.RestAPI.domain.service;

import com.HiddenSrilanka.RestAPI.application.response.order.BaseOrderDetailsResponse;
import com.HiddenSrilanka.RestAPI.application.response.order.BaseOrderManagementResponse;
import com.HiddenSrilanka.RestAPI.application.response.order.BaseUpdateOrderResponse;
import com.HiddenSrilanka.RestAPI.domain.dto.OrderManagementDTO;
import org.springframework.stereotype.Repository;

public interface OrderManagementService {
    BaseOrderManagementResponse createOrder(OrderManagementDTO orderManagementDTO);
    BaseOrderDetailsResponse getOrderDetailsByBrokerId(String brokerId);
    BaseOrderDetailsResponse getOrderDetailsByUserId(String userId);
    BaseUpdateOrderResponse updateOrderDetails(String orderID);
    BaseUpdateOrderResponse deleteOrderDetails(String orderID);
}
