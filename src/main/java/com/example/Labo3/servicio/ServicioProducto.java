package com.example.Labo3.servicio;

import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.excepciones.ExcepcionCategoriaNula;
import com.example.Labo3.persistencia.excepciones.ExcepcionProductoNulo;

import java.util.List;

public interface ServicioProducto {
    List<Producto> getAllProducts() throws ExcepcionProductoNulo;
    Producto createProduct(Producto producto) throws ExcepcionCategoriaNula;
    Producto getProductById(int id) throws ExcepcionProductoNulo;
    Boolean deleteProduct(int id) throws ExcepcionProductoNulo;
    Producto updateProduct(int id, Producto prod) throws ExcepcionProductoNulo;
    List<Producto>  getProductsByFilter(String name, String brand, String category_name) throws ExcepcionProductoNulo;
}