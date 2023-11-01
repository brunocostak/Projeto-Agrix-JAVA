package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropResponseDto;
import com.betrybe.agrix.dto.FarmResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRespositorie;
import com.betrybe.agrix.models.repositories.FarmRepositorie;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Farm Controller. */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmRepositorie farmRepositorie;
  private final CropRespositorie cropRespositorie;

  @Autowired
  public FarmController(FarmRepositorie farmRepositorie, CropRespositorie cropRespositorie) {
    this.farmRepositorie = farmRepositorie;
    this.cropRespositorie = cropRespositorie;
  }

  @PostMapping("")
  public ResponseEntity<FarmResponseDto> createFarm(@RequestBody Farm farm) {
    Farm newFarm = farmRepositorie.save(farm);
    return ResponseEntity.status(HttpStatus.CREATED).body(new FarmResponseDto(newFarm));
  }

  @GetMapping("")
  public List<FarmResponseDto> getFarm() {
    List<Farm> farms = farmRepositorie.findAll();
    return farms.stream().map(FarmResponseDto::new).collect(Collectors.toList());
  }

  // @GetMapping("/{id}")
  /** Get Farm by Id. */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarmById(@PathVariable Integer id) {
    Optional<Farm> optionalFarm = farmRepositorie.findById(id);

    if (optionalFarm.isPresent()) {
      Farm farm = optionalFarm.get();
      return ResponseEntity.ok(new FarmResponseDto(farm));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }

  /** Create Crop. */
  @PostMapping("/{id}/crops")
  public ResponseEntity<?> createCrop(@PathVariable Integer id, @RequestBody Crop crop) {
    Optional<Farm> optionalFarm = farmRepositorie.findById(id); // Correção: era farmRepositorie

    if (optionalFarm.isPresent()) {
      Farm farm = optionalFarm.get();
      crop.setFarm(farm);
      Crop newCrop = cropRespositorie.save(crop); // Correção: era cropRespositorie
      return ResponseEntity.status(HttpStatus.CREATED).body(new CropResponseDto(newCrop));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }

  /** Get Crops by Farm. */
  @GetMapping("/{id}/crops")
  public ResponseEntity<?> getCropsByFarm(@PathVariable int id) {
    Optional<Farm> optionalFarm = farmRepositorie.findById(id);

    if (optionalFarm.isPresent()) {
      Farm farm = optionalFarm.get();
      List<Crop> crops = cropRespositorie.findByFarm(farm);
      return ResponseEntity.ok(crops.stream().map(CropResponseDto::new)
          .collect(Collectors.toList()));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }


}
