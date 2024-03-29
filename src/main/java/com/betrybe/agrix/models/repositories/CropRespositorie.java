package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Crop repository.
 */
@Repository
public interface CropRespositorie extends JpaRepository<Crop, Integer> {

  List<Crop> findByFarm(Farm farm);

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
