package com.HiddenSrilanka.RestAPI.domain.service.impl;

import com.HiddenSrilanka.RestAPI.application.response.place.AllPlacesResponse;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseAllPlacesDetails;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseCreatePlaceResponse;
import com.HiddenSrilanka.RestAPI.domain.dto.PlaceManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.model.PlaceManagementEntity;
import com.HiddenSrilanka.RestAPI.domain.model.PlacesImagesEntity;
import com.HiddenSrilanka.RestAPI.domain.repo.PlaceImagesRepo;
import com.HiddenSrilanka.RestAPI.domain.repo.PlaceManagementRepo;
import com.HiddenSrilanka.RestAPI.domain.service.PlaceManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceManagementServiceImpl implements PlaceManagementService {
    private static final Logger logger = LoggerFactory.getLogger(PlaceManagementServiceImpl.class);
    private final PlaceManagementRepo placeManagementRepo;
    private final PlaceImagesRepo placeImagesRepo;

    public PlaceManagementServiceImpl(PlaceManagementRepo placeManagementRepo, PlacesImagesEntity placesImagesEntity, PlaceImagesRepo placeImagesRepo, PlaceImagesRepo placeImagesRepo1) {
        this.placeManagementRepo = placeManagementRepo;
        this.placeImagesRepo = placeImagesRepo1;
    }

    @Override
    public BaseCreatePlaceResponse createPlace(List<MultipartFile> images, PlaceManagementDTO placeManagementDTO) throws IOException {
        logger.info("Method Execution Started In createPlace |Images={} |PlaceDetails={}",images,placeManagementDTO);
        placeManagementRepo.save(setValuePlaceManagementEntity(images,placeManagementDTO));
        BaseCreatePlaceResponse baseCreatePlaceResponse = new BaseCreatePlaceResponse();
        baseCreatePlaceResponse.setStatusCode("200");
        baseCreatePlaceResponse.setMessage("Operation Successfully");
        logger.info("Method Execution Completed In createPlace");
        return baseCreatePlaceResponse;

    }
    public PlaceManagementEntity setValuePlaceManagementEntity(List<MultipartFile> images, PlaceManagementDTO placeManagementDTO) throws IOException {
        logger.info("Method Execution Started In setValuePlaceManagementEntity");
        PlaceManagementEntity placeManagementEntity = new PlaceManagementEntity();
        List<PlacesImagesEntity> placeImagesEntity = new ArrayList<>();

        placeManagementEntity.setCity(placeManagementDTO.getCity());
        placeManagementEntity.setCountry(placeManagementDTO.getCountry());
        placeManagementEntity.setPlaceName(placeManagementDTO.getPlaceName());
        placeManagementEntity.setPlaceAddress(placeManagementDTO.getPlaceAddress());
        placeManagementEntity.setDescription(placeManagementDTO.getDescription());
        placeManagementEntity.setLatitude(placeManagementDTO.getLatitude());
        placeManagementEntity.setLongitude(placeManagementDTO.getLongitude());
        placeManagementEntity.setPriceRange(placeManagementDTO.getPriceRange());

        for (MultipartFile data :images){
            PlacesImagesEntity placesImagesEntity = new PlacesImagesEntity();
            placesImagesEntity.setPlaceImageData(data.getBytes());
            placeImagesEntity.add(placesImagesEntity);
        }
        placeManagementEntity.setPlacesImagesEntities(placeImagesEntity);
        logger.info("Method Execution Completed In setValuePlaceManagementEntity");
        return placeManagementEntity;
    }
    public BaseAllPlacesDetails getAllPlaceDetails(){
        logger.info("Method Execution Started In getAllPlaceDetails");
        List<PlaceManagementEntity> allPlaces = placeManagementRepo.findAll();
        List<AllPlacesResponse> data = new ArrayList<>();
        for (PlaceManagementEntity place : allPlaces) {
            AllPlacesResponse allPlacesResponse = new AllPlacesResponse();
            allPlacesResponse.setId(place.getPlaceId());
            allPlacesResponse.setCity(place.getCity());
            allPlacesResponse.setCountry(place.getCountry());
            allPlacesResponse.setPlaceName(place.getPlaceName());
            allPlacesResponse.setPlaceAddress(place.getPlaceAddress());
            allPlacesResponse.setDescription(place.getDescription());
            allPlacesResponse.setLatitude(place.getLatitude());
            allPlacesResponse.setLongitude(place.getLongitude());
            allPlacesResponse.setPriceRange(place.getPriceRange());
            List<byte[]> allImageForOnePlace = placeImagesRepo.getAllImageForOnePlace(place.getPlaceId());
            allPlacesResponse.setImages(allImageForOnePlace);
            data.add(allPlacesResponse);
        }
        BaseAllPlacesDetails baseAllPlacesDetails = new BaseAllPlacesDetails();
        baseAllPlacesDetails.setStatusCode("200");
        baseAllPlacesDetails.setMessage("Operation Successfully");
        baseAllPlacesDetails.setData(data);
        logger.info("Method Execution Completed In getAllPlaceDetails");
        return baseAllPlacesDetails;
    }
    public BaseAllPlacesDetails getSinglePlaceDetailsById(int id){
        logger.info("Method Execution Started In getSinglePlaceDetailsById |PlaceID={}",id);
        Optional<PlaceManagementEntity> byId = placeManagementRepo.findById(id);
        BaseAllPlacesDetails baseAllPlacesDetails = new BaseAllPlacesDetails();
        if(byId.isPresent()){
            List<AllPlacesResponse> data = new ArrayList<>();
            AllPlacesResponse allPlacesResponse = new AllPlacesResponse();
            allPlacesResponse.setId(byId.get().getPlaceId());
            allPlacesResponse.setCity(byId.get().getCity());
            allPlacesResponse.setCountry(byId.get().getCountry());
            allPlacesResponse.setPlaceName(byId.get().getPlaceName());
            allPlacesResponse.setPlaceAddress(byId.get().getPlaceAddress());
            allPlacesResponse.setDescription(byId.get().getDescription());
            allPlacesResponse.setLatitude(byId.get().getLatitude());
            allPlacesResponse.setLongitude(byId.get().getLongitude());
            allPlacesResponse.setPriceRange(byId.get().getPriceRange());
            List<byte[]> allImageForOnePlace = placeImagesRepo.getAllImageForOnePlace(byId.get().getPlaceId());
            allPlacesResponse.setImages(allImageForOnePlace);
            data.add(allPlacesResponse);

            baseAllPlacesDetails.setStatusCode("200");
            baseAllPlacesDetails.setMessage("Operation Successfully");
            baseAllPlacesDetails.setData(data);
            return baseAllPlacesDetails;
        }
        baseAllPlacesDetails.setStatusCode("200");
        baseAllPlacesDetails.setMessage("Operation Successfully");
        baseAllPlacesDetails.setData(null);
        logger.info("Method Execution Completed In getSinglePlaceDetailsById");
        return baseAllPlacesDetails;
    }
}
