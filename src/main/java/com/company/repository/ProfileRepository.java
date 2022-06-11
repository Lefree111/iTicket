package com.company.repository;

import com.company.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByPhone(String pohone);

    Optional<ProfileEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set visible = :visible where id = :id")
    int delete(@Param("visible") boolean b, @Param("id") String id);

    Optional<ProfileEntity> findByEmailAndPassword(String email, String password);


}
