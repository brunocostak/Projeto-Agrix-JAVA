package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Farm;

/** DTO for Farm. */
public record FarmResponseDto(int id, String name, Double size) {

  /** Constructor. */
  public FarmResponseDto(Farm farm) {
    this(farm.getId(), farm.getName(), farm.getSize());
  }
}
