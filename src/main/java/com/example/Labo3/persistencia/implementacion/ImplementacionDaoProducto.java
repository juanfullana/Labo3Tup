package com.example.Labo3.persistencia.implementacion;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.DaoProducto;
import com.example.Labo3.persistencia.DaoCategoria;
import com.example.Labo3.persistencia.excepciones.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ImplementacionDaoProducto implements DaoProducto {

    private final List<Categoria> categories;
    private final List<Producto> productos;

    @Autowired
    public ImplementacionDaoProducto(DaoCategoria daoCategoria) throws IllegalStateException {
        this.categories = daoCategoria.findAllCategory();
        this.productos = new ArrayList<>();
    }

    @Override
    public List<Producto> findAllProducts(){
        return productos;
    }

    @Override
    public Producto createProduct(Producto producto) throws ExcepcionCategoriaNula {
        boolean categoryFound = false;

        if(categories.isEmpty()) {
            throw new ExcepcionCategoriaNula("There are no categories loaded yet. Please create a category before creating a new Product.");
        } else {
            for (Categoria categoria : categories) {
                if(producto.getCategory().equalsIgnoreCase(categoria.getName())) {
                    producto.setId(generateId());
                    productos.add(producto);
                    categoria.getProductList().add(producto);
                    categoryFound = true;
                }
            }
        }

        if(!categoryFound) {
            throw new ExcepcionCategoriaNula("The Category doesn't exist. Please create a new category before creating a product for that category.");
        }

        return producto;
    }

    private int generateId() {
        return (productos.isEmpty()) ? 0 : productos.get(productos.size() - 1).getId() + 1;
    }

    @Override
    public Producto getProductById(int id) throws ExcepcionProductoNulo {
        for (Producto producto : productos) {
            if(producto.getId() == id){
                return producto;
            }
        }
        throw new ExcepcionProductoNulo("The product with ID " + id + " couldn't be found.");
    }

    @Override
    public Boolean deleteProduct(int id) throws ExcepcionProductoNulo {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        throw new ExcepcionProductoNulo("The product with ID " + id + " couldn't be found.");
    }

    @Override
    public Producto updateProduct(int id, Producto prod) throws ExcepcionProductoNulo {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                producto.setName(prod.getName());
                producto.setDescription(prod.getDescription());
                producto.setCategory(prod.getCategory());
                producto.setBrand(prod.getBrand());
                producto.setList_price(prod.getList_price());
                producto.setType(prod.getType());
                return producto;
            }
        }
        throw new ExcepcionProductoNulo("The product with ID " + id + " couldn't be found.");
    }

    @Override
    public List<Producto> getProductsByFilter(String name, String brand, String category_name) throws ExcepcionProductoNulo {
        List<Producto> filteredProductos = new ArrayList<>();
        for (Producto producto : productos) {
            if ((name == null || producto.getName().equalsIgnoreCase(name))
                    && (brand == null || producto.getBrand().equalsIgnoreCase(brand))
                    && (category_name == null || producto.getCategory().equalsIgnoreCase(category_name))) {
                filteredProductos.add(producto);
            }
        }
        if (!filteredProductos.isEmpty()) {
            return filteredProductos;
        } else {
            throw new ExcepcionProductoNulo("No products match the filters provided.");
        }
    }
}
