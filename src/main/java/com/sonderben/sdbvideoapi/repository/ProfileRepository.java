package com.sonderben.sdbvideoapi.repository;


import com.sonderben.sdbvideoapi.entity.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

    @Query(value = "select count(client_id) from profiles where client_id=?1",nativeQuery = true)
    int getNumberOfProfileByIdClient(Long id);







}
