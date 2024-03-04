package com.example.Labo3.persistencia;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.excepciones.ExcepcionCategoriaNula;
import com.example.Labo3.persistencia.implementacion.ImplementacionDaoProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductoDaoImplTest {
    private ImplementacionDaoProducto productDao;

    @Mock
    private DaoCategoria daoCategoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productDao = new ImplementacionDaoProducto(daoCategoria);
    }

    @Test
    void createProductTest_NoCategories() {
        when(daoCategoria.findAllCategory()).thenReturn(new ArrayList<>());
        Producto producto = new Producto("Garrafa", "Una garrafa", "Hogar", "Garrafin", 9999.99, "Gas");

        assertThrows(ExcepcionCategoriaNula.class, () -> productDao.createProduct(producto));
    }

    @Test
    void createProductTest_WithCategories() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(1, "Hogar", "Productos para el hogar"));
        when(daoCategoria.findAllCategory()).thenReturn(categorias);

        Producto producto = new Producto("Mesa", "Una mesa de madera", "Hogar", "Madera", 199.99, "Muebles");

        Producto productoCreado = null;
        try {
            productoCreado = productDao.createProduct(producto);
        } catch (ExcepcionCategoriaNula e) {
            fail("No se esperaba una excepción de categoría nula.");
        }

        assertNotNull(productoCreado);
        assertEquals(producto, productoCreado);
        assertTrue(productDao.findAllProducts().contains(productoCreado));
    }
}