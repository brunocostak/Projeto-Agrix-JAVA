package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/** DTO for FertilizerRepositorie. */
public record FertilizerRepositorieDto(int id, String name, String brand, String composition) {

  public FertilizerRepositorieDto(Fertilizer fertilizer) {
    this(fertilizer.getId(), fertilizer.getName(),
        fertilizer.getBrand(), fertilizer.getComposition());
  }

}
