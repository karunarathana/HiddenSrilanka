package com.HiddenSrilanka.RestAPI.domain.repo;

import com.HiddenSrilanka.RestAPI.domain.model.HotelManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelManagementRepo extends JpaRepository<HotelManagementEntity,Integer> {
}
