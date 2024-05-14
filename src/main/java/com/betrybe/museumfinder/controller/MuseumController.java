package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.CoordinateUtil;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Museum controller.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private MuseumServiceInterface museumService;

  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * New museum response entity.
   *
   * @param museumCreationDto the museum creation dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<MuseumDto> newMuseum(@RequestBody MuseumCreationDto museumCreationDto) {
    Museum newMuseum = ModelDtoConverter.dtoToModel(museumCreationDto);
    Museum savedMuseum = museumService.createMuseum(newMuseum);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ModelDtoConverter.modelToDto(savedMuseum));
  }

  /**
   * Gets closest museums.
   *
   * @param latitude  the latitude
   * @param longitude the longitude
   * @param maxDistKm the max dist km
   * @return the closest museums
   */
  @GetMapping("/closest")
  public ResponseEntity<Object> getClosestMuseums(
      @RequestParam("lat") double latitude,
      @RequestParam("lng") double longitude,
      @RequestParam(value = "max_dist_km") Double maxDistKm
  ) {
    Coordinate coordinate = new Coordinate(latitude, longitude);

    Museum closestMuseum = museumService.getClosestMuseum(coordinate, maxDistKm);

    MuseumDto museumDto = ModelDtoConverter.modelToDto(closestMuseum);

    return ResponseEntity.ok(museumDto);
  }
}
