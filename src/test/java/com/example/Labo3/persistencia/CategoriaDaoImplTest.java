package com.example.Labo3.persistencia;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.implementacion.ImplementacionDaoCategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaDaoImplTest {

    private ImplementacionDaoCategoria categoryDao;

    @BeforeEach
    public void setUp() {
        categoryDao = new ImplementacionDaoCategoria();
    }

    @Test
    public void createCategoryTest() {
        Categoria categoria = new Categoria(0, "Cate 1", "Cate 1");
        Categoria createdCategoria = categoryDao.createCategory(categoria);
        assertNotNull(createdCategoria);
        assertEquals(categoria.getName(), createdCategoria.getName());
    }

    @Test
    public void getCategoryByIdTest() {
        Categoria categoria = new Categoria(0, "Cate 1", "Cate 1");
        categoryDao.createCategory(categoria);
        Categoria getCategoria = categoryDao.getCategoryById(0);
        assertNotNull(getCategoria);
        assertEquals(categoria.getId(), getCategoria.getId());
    }

    @Test
    public void deleteCategoryTest() {
        Categoria categoria = new Categoria(0, "Cate 1", "Cate 1");
        categoryDao.createCategory(categoria);
        assertTrue(categoryDao.deleteCategory(0));
    }

    @Test
    public void editCategoryTest() {
        Categoria categoria = new Categoria(0, "Cate 1", "Cate 1");
        categoryDao.createCategory(categoria);
        Categoria updateCategoria = new Categoria(0, "Cate 1", "Cate 1 editada");
        Categoria editedCategoria = categoryDao.editCategory(0, updateCategoria);
        assertNotNull(editedCategoria);
        assertEquals(updateCategoria.getName(), editedCategoria.getName());
    }

    @Test
    public void testOrderCategoryProductsByPriceRange_NoProductsInRange() {
        Categoria categoria = new Categoria(1, "Hogar", "Cosas de Hogar");
        Producto producto = new Producto("Garrafa", "Una garrafa", "Hogar", "Garrafin", 9999.99, "Gas");
        Producto producto2 = new Producto("Anafe", "Un anafe", "Hogar", "Anafin", 5200.0, "Gas");
        categoria.getProductList().add(producto);
        categoria.getProductList().add(producto2);

        categoryDao.createCategory(categoria);

        List<Categoria> filteredCategories = categoryDao.orderCategoryProductsByPriceRange("1200", "1500");

        assertFalse(filteredCategories.isEmpty());
    }

    @Test
    public void orderCategoryProductsByPriceRangeTest() {
        List<Categoria> filteredCategories = categoryDao.orderCategoryProductsByPriceRange("700", "900");
        assertTrue(filteredCategories.isEmpty());
    }

    @Test
    public void testOrderCategoryProductsByPriceRange_Parameterized() {
        String[][] testData = {
                {"1200", "1500", "Hogar", "Gas"}, // Rango válido
                {"2000", "3000", "Electrodomésticos", "Eléctrico"}, // Rango inválido
                {"500", "700", "Herramientas", "Manual"} // Sin productos en la categoría
        };

        for (String[] data : testData) {
            List<Categoria> filteredCategories = categoryDao.orderCategoryProductsByPriceRange(data[0], data[1]);
            assertEquals(data[2], filteredCategories.get(0).getName());
            assertEquals(data[3], filteredCategories.get(0).getProductList().get(0).getType());
        }
    }
}
