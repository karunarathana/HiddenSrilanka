package com.HiddenSrilanka.RestAPI.domain.repo;

import com.HiddenSrilanka.RestAPI.domain.model.PlacesImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceImagesRepo extends JpaRepository<PlacesImagesEntity,Integer> {
    @Query(value = "SELECT place_images FROM t_place_images WHERE fk_place_id = :placeId", nativeQuery = true)
    List<byte[]> getAllImageForOnePlace(int placeId);
}
