package com.example.Labo3.servicio.implementacion;

import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.DaoProducto;
import com.example.Labo3.persistencia.excepciones.ExcepcionCategoriaNula;
import com.example.Labo3.persistencia.excepciones.ExcepcionProductoNulo;
import com.example.Labo3.servicio.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioProductoImpl implements ServicioProducto {
    private final DaoProducto daoProducto;

    @Autowired
    public ServicioProductoImpl(DaoProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    @Override
    public List<Producto> getAllProducts() throws ExcepcionProductoNulo {
        return daoProducto.findAllProducts();
    }

    @Override
    public Producto createProduct(Producto producto) throws ExcepcionCategoriaNula {
        return daoProducto.createProduct(producto);
    }

    @Override
    public Producto getProductById(int id) throws ExcepcionProductoNulo {
        return daoProducto.getProductById(id);
    }

    @Override
    public Boolean deleteProduct(int id) throws ExcepcionProductoNulo {
        return  daoProducto.deleteProduct(id);
    }

    @Override
    public Producto updateProduct(int id, Producto prod) throws ExcepcionProductoNulo {
        return daoProducto.updateProduct(id, prod);
    }

    @Override
    public List<Producto> getProductsByFilter(String name, String brand, String category_name) throws ExcepcionProductoNulo {
        return daoProducto.getProductsByFilter(name, brand, category_name);
    }
}
