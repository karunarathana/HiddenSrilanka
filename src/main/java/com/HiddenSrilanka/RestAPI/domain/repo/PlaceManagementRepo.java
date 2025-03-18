package com.HiddenSrilanka.RestAPI.domain.repo;


import com.HiddenSrilanka.RestAPI.domain.model.PlaceManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceManagementRepo extends JpaRepository<PlaceManagementEntity,Integer> {
}
