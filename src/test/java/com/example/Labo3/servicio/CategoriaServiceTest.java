package com.example.Labo3.servicio;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.DaoCategoria;
import com.example.Labo3.servicio.implementacion.ServicioCategoriaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoriaServiceTest {
    @Mock
    private DaoCategoria daoCategoria;

    @InjectMocks
    private ServicioCategoriaImpl categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCategoriesTest() {
        List<Categoria> categories = new ArrayList<>();
        categories.add(new Categoria(0, "Categoria 0", "Categoria 0"));
        categories.add(new Categoria(1, "Categoria 1", "Categoria 1"));

        when(daoCategoria.findAllCategory()).thenReturn(categories);

        List<Categoria> result = categoryService.getAllCategories();

        assertEquals(categories.size(), result.size());
        for (int i = 0; i < categories.size(); i++){
            assertEquals(categories.get(i), result.get(i));
        }
    }

    @Test
    public void testOrderCategoryProductsByPriceRange() {
        Categoria categoria1 = new Categoria(1, "Hogar", "Hogar");
        Categoria categoria2 = new Categoria(2, "NoHogar", "NoHogar");

        Producto producto1 = new Producto("Garrafa", "Una garrafa", "Hogar", "Garrafin", 9999.99, "Gas");
        Producto producto2 = new Producto("Mesa", "Una mesa de madera", "Hogar", "Madera", 199.99, "Muebles");
        Producto producto3 = new Producto("Anafe", "Un anafe", "Hogar", "Anafin", 5200.0, "Gas");
        Producto producto4 = new Producto("Producto ","Un producto", "Producto", "Brand 1", 60.0, "type");

        categoria1.getProductList().addAll(Arrays.asList(producto1, producto2));
        categoria2.getProductList().addAll(Arrays.asList(producto3, producto4));

        when(daoCategoria.orderCategoryProductsByPriceRange("1", "100")).thenReturn(new ArrayList<>(Arrays.asList(categoria1, categoria2)));

        List<Categoria> result1 = categoryService.orderCategoryProductsByPriceRange("1", "20");
        List<Categoria> result2 = categoryService.orderCategoryProductsByPriceRange("25", "100");

        assertEquals(2, result1.size());
        assertEquals(0, result2.size());

        assertEquals(producto1, result1.get(0).getProductList().get(0));
        assertEquals(producto2, result1.get(0).getProductList().get(1));
    }
}