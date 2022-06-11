package com.company.repository;

import com.company.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,String> {

    Optional<ProductEntity> findByName(String name);



}
