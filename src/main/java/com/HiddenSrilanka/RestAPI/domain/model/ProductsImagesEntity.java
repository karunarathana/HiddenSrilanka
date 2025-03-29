package com.HiddenSrilanka.RestAPI.domain.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="t_product_images")
@Component
public class ProductsImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;
    @Lob
    @Column(name="productImages",length = 1000000)
    private byte[] ProductImageData;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public byte[] getProductImageData() {
        return ProductImageData;
    }

    public void setProductImageData(byte[] productImageData) {
        ProductImageData = productImageData;
    }
}
