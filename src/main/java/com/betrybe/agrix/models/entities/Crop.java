package com.betrybe.agrix.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Crop entity.
 */
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(name = "planted_area")
  private Double plantedArea;

  @Column(name = "planted_date")
  private LocalDate plantedDate;


  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  @ManyToMany
  @JoinTable(name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id"))
  private List<Crop> fertilizers;

  public double getPlantedArea() {
    return plantedArea;
  }

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getFarm() {
    return farm.getId();
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Crop> getFertilizers() {
    return fertilizers;
  }

  public void addFertilizer(Crop fertilizer) {
    fertilizers.add(fertilizer);
  }
}
