package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Garage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Garage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GarageRepository extends JpaRepository<Garage,Long> {
    
}
