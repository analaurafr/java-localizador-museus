package com.betrybe.museumfinder.solution;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CollectionTypeService collectionTypeService;

  @BeforeEach
  public void setUp() {
    // Configurar comportamento do mock do CollectionTypeService
    Mockito.when(collectionTypeService.countByCollectionTypes(anyString()))
        .thenReturn(new CollectionTypeCount(new String[]{"hist", "imag"}, 492));
  }

  @Test
  @DisplayName("Testar rota de contagem de tipos de coleção")
  public void testCountCollectionTypes() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/collections/count/hist,imag")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.collectionTypes").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(492));
  }
}
