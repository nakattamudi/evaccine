package com.evaccine.user.repository;

import com.evaccine.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByAadharNumber(final String aadharNumber);
}
