package com.company.repository;

import com.company.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantEntity, String> {
    Optional<MerchantEntity> findByAddress(String address);
}