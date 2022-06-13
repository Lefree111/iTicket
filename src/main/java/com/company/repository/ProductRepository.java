package com.company.repository;

import com.company.entity.ProductEntity;
import com.company.enums.product.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,String> {


    @Transactional
    @Modifying
    @Query("update ProductEntity set status = :status where id = :id")
    int updateStatus(@Param("status") ProductStatus status, @Param("id") String id);

}
