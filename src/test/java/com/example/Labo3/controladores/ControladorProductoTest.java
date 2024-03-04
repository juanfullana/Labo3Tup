package com.example.Labo3.controladores;

import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.excepciones.ExcepcionCategoriaNula;
import com.example.Labo3.persistencia.excepciones.ExcepcionProductoNulo;
import com.example.Labo3.servicio.ServicioProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ControladorProductoTest {
    @Mock
    private ServicioProducto servicioProducto;

    @InjectMocks
    private ControladorProducto controladorProducto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getProductByIdTest() throws ExcepcionProductoNulo {
        Producto producto = new Producto("Garrafa", "Una garrafa", "Hogar", "Garrafin", 9999.99, "Gas");
        when(servicioProducto.getProductById(1)).thenReturn(producto);

        Producto result = controladorProducto.getProductById(1);

        assertEquals(producto, result);
    }

    @Test
    public void deleteProductTest() throws ExcepcionProductoNulo {
        when(servicioProducto.deleteProduct(1)).thenReturn(true);

        Boolean result = controladorProducto.deleteProduct(1);

        assertTrue(result);
    }

    @Test
    public void deleteProductException() throws ExcepcionProductoNulo {
        when(servicioProducto.deleteProduct(anyInt())).thenThrow(new ExcepcionProductoNulo("No products with that id, please try again"));

        assertThrows(ExcepcionProductoNulo.class, () -> {
            controladorProducto.deleteProduct(1);
        });

        verify(servicioProducto, times(1)).deleteProduct(1);
    }

    @Test
    public void createProductTest() throws ExcepcionCategoriaNula {
        Producto producto = new Producto("Garrafa", "Una garrafa", "Hogar", "Garrafin", 9999.99, "Gas");
        when(servicioProducto.createProduct(producto)).thenReturn(producto);

        Producto result = controladorProducto.createProduct(producto);

        assertEquals(producto, result);
    }
}