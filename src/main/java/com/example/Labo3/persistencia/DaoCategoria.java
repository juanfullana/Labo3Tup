package com.example.Labo3.persistencia;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public interface DaoCategoria {
    List<Categoria> findAllCategory();
    Categoria createCategory(Categoria cat);
    Categoria getCategoryById(int id);
    Boolean deleteCategory(int id);
    Categoria editCategory(int id, Categoria cat);
    ArrayList<Producto> getCategoryProductsByBrand(String band);
    Categoria updateCategory(int id, Categoria cat);
    ArrayList<Categoria> orderCategoryProductsByPrice(String order_price);
    ArrayList<Categoria> orderCategoryProductsByPriceRange(String price_min, String price_max);
}
