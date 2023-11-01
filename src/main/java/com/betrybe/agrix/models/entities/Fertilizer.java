package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Fertilizer entity.
 */
@Entity
@Table(name = "fertilizers")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private int id;
  private String name;

  private String brand;

  private String composition;

  @ManyToMany
  private List<Crop> crops;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }

  public String getBrand() {
    return brand;
  }

}
