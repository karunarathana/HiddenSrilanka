package com.HiddenSrilanka.RestAPI.domain.service;

import com.HiddenSrilanka.RestAPI.application.response.place.BaseCreatePlaceResponse;
import com.HiddenSrilanka.RestAPI.application.response.product.BaseAllProductDetails;
import com.HiddenSrilanka.RestAPI.domain.dto.ProductManagementDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductManagementService {
    BaseCreatePlaceResponse createProduct(List<MultipartFile> images, ProductManagementDTO productManagementDTO) throws IOException;
    BaseAllProductDetails getAllProductDetails();
}
