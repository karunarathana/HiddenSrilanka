package com.HiddenSrilanka.RestAPI.domain.service.impl;

import com.HiddenSrilanka.RestAPI.application.response.place.BaseAllPlacesDetails;
import com.HiddenSrilanka.RestAPI.application.response.place.BaseCreatePlaceResponse;
import com.HiddenSrilanka.RestAPI.application.response.product.AllProductResponse;
import com.HiddenSrilanka.RestAPI.application.response.product.BaseAllProductDetails;
import com.HiddenSrilanka.RestAPI.domain.dto.ProductManagementDTO;
import com.HiddenSrilanka.RestAPI.domain.model.ProductManagementEntity;
import com.HiddenSrilanka.RestAPI.domain.model.ProductsImagesEntity;
import com.HiddenSrilanka.RestAPI.domain.repo.ProductImageRepo;
import com.HiddenSrilanka.RestAPI.domain.repo.ProductManagementRepo;
import com.HiddenSrilanka.RestAPI.domain.service.ProductManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {
    private static final Logger logger = LoggerFactory.getLogger(ProductManagementServiceImpl.class);
    private final ProductManagementRepo productManagementRepo;
    private final ProductImageRepo productImageRepo;


    public ProductManagementServiceImpl(ProductManagementRepo productManagementRepo, ProductImageRepo productImageRepo) {
        this.productManagementRepo = productManagementRepo;
        this.productImageRepo = productImageRepo;
    }


    private ProductManagementEntity setValueProductManagementEntity(List<MultipartFile> images, ProductManagementDTO productManagementDTO) throws IOException {
        logger.info("Method Execution Started In setValueProductManagementEntity");
        ProductManagementEntity productManagementEntity = new ProductManagementEntity();
        List<ProductsImagesEntity> placeImagesEntity = new ArrayList<>();

        productManagementEntity.setName(productManagementDTO.getName());
        productManagementEntity.setDescription(productManagementDTO.getDescription());
        productManagementEntity.setPrice(productManagementDTO.getPrice());
        productManagementEntity.setQuantity(productManagementDTO.getQuantity());
        productManagementEntity.setBrandId(productManagementDTO.getBrandId());
        productManagementEntity.setCategoryId(productManagementDTO.getCategoryId());
        productManagementEntity.setCreatedAt(productManagementDTO.getCreatedAt());
        productManagementEntity.setUpdatedAt(productManagementDTO.getUpdatedAt());
        productManagementEntity.setStatus(productManagementDTO.getStatus());

        for (MultipartFile data :images){
            ProductsImagesEntity productsImages = new ProductsImagesEntity();
            productsImages.setProductImageData(data.getBytes());
            placeImagesEntity.add(productsImages);
        }
        productManagementEntity.setProductsImagesEntities(placeImagesEntity);
        logger.info("Method Execution Completed In setValuePlaceManagementEntity");
        return productManagementEntity;
    }

    @Override
    public BaseCreatePlaceResponse createProduct(List<MultipartFile> images, ProductManagementDTO productManagementDTO) throws IOException {
        logger.info("Method Execution Started In createProduct |Images={} |PlaceDetails={}",images,productManagementDTO);
        productManagementRepo.save(setValueProductManagementEntity(images,productManagementDTO));
        BaseCreatePlaceResponse baseCreatePlaceResponse = new BaseCreatePlaceResponse();
        baseCreatePlaceResponse.setStatusCode("200");
        baseCreatePlaceResponse.setMessage("Operation Successfully");
        logger.info("Method Execution Completed In createProduct");
        return baseCreatePlaceResponse;
    }

    @Override
    public BaseAllProductDetails getAllProductDetails() {
        logger.info("Method Execution Started In getAllProductDetails");
        List<ProductManagementEntity> allProduct = productManagementRepo.findAll();
        List<AllProductResponse> data = new ArrayList<>();
        for (ProductManagementEntity product : allProduct) {
            AllProductResponse allProductResponse = new AllProductResponse();
            allProductResponse.setId(String.valueOf(product.getProductId()));
            allProductResponse.setDescription(product.getDescription());
            allProductResponse.setName(product.getName());
            allProductResponse.setBrandId(product.getBrandId());
            allProductResponse.setCreatedAt(product.getCreatedAt());
            allProductResponse.setQuantity(product.getQuantity());
            allProductResponse.setStatus(product.getStatus());

            List<byte[]> allImageForOneProduct = productImageRepo.getAllImageForOneProduct(product.getProductId());
            allProductResponse.setImages(allImageForOneProduct);
            data.add(allProductResponse);
        }
        BaseAllProductDetails baseAllProductDetails = new BaseAllProductDetails();
        baseAllProductDetails.setStatusCode("200");
        baseAllProductDetails.setMessage("Operation Successfully");
        baseAllProductDetails.setData(data);
        logger.info("Method Execution Completed In getAllProductDetails");
        return baseAllProductDetails;
    }


}
