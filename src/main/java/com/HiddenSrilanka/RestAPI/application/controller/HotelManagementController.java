package com.HiddenSrilanka.RestAPI.application.controller;

import com.HiddenSrilanka.RestAPI.application.response.hotel.BaseAllHotelDetails;
import com.HiddenSrilanka.RestAPI.application.response.hotel.BaseCreateHotelResponse;
import com.HiddenSrilanka.RestAPI.constant.Constant;
import com.HiddenSrilanka.RestAPI.domain.dto.HotelManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.service.HotelManagementService;
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
public class HotelManagementController {
    private static final Logger logger = LoggerFactory.getLogger(HotelManagementController.class);
    private final HotelManagementService hotelManagementService;

    public HotelManagementController(HotelManagementService hotelManagementService) {
        this.hotelManagementService = hotelManagementService;
    }

    @RequestMapping(value =Constant.CREATE_HOTEL ,method = RequestMethod.POST)
    public ResponseEntity<BaseCreateHotelResponse> createHotel(@RequestParam("images") List<MultipartFile> images, @RequestParam("HotelData") String hotelDetails) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HotelManagementDTO hotelManagementDTO = mapper.readValue(hotelDetails, HotelManagementDTO.class);
        logger.info("Request Started In createHotel |Images={} |HotelDetails={}",images,hotelManagementDTO);
        BaseCreateHotelResponse response = hotelManagementService.createHotel(images, hotelManagementDTO);
        logger.info("Request Completed In createHotel |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.GET_ALL_HOTELS ,method = RequestMethod.GET)
    public ResponseEntity<BaseAllHotelDetails> allHotels(){
        logger.info("Request Started In allHotels ");
        BaseAllHotelDetails response = hotelManagementService.getAllHotelDetails();
        logger.info("Request Completed In allHotels |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//    @RequestMapping(value =Constant.GET_SINGLE_PLACE_BY_ID ,method = RequestMethod.GET)
//    public ResponseEntity<BaseAllPlacesDetails> singlePlace(@RequestParam("id") String id){
//        logger.info("Request Started In singlePlace |Id={}",id);
//        BaseAllPlacesDetails response = hotelManagementService.getSingleHotelDetailsById(Integer.parseInt(id));
//        logger.info("Request Completed In singlePlace |Response={}",response);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//    @RequestMapping(value =Constant.DELETE_PLACE_BY_ID ,method = RequestMethod.DELETE)
//    public ResponseEntity<BasePlaceDeleteResponse> deleteSinglePlace(@RequestParam("id") String id){
//        logger.info("Request Started In deleteSinglePlace |PlaceId={}",id);
//        BasePlaceDeleteResponse response = hotelManagementService.deleteHotelById(Integer.parseInt(id));
//        logger.info("Request Completed In deleteSinglePlace |Response={}",response);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
