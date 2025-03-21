package com.HiddenSrilanka.RestAPI.domain.repo;

import com.HiddenSrilanka.RestAPI.domain.model.HotelImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelImagesRepo extends JpaRepository<HotelImagesEntity,Integer> {
    @Query(value = "SELECT hotel_images FROM t_hotel_images WHERE fk_hotel_id = :hotelId", nativeQuery = true)
    List<byte[]> getAllImageForOneHotels(int hotelId);
}
