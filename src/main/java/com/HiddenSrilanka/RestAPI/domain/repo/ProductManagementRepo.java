package com.HiddenSrilanka.RestAPI.domain.repo;

import com.HiddenSrilanka.RestAPI.domain.model.ProductManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductManagementRepo  extends JpaRepository<ProductManagementEntity,Integer> {

}
