package com.betrybe.museumfinder.dto;

/**
 * The type Museum creation dto.
 */
public record MuseumCreationDto(
    String name,
    String description,
    String address,
    String collectionType,
    String subject,
    String url,
    com.betrybe.museumfinder.model.Coordinate coordinate
) {

}
