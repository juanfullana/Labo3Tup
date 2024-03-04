package com.example.Labo3.controladores;

import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.excepciones.ExcepcionCategoriaNula;
import com.example.Labo3.persistencia.excepciones.ExcepcionProductoNulo;
import com.example.Labo3.servicio.ServicioProducto;
import com.example.Labo3.utilidad.GestorRespuestas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ControladorProducto {
    private final ServicioProducto servicioProducto;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() throws ExcepcionProductoNulo {
        if (servicioProducto.getAllProducts().isEmpty()) {
            return GestorRespuestas.response(
                HttpStatus.NO_CONTENT,
                servicioProducto.getAllProducts(),
                "No products created yet.");
        } else {
            return GestorRespuestas.response(
                HttpStatus.OK,
                servicioProducto.getAllProducts(),
                "Categories retrieved successfully.");
        }
    }
    
    @PostMapping
    public Producto createProduct(@RequestBody Producto producto) throws ExcepcionCategoriaNula {
        return servicioProducto.createProduct(producto);
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable int id) throws ExcepcionProductoNulo {
        return servicioProducto.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable int id) throws ExcepcionProductoNulo {
        return servicioProducto.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable int id, @RequestBody Producto prod) throws ExcepcionProductoNulo {
        return servicioProducto.updateProduct(id, prod);
    }

    @GetMapping("/filter")
    public List<Producto> getProductsByFilter(@RequestParam Map<String, String> params) throws ExcepcionProductoNulo {
        String name = params.get("name");
        String brand = params.get("brand");
        String category_name = params.get("cat");
        return servicioProducto.getProductsByFilter(name, brand, category_name);
    }
}
