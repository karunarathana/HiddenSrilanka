package com.HiddenSrilanka.RestAPI.domain.service.impl;

import com.HiddenSrilanka.RestAPI.application.response.hotel.AllHotelResponse;
import com.HiddenSrilanka.RestAPI.application.response.hotel.BaseAllHotelDetails;
import com.HiddenSrilanka.RestAPI.application.response.hotel.BaseCreateHotelResponse;
import com.HiddenSrilanka.RestAPI.application.response.place.AllPlaceResponse;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseAllPlacesDetails;
import com.HiddenSrilanka.RestAPI.application.response.place.BasePlaceDeleteResponse;
import com.HiddenSrilanka.RestAPI.domain.dto.HotelManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.model.HotelImagesEntity;
import com.HiddenSrilanka.RestAPI.domain.model.HotelManagementEntity;
import com.HiddenSrilanka.RestAPI.domain.repo.HotelImagesRepo;
import com.HiddenSrilanka.RestAPI.domain.repo.HotelManagementRepo;
import com.HiddenSrilanka.RestAPI.domain.service.HotelManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelManagementServiceImpl implements HotelManagementService {
    private static final Logger logger = LoggerFactory.getLogger(HotelManagementServiceImpl.class);
    private final HotelManagementRepo hotelManagementRepo;
    private final HotelImagesRepo hotelImagesRepo;

    public HotelManagementServiceImpl(HotelManagementRepo hotelManagementRepo, HotelImagesRepo hotelImagesRepo) {
        this.hotelManagementRepo = hotelManagementRepo;
        this.hotelImagesRepo = hotelImagesRepo;
    }


    @Override
    public BaseCreateHotelResponse createHotel(List<MultipartFile> images, HotelManagementDTO hotelManagementDTO) throws IOException {
        logger.info("Method Execution Started In createHotel |Images={} |HotelDetails={}",images,hotelManagementDTO);
        hotelManagementRepo.save(setValuePlaceManagementEntity(images,hotelManagementDTO));
        BaseCreateHotelResponse baseCreatePlaceResponse = new BaseCreateHotelResponse();
        baseCreatePlaceResponse.setStatusCode("200");
        baseCreatePlaceResponse.setMessage("Operation Successfully");
        logger.info("Method Execution Completed In createHotel");
        return baseCreatePlaceResponse;
    }
    public HotelManagementEntity setValuePlaceManagementEntity(List<MultipartFile> images, HotelManagementDTO hotelManagementDTO) throws IOException {
        logger.info("Method Execution Started In setValueHotelManagementEntity");
        HotelManagementEntity hotelManagementEntity = new HotelManagementEntity();
        List<HotelImagesEntity> hotelImagesEntities = new ArrayList<>();

        hotelManagementEntity.setCity(hotelManagementDTO.getCity());
        hotelManagementEntity.setCountry(hotelManagementDTO.getCountry());
        hotelManagementEntity.setHotelName(hotelManagementDTO.getHotelName());
        hotelManagementEntity.setHotelAddress(hotelManagementDTO.getAddress());
        hotelManagementEntity.setDescription(hotelManagementDTO.getDescription());
        hotelManagementEntity.setLatitude(hotelManagementDTO.getLatitude());
        hotelManagementEntity.setLongitude(hotelManagementDTO.getLongitude());
        hotelManagementEntity.setPriceRange(hotelManagementDTO.getPriceRange());
        hotelManagementEntity.setContactNumber(hotelManagementDTO.getContactNumber());
        hotelManagementEntity.setCreateUser(hotelManagementDTO.getCreateUser());

        for (MultipartFile data :images){
            HotelImagesEntity hotelImagesEntity = new HotelImagesEntity();
            hotelImagesEntity.setHotelImageData(data.getBytes());
            hotelImagesEntities.add(hotelImagesEntity);
        }
        hotelManagementEntity.setHotelImagesEntities(hotelImagesEntities);
        logger.info("Method Execution Completed In setValueHotelManagementEntity");
        return hotelManagementEntity;
    }

    @Override
    public BaseAllHotelDetails getAllHotelDetails() {
        logger.info("Method Execution Started In getAllPlaceDetails");
        List<HotelManagementEntity> allHotels = hotelManagementRepo.findAll();
        List<AllHotelResponse> data = new ArrayList<>();
        for (HotelManagementEntity hotel : allHotels) {
            AllHotelResponse allHotelResponse = new AllHotelResponse();
            allHotelResponse.setHotelId(hotel.getHotelId());
            allHotelResponse.setCity(hotel.getCity());
            allHotelResponse.setCountry(hotel.getCountry());
            allHotelResponse.setHotelName(hotel.getHotelName());
            allHotelResponse.setAddress(hotel.getHotelAddress());
            allHotelResponse.setDescription(hotel.getDescription());
            allHotelResponse.setLatitude(hotel.getLatitude());
            allHotelResponse.setLongitude(hotel.getLongitude());
            allHotelResponse.setPriceRange(hotel.getPriceRange());
            List<byte[]> allImageForOneHotels = hotelImagesRepo.getAllImageForOneHotels(hotel.getHotelId());
            allHotelResponse.setImages(allImageForOneHotels);
            data.add(allHotelResponse);
        }
        BaseAllHotelDetails baseAllHotelDetails = new BaseAllHotelDetails();
        baseAllHotelDetails.setStatusCode("200");
        baseAllHotelDetails.setMessage("Operation Successfully");
        baseAllHotelDetails.setData(data);
        logger.info("Method Execution Completed In getAllPlaceDetails");
        return baseAllHotelDetails;
    }

    @Override
    public BaseAllPlacesDetails getSingleHotelDetailsById(int id) {
        return null;
    }

    @Override
    public BasePlaceDeleteResponse deleteHotelById(int id) {
        return null;
    }
}
