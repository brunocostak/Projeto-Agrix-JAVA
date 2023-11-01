package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Fertilizer repository.
 */
@Repository
public interface FertilizerRepositorie extends JpaRepository<Fertilizer, Integer> {

}
