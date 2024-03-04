package com.example.Labo3.controladores;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.servicio.ServicioCategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ControladorCategoriaTest {

    @Mock
    private ServicioCategoria servicioCategoria;

    @InjectMocks
    private ControladorCategoria controladorCategoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories_NoCategories() {
        when(servicioCategoria.getAllCategories()).thenReturn(new ArrayList<>());

        ResponseEntity<Object> responseEntity = controladorCategoria.getCategorias();

        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("data", new ArrayList<>());
        expectedBody.put("message", "No categories found.");
        expectedBody.put("status", HttpStatus.NO_CONTENT.value());
        assertEquals(expectedBody, responseEntity.getBody());
    }

    @Test
    public void testGetAllCategories_WithCategories() {
        List<Categoria> categories = new ArrayList<>();
        categories.add(new Categoria(1, "Category 1", "Description 1"));
        when(servicioCategoria.getAllCategories()).thenReturn(categories);

        ResponseEntity<Object> responseEntity = controladorCategoria.getCategorias();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
        assertNotNull(responseBody);

        assertEquals("Categories retrieved successfully.", responseBody.get("message"));

        List<Categoria> retrievedCategories = (List<Categoria>) responseBody.get("data");
        assertNotNull(retrievedCategories);
        assertEquals(categories.size(), retrievedCategories.size());
        assertEquals(categories.get(0).getId(), retrievedCategories.get(0).getId());
        assertEquals(categories.get(0).getName(), retrievedCategories.get(0).getName());
        assertEquals(categories.get(0).getDescription(), retrievedCategories.get(0).getDescription());
    }
}
