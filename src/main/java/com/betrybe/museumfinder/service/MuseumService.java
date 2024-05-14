package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Museum service.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    return null;
  }

  @Override
  public Museum createMuseum(Museum museum) {
    CoordinateUtil coordinateUtil = new CoordinateUtil();

    // Verificar se as coordenadas são válidas
    if (!coordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException();
    }

    // Chamar o método saveMuseum do bean MuseumFakeDatabase para salvar o objeto Museum
    return museumFakeDatabase.saveMuseum(museum);
  }


  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}
