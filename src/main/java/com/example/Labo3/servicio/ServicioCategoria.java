package com.example.Labo3.servicio;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public interface ServicioCategoria {
    List<Categoria> getAllCategories();
    Categoria createCategory(Categoria cat);
    Categoria getCategoryById(int id);
    Boolean deleteCategory(int id);
    Categoria editCategory(int id, Categoria cat);
    ArrayList<Producto> getCategoryProductsByBrand(String brand);
    ArrayList<Categoria> orderCategoryProductsByPrice(String order_price);
    ArrayList<Categoria> orderCategoryProductsByPriceRange(String price_min, String price_max);
}
