package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerRepositorieDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepositorie;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**. FertilizerController
 *
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerRepositorie fertilizerRepositorie;

  public FertilizerController(FertilizerRepositorie fertilizerRepositorie) {
    this.fertilizerRepositorie = fertilizerRepositorie;
  }

  /**. getFertilizers
   */
  @PostMapping()
  public ResponseEntity<?> getFertilizers(@RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerRepositorie.save(fertilizer);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new FertilizerRepositorieDto(newFertilizer));
  }

  /**. getAllFertilizers
   */
  @GetMapping("")
  public ResponseEntity<List<FertilizerRepositorieDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerRepositorie.findAll();
    List<FertilizerRepositorieDto> fertilizerDtos = fertilizers.stream()
        .map(FertilizerRepositorieDto::new)
        .collect(Collectors.toList());
    return ResponseEntity.ok(fertilizerDtos);
  }

  /**. getFertilizer
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<?> getFertilizer(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> optionalFertilizer = fertilizerRepositorie.findById(
        Math.toIntExact(fertilizerId));

    if (optionalFertilizer.isPresent()) {
      Fertilizer fertilizer = optionalFertilizer.get();
      FertilizerRepositorieDto fertilizerDto = new FertilizerRepositorieDto(fertilizer);
      return ResponseEntity.ok(fertilizerDto);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
    }
  }
}
