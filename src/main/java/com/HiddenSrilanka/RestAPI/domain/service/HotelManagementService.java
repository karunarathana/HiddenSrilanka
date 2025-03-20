package com.HiddenSrilanka.RestAPI.domain.service;

import com.HiddenSrilanka.RestAPI.application.response.hotel.BaseAllHotelDetails;
import com.HiddenSrilanka.RestAPI.application.response.hotel.BaseCreateHotelResponse;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseAllPlacesDetails;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseCreatePlaceResponse;
import com.HiddenSrilanka.RestAPI.application.response.place.BasePlaceDeleteResponse;
import com.HiddenSrilanka.RestAPI.domain.dto.HotelManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.dto.PlaceManagementDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HotelManagementService {
    BaseCreateHotelResponse createHotel(List<MultipartFile> images, HotelManagementDTO hotelManagementDTO) throws IOException;
    BaseAllHotelDetails getAllHotelDetails();
    BaseAllPlacesDetails getSingleHotelDetailsById(int id);
    BasePlaceDeleteResponse deleteHotelById(int id);
}
