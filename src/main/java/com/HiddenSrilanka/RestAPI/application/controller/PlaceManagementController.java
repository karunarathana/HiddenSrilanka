package com.HiddenSrilanka.RestAPI.application.controller;

import com.HiddenSrilanka.RestAPI.application.response.place.BaseAllPlacesDetails;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseCreatePlaceResponse;
import com.HiddenSrilanka.RestAPI.application.response.place.BasePlaceDeleteResponse;
import com.HiddenSrilanka.RestAPI.application.response.product.BaseAllProductDetails;
import com.HiddenSrilanka.RestAPI.constant.Constant;
import com.HiddenSrilanka.RestAPI.domain.dto.PlaceManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.service.PlaceManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class PlaceManagementController {
    private static final Logger logger = LoggerFactory.getLogger(PlaceManagementController.class);
    private final PlaceManagementService placeManagementService;

    public PlaceManagementController(PlaceManagementService placeManagementService) {
        this.placeManagementService = placeManagementService;
    }

    @RequestMapping(value =Constant.CREATE_PLACE ,method = RequestMethod.POST)
    public ResponseEntity<BaseCreatePlaceResponse> createPlace(@RequestParam("images") List<MultipartFile> images, @RequestParam("placeData") String placeDetails) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PlaceManagementDTO placeManagementDTO = mapper.readValue(placeDetails, PlaceManagementDTO.class);
        logger.info("Request Started In uploadPlaceDetails |Images={} |PlaceDetails={}",images,placeManagementDTO);
        BaseCreatePlaceResponse response = placeManagementService.createPlace(images, placeManagementDTO);
        logger.info("Request Completed In createPlace |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.GET_ALL_PLACES ,method = RequestMethod.GET)
    public ResponseEntity<BaseAllPlacesDetails> allPlaces(){
        logger.info("Request Started In allPlaces ");
        BaseAllPlacesDetails response = placeManagementService.getAllPlaceDetails();
        logger.info("Request Completed In allPlaces |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.GET_SINGLE_PLACE_BY_ID ,method = RequestMethod.GET)
    public ResponseEntity<BaseAllPlacesDetails> singlePlace(@RequestParam("id") String id){
        logger.info("Request Started In singlePlace |Id={}",id);
        BaseAllPlacesDetails response = placeManagementService.getSinglePlaceDetailsById(Integer.parseInt(id));
        logger.info("Request Completed In singlePlace |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @RequestMapping(value =Constant.DELETE_PLACE_BY_ID ,method = RequestMethod.DELETE)
    public ResponseEntity<BasePlaceDeleteResponse> deleteSinglePlace(@RequestParam("id") String id){
        logger.info("Request Started In deleteSinglePlace |PlaceId={}",id);
        BasePlaceDeleteResponse response = placeManagementService.deletePlaceById(Integer.parseInt(id));
        logger.info("Request Completed In deleteSinglePlace |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value =Constant.SEARCH_PLACE_BY_CITY ,method = RequestMethod.GET)
    public ResponseEntity<BaseAllPlacesDetails> searchPlaceByCity(@RequestParam("city") String city){
        logger.info("Request Started In searchPlaceByCity |PlaceId={}",city);
        BaseAllPlacesDetails response = placeManagementService.getPlaceDetailsByCity(city);
        logger.info("Request Completed In searchPlaceByCity |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value =Constant.UPDATE_PLACE_STATUS ,method = RequestMethod.POST)
    public ResponseEntity<BaseCreatePlaceResponse> updatePlaceStatus(@RequestParam("placeId") String placeId,@RequestParam("status") String status){
        logger.info("Request Started In updatePlaceStatus ");
        BaseCreatePlaceResponse response = placeManagementService.updatePlaceStatus(placeId,status);
        logger.info("Request Completed In updatePlaceStatus |Response={}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
