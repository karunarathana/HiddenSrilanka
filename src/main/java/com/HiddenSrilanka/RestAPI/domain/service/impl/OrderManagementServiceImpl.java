package com.HiddenSrilanka.RestAPI.domain.service.impl;

import com.HiddenSrilanka.RestAPI.application.response.order.BaseOrderDetailsResponse;
import com.HiddenSrilanka.RestAPI.application.response.order.BaseOrderManagementResponse;
import com.HiddenSrilanka.RestAPI.application.response.order.BaseUpdateOrderResponse;
import com.HiddenSrilanka.RestAPI.domain.dto.OrderManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.model.OrderManagementEntity;
import com.HiddenSrilanka.RestAPI.domain.repo.OrderManagementRepo;
import com.HiddenSrilanka.RestAPI.domain.service.OrderManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {
    private static final Logger logger = LoggerFactory.getLogger(OrderManagementServiceImpl.class);
    private final OrderManagementRepo orderManagementRepo;

    public OrderManagementServiceImpl(OrderManagementRepo orderManagementRepo) {
        this.orderManagementRepo = orderManagementRepo;
    }

    public BaseOrderManagementResponse createOrder(OrderManagementDTO orderManagementDTO){
        logger.info("Method Execution Start In createOrder |OrderDetails={}",orderManagementDTO);
        OrderManagementEntity orderManagementEntity = setValueEntityClass(orderManagementDTO);
        OrderManagementEntity saveResponse = orderManagementRepo.save(orderManagementEntity);
        BaseOrderManagementResponse baseOrderManagementResponse = new BaseOrderManagementResponse();
        baseOrderManagementResponse.setMessage("Operation Successfully");
        baseOrderManagementResponse.setStatusCode("201");
        return baseOrderManagementResponse;
    }
    public OrderManagementEntity setValueEntityClass(OrderManagementDTO orderManagementDTO){
        logger.info("Method Execution Start In setValueEntityClass |OrderDetails={}",orderManagementDTO);
        OrderManagementEntity orderManagementEntity = new OrderManagementEntity();
        orderManagementEntity.setBrokerId(orderManagementDTO.getBrokerId());
        orderManagementEntity.setContactEmail(orderManagementDTO.getContactEmail());
        orderManagementEntity.setHotelName(orderManagementDTO.getHotelName());
        orderManagementEntity.setStatus(orderManagementDTO.getStatus());
        orderManagementEntity.setPaymentStatus(orderManagementDTO.getPaymentStatus());
        orderManagementEntity.setTotalPrice(orderManagementDTO.getTotalPrice());
        orderManagementEntity.setUserId(orderManagementDTO.getUserId());
        orderManagementEntity.setContactNumber(orderManagementDTO.getContactNumber());
        logger.info("Method Execution Complete In setValueEntityClass |OrderEntity={}",orderManagementEntity);
        return orderManagementEntity;
    }
    public BaseOrderDetailsResponse getOrderDetailsByBrokerId(String brokerId){
        logger.info("Method Execution Start In getOrderDetailsByBrokerId |BrokerId={}",brokerId);
        BaseOrderDetailsResponse baseOrderDetailsResponse = new BaseOrderDetailsResponse();
        Optional<List<OrderManagementEntity>> orderDetailsByBrokerId = orderManagementRepo.getOrderDetailsByBrokerId(brokerId);
        if (orderDetailsByBrokerId.isPresent()){
            baseOrderDetailsResponse.setStatusCode("201");
            baseOrderDetailsResponse.setMessage("Operation Successfully");
            baseOrderDetailsResponse.setData(orderDetailsByBrokerId.get());
            return baseOrderDetailsResponse;
        }
        baseOrderDetailsResponse.setStatusCode("401");
        baseOrderDetailsResponse.setMessage("Operation Failed!");
        baseOrderDetailsResponse.setData(null);
        return baseOrderDetailsResponse;


    }

    @Override
    public BaseOrderDetailsResponse getOrderDetailsByUserId(String userId) {
        logger.info("Method Execution Start In getOrderDetailsByUserId |BrokerId={}",userId);
        BaseOrderDetailsResponse baseOrderDetailsResponse = new BaseOrderDetailsResponse();
        Optional<List<OrderManagementEntity>> orderDetailsByUserId = orderManagementRepo.getOrderDetailsByUserId(userId);
        if (orderDetailsByUserId.isPresent()){
            baseOrderDetailsResponse.setStatusCode("201");
            baseOrderDetailsResponse.setMessage("Operation Successfully");
            baseOrderDetailsResponse.setData(orderDetailsByUserId.get());
            return baseOrderDetailsResponse;
        }
        baseOrderDetailsResponse.setStatusCode("401");
        baseOrderDetailsResponse.setMessage("Operation Failed!");
        baseOrderDetailsResponse.setData(null);
        return baseOrderDetailsResponse;
    }

    @Override
    public BaseUpdateOrderResponse updateOrderDetails(String status,String orderID) {
        logger.info("Method Execution Start In updateOrderDetails |OrderID={}",orderID);
        BaseUpdateOrderResponse baseUpdateOrderResponse = new BaseUpdateOrderResponse();
        int accept = orderManagementRepo.updateStatusByOrderId(status, orderID);
        if (accept == 1){
            baseUpdateOrderResponse.setMessage("Update Successfully");
            baseUpdateOrderResponse.setStatusCode("201");
            return baseUpdateOrderResponse;
        }
        baseUpdateOrderResponse.setMessage("Operation Failed!");
        baseUpdateOrderResponse.setStatusCode("401");
        return null;
    }

    @Override
    public BaseUpdateOrderResponse deleteOrderDetails(String orderID) {
        logger.info("Method Execution Start In deleteOrderDetails |OrderID={}",orderID);
        orderManagementRepo.deleteById(Integer.valueOf(orderID));
        BaseUpdateOrderResponse baseUpdateOrderResponse = new BaseUpdateOrderResponse();
        baseUpdateOrderResponse.setMessage("Delete Successfully");
        baseUpdateOrderResponse.setStatusCode("201");
        return baseUpdateOrderResponse;
    }

}
