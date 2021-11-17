package com.sonderben.sdbvideoapi.repository;

import com.sonderben.sdbvideoapi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query(value = "select type_access.num_of_screen from type_access " +
            "inner join Clients on type_access.id =Clients.access_id " +
            "where clients.id=?1",nativeQuery = true)
    int getNumberOfScreenByIdClient(Long id);

    @Query(value = "select all_profiles_can_create_new_profile from clients " +
            "where clients.id=(select client_id from profiles where profiles.id=?1)",nativeQuery = true)
    Boolean isAllProfilesCanCreateNewProfile(Long ProfileId);

    @Query(value = "select *from clients " +
            "where clients.id=(select client_id from profiles where profiles.id=?1)",nativeQuery = true)
    Client getClientByProfileId(Long ProfileId);


    Client findClientByEmailAndPassword(String email, String password);
    Client findClientByEmail(String email);
}
