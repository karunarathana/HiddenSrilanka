package com.HiddenSrilanka.RestAPI.domain.repo;


import com.HiddenSrilanka.RestAPI.domain.model.PlaceManagementEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PlaceManagementRepo extends JpaRepository<PlaceManagementEntity,Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE t_places SET status = :cusStatus WHERE place_id = :placeId", nativeQuery = true)
    void updateStatusByPlaceId(String cusStatus,int placeId);

    @Query(value = "SELECT * FROM t_places WHERE city = :city", nativeQuery = true)
    Optional<ArrayList<PlaceManagementEntity>> getPlaceDetailsByCity(String city);
}
