package com.HiddenSrilanka.RestAPI.application.controller;

import com.HiddenSrilanka.RestAPI.application.response.place.BaseAllPlacesDetails;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseCreatePlaceResponse;
import com.HiddenSrilanka.RestAPI.application.response.product.BaseAllProductDetails;
import com.HiddenSrilanka.RestAPI.constant.Constant;
import com.HiddenSrilanka.RestAPI.domain.dto.ProductManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.service.ProductManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(Constant.API_ROOT)
public class ProductManagementController {
    private static final Logger logger = LoggerFactory.getLogger(ProductManagementController.class);
    private final ProductManagementService productManagementService;

    public ProductManagementController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @RequestMapping(value =Constant.CREATE_PRODUCT ,method = RequestMethod.POST)
    public ResponseEntity<BaseCreatePlaceResponse> createProduct(@RequestParam("images") List<MultipartFile> images, @RequestParam("productData") String productData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProductManagementDTO productManagementDTO = mapper.readValue(productData,ProductManagementDTO.class);
        logger.info("Request Started In createProduct |Images={} |ProductData={}",images,productManagementDTO);
        BaseCreatePlaceResponse response = productManagementService.createProduct(images,productManagementDTO);
        logger.info("Request Completed In createProduct |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.GET_ALL_PRODUCT ,method = RequestMethod.GET)
    public ResponseEntity<BaseAllProductDetails> allProduct(){
        logger.info("Request Started In allProduct ");
        BaseAllProductDetails response = productManagementService.getAllProductDetails();
        logger.info("Request Completed In allProduct |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
