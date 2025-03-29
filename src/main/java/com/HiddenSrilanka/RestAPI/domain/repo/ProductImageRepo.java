package com.HiddenSrilanka.RestAPI.domain.repo;

import com.HiddenSrilanka.RestAPI.domain.model.ProductsImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductsImagesEntity,Integer> {
    @Query(value = "SELECT product_images FROM t_product_images WHERE fk_product_id = :productId", nativeQuery = true)
    List<byte[]> getAllImageForOneProduct(int productId);
}