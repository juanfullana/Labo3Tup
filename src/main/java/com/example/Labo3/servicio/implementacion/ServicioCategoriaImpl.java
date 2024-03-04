package com.example.Labo3.servicio.implementacion;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.DaoCategoria;
import com.example.Labo3.servicio.ServicioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioCategoriaImpl implements ServicioCategoria {
    private final DaoCategoria daoCategoria;

    @Autowired
    public ServicioCategoriaImpl(DaoCategoria daoCategoria) {
        this.daoCategoria = daoCategoria;
    }

    @Override
    public List<Categoria> getAllCategories() {
        return daoCategoria.findAllCategory();
    }

    @Override
    public Categoria createCategory(Categoria cat) {
        return daoCategoria.createCategory(cat);
    }

    @Override
    public Categoria getCategoryById(int id){
        return daoCategoria.getCategoryById(id);
    }

    @Override
    public Boolean deleteCategory(int id){
        return daoCategoria.deleteCategory(id);
    }

    @Override 
    public Categoria editCategory(int id, Categoria cat){
        return daoCategoria.editCategory(id, cat);
    }

    @Override
    public ArrayList<Producto> getCategoryProductsByBrand(String brand) {
        return daoCategoria.getCategoryProductsByBrand(brand);
    }

    @Override
    public ArrayList<Categoria> orderCategoryProductsByPrice(String order_price) {
        return daoCategoria.orderCategoryProductsByPrice(order_price);
    }

    @Override
    public ArrayList<Categoria> orderCategoryProductsByPriceRange(String price_min, String price_max) {
        return daoCategoria.orderCategoryProductsByPriceRange(price_min, price_max);
    }
}