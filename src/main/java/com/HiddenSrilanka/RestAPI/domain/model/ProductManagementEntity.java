package com.HiddenSrilanka.RestAPI.domain.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="t_products")
public class ProductManagementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;
    private String name;
    private String description;
    private String price;
    private String quantity;
    private String categoryId;
    private String brandId;
    private String createdAt;
    private String updatedAt;
    private String status;

    // One-to-many relationship with PlaceImage
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FkProductId",referencedColumnName = "productId")
    private List<ProductsImagesEntity> productsImagesEntities;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductsImagesEntity> getProductsImagesEntities() {
        return productsImagesEntities;
    }

    public void setProductsImagesEntities(List<ProductsImagesEntity> productsImagesEntities) {
        this.productsImagesEntities = productsImagesEntities;
    }
}
