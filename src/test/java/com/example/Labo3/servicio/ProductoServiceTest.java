package com.example.Labo3.servicio;

import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.DaoProducto;
import com.example.Labo3.persistencia.excepciones.ExcepcionCategoriaNula;
import com.example.Labo3.persistencia.excepciones.ExcepcionProductoNulo;
import com.example.Labo3.servicio.implementacion.ServicioProductoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @Mock
    private DaoProducto daoProducto;

    @InjectMocks
    private ServicioProductoImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() throws ExcepcionProductoNulo {
        List<Producto> productoList = new ArrayList<>();
        productoList.add(new Producto("Garrafa", "Una garrafa", "Hogar", "Garrafin", 9999.99, "Gas"));
        productoList.add(new Producto("Mesa", "Una mesa", "Hogar", "Mesita", 3500.0, "Mueble"));

        when(daoProducto.findAllProducts()).thenReturn(productoList);

        List<Producto> result = productService.getAllProducts();

        assertEquals(productoList, result);
        verify(daoProducto, times(1)).findAllProducts();
    }

    @Test
    public void testCreateProduct() throws ExcepcionCategoriaNula {
        Producto producto = new Producto("Mesa", "Una mesa", "Hogar", "Mesita", 3500.0, "Mueble");

        when(daoProducto.createProduct(producto)).thenReturn(producto);

        Producto createProducto = productService.createProduct(producto);

        assertEquals(producto, createProducto);
        verify(daoProducto, times(1)).createProduct(producto);
    }

    @Test
    public void testGetProductById() throws ExcepcionProductoNulo {
        int nonExistentProductId = 999999;
        when(daoProducto.getProductById(nonExistentProductId)).thenThrow(new ExcepcionProductoNulo("There are no products with that id"));

        assertThrows(ExcepcionProductoNulo.class, () -> productService.getProductById(nonExistentProductId));
        verify(daoProducto, times(1)).getProductById(nonExistentProductId);
    }
}
