package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Long> {

    Administrator findAdministratorByEmailAndPassword(String email, String password);
    Administrator findAdministratorByEmail(String email);
}
