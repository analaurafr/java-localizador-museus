package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CollectionServiceTest {

  @Autowired
  CollectionTypeService collectionTypeService;

  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Testar contagem de tipos de coleção quando não há correspondência")
  public void testCountByCollectionTypes_NoMatch() {
    // Configuração do mock do MuseumFakeDatabase
    when(museumFakeDatabase.countByCollectionType(anyString())).thenReturn(0L);

    // Chamar o método do service
    CollectionTypeCount result = collectionTypeService.countByCollectionTypes("hist,imag");

    // Verificar se o resultado está correto
    assertEquals(2, result.collectionTypes().length);
    assertEquals(0, result.count());
  }

  @Test
  @DisplayName("Testar contagem de tipos de coleção quando há apenas um tipo")
  public void testCountByCollectionTypes_SingleType() {
    // Configuração do mock do MuseumFakeDatabase
    when(museumFakeDatabase.countByCollectionType(anyString())).thenReturn(50L);

    // Chamar o método do service
    CollectionTypeCount result = collectionTypeService.countByCollectionTypes("hist");

    // Verificar se o resultado está correto
    assertEquals(1, result.collectionTypes().length);
    assertEquals(50, result.count());
  }
}
