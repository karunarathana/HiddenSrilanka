package com.HiddenSrilanka.RestAPI.application.controller;

import com.HiddenSrilanka.RestAPI.application.response.order.BaseOrderDetailsResponse;
import com.HiddenSrilanka.RestAPI.application.response.order.BaseOrderManagementResponse;
import com.HiddenSrilanka.RestAPI.application.response.order.BaseUpdateOrderResponse;
import com.HiddenSrilanka.RestAPI.constant.Constant;
import com.HiddenSrilanka.RestAPI.domain.dto.OrderManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.service.OrderManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(Constant.API_ROOT)
public class OrderManagementController {
    private static final Logger logger = LoggerFactory.getLogger(OrderManagementController.class);
    private final OrderManagementService orderManagementService;

    public OrderManagementController(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }

    @RequestMapping(value =Constant.CREATE_ORDER ,method = RequestMethod.POST)
    public ResponseEntity<BaseOrderManagementResponse> createOrder(@RequestBody OrderManagementDTO orderManagementDTO) throws IOException {
        logger.info("Request Started In createOrder |OrderDetails={}",orderManagementDTO);
        BaseOrderManagementResponse response = orderManagementService.createOrder(orderManagementDTO);
        logger.info("Request Completed In createHotel |OrderDetails={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.GET_ALL_ORDER_DETAILS_BROKER_ID ,method = RequestMethod.GET)
    public ResponseEntity<BaseOrderDetailsResponse> getOrderDetailsByBrokerId(@RequestParam("brokerId") String brokerId) throws IOException {
        logger.info("Request Started In getOrderDetailsByBrokerId |BrokerID={}",brokerId);
        BaseOrderDetailsResponse response = orderManagementService.getOrderDetailsByBrokerId(brokerId);
        logger.info("Request Completed In getOrderDetailsByBrokerId |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.GET_ALL_ORDER_DETAILS_USER_ID ,method = RequestMethod.GET)
    public ResponseEntity<BaseOrderDetailsResponse> getOrderDetailsByUserId(@RequestParam("userId") String userId) throws IOException {
        logger.info("Request Started In getOrderDetailsByUserId |UserId={}",userId);
        BaseOrderDetailsResponse response = orderManagementService.getOrderDetailsByUserId(userId);
        logger.info("Request Completed In getOrderDetailsByUserId |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.UPDATE_ORDER_STATUS ,method = RequestMethod.POST)
    public ResponseEntity<BaseUpdateOrderResponse> updateOrderDetails(@RequestParam("orderId") String orderID,@RequestParam("status") String status) throws IOException {
        logger.info("Request Started In updateOrderDetails |OrderID={}",orderID);
        BaseUpdateOrderResponse response = orderManagementService.updateOrderDetails(status,orderID);
        logger.info("Request Completed In updateOrderDetails |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.DELETE_ORDER_DETAILS ,method = RequestMethod.DELETE)
    public ResponseEntity<BaseUpdateOrderResponse> deleteOrderDetails(@RequestParam("orderId") String orderID) throws IOException {
        logger.info("Request Started In deleteOrderDetails |OrderID={}",orderID);
        BaseUpdateOrderResponse response = orderManagementService.deleteOrderDetails(orderID);
        logger.info("Request Completed In deleteOrderDetails |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
