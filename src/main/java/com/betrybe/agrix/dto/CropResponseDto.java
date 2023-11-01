package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/** DTO for Crop. */
public record CropResponseDto(int id, String name, Double plantedArea, Integer farmId,
                              LocalDate plantedDate,
                              LocalDate harvestDate) {

  public CropResponseDto(Crop newCrop) {
    this(newCrop.getId(), newCrop.getName(), newCrop.getPlantedArea(), newCrop.getFarm(),
        newCrop.getPlantedDate(), newCrop.getHarvestDate());
  }
}
