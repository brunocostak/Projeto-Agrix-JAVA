package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropResponseDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRespositorie;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**. CropsController
 *
 */
@RestController
@RequestMapping("crops")
public class CropsController {

  private final CropRespositorie cropRespositorie;

  @Autowired
  public CropsController(CropRespositorie cropRespositorie) {
    this.cropRespositorie = cropRespositorie;
  }

  @GetMapping("")
  public ResponseEntity<?> getAllCrops() {
    List<Crop> crops = cropRespositorie.findAll();
    return ResponseEntity.ok(crops.stream().map(CropResponseDto::new).collect(Collectors.toList()));
  }

  /**. getCropById
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getCropById(@PathVariable Integer id) {
    Optional<Crop> optionalCrop = cropRespositorie.findById(id);
    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }
    return ResponseEntity.ok(new CropResponseDto(cropRespositorie.findById(id).get()));
  }

  /**. searchCrops
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropResponseDto>> searchCrops(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

    List<Crop> crops = cropRespositorie.findByHarvestDateBetween(start, end);

    List<CropResponseDto> cropResponseDtos = crops.stream()
        .map(CropResponseDto::new)
        .collect(Collectors.toList());

    return ResponseEntity.ok(cropResponseDtos);
  }

  /**.
   * Associate a crop with a fertilizer.
   */
  @PostMapping("{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropWithFertilizer(@PathVariable Integer cropId,
      @PathVariable Integer fertilizerId) {
    // Verifique se a plantação (crop) com o ID fornecido existe.
    Optional<Crop> optionalCrop = cropRespositorie.findById(cropId);
    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }

    // Verifique se o fertilizante com o ID fornecido existe.
    Optional<Crop> optionalFertilizer = cropRespositorie.findById(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }

    // Associe o fertilizante à plantação.
    Crop crop = optionalCrop.get();
    Crop fertilizer = optionalFertilizer.get();

    // Certifique-se de que a classe Crop tenha um método para associar fertilizantes.
    crop.addFertilizer(fertilizer);

    // Salve a plantação atualizada.
    cropRespositorie.save(crop);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**.
   * Get all fertilizers associated with a crop.
   */
  @GetMapping("{cropId}/fertilizers")
  public ResponseEntity<?> getFertilizersByCrop(@PathVariable Integer cropId) {
    Optional<Crop> optionalCrop = cropRespositorie.findById(cropId);
    if (optionalCrop.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
    }
    Crop crop = optionalCrop.get();
    List<Crop> fertilizers = crop.getFertilizers();
    return ResponseEntity.ok(fertilizers.stream().map(CropResponseDto::new)
        .collect(Collectors.toList()));
  }
}
