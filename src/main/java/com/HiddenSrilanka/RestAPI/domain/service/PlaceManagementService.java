package com.HiddenSrilanka.RestAPI.domain.service;

import com.HiddenSrilanka.RestAPI.application.response.place.BaseAllPlacesDetails;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseCreatePlaceResponse;
import com.HiddenSrilanka.RestAPI.application.response.place.BasePlaceDeleteResponse;
import com.HiddenSrilanka.RestAPI.domain.dto.PlaceManagementDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlaceManagementService {
    BaseCreatePlaceResponse createPlace(List<MultipartFile> images, PlaceManagementDTO placeManagementDTO) throws IOException;
    BaseAllPlacesDetails getAllPlaceDetails();
    BaseAllPlacesDetails getSinglePlaceDetailsById(int id);
    BasePlaceDeleteResponse deletePlaceById(int id);
}
