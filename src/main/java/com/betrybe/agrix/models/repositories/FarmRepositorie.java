package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Farm repository.
 */
@Repository
public interface FarmRepositorie extends JpaRepository<Farm, Integer> {

}
