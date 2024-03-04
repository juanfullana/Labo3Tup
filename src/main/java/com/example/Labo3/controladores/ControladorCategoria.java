package com.example.Labo3.controladores;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.servicio.ServicioCategoria;
import com.example.Labo3.utilidad.GestorRespuestas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.Map;

@RestController
@RequestMapping("/categories")
public class ControladorCategoria {
    private final ServicioCategoria servicioCategoria;

    @Autowired
    public ControladorCategoria(ServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    @GetMapping
    public ResponseEntity<Object> getCategorias() {

        if (servicioCategoria.getAllCategories().isEmpty()) {
            return GestorRespuestas.response(
                    HttpStatus.NO_CONTENT,
                    servicioCategoria.getAllCategories(),
                    "No categories found."
            );
        } else {
            return GestorRespuestas.response(
                    HttpStatus.OK,
                    servicioCategoria.getAllCategories(),
                    "Categories retrived successfuly"
            );
        }
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody Categoria categoria){
        return GestorRespuestas.response(
                HttpStatus.OK,
                servicioCategoria.createCategory(categoria),
                "Category created successfully."
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id) {
        if(servicioCategoria.getCategoryById(id) == null){
            return GestorRespuestas.response(
                    HttpStatus.NOT_FOUND,
                    servicioCategoria.getCategoryById(id),
                    "Category with id " + id + " does not exist."
            );
        } else {
            return GestorRespuestas.response(
                    HttpStatus.OK,
                    servicioCategoria.getCategoryById(id),
                    "Category retrieved successfully."
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id) {
        if(servicioCategoria.deleteCategory(id)) {
            return GestorRespuestas.response(
                    HttpStatus.OK,
                    servicioCategoria.deleteCategory(id),
                    "Category deleted successfully."
            );
        } else {
            return GestorRespuestas.response(
                    HttpStatus.NOT_FOUND,
                    servicioCategoria.deleteCategory(id),
                    "Category does not exist."
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editCategory(@PathVariable int id, @RequestBody Categoria categoria){
        if(servicioCategoria.editCategory(id, categoria) == null) {
            return GestorRespuestas.response(
                    HttpStatus.NOT_FOUND,
                    servicioCategoria.editCategory(id, categoria),
                    "Category with id " + id + " does not exist."
            );
        } else {
            return GestorRespuestas.response(
                    HttpStatus.OK,
                    servicioCategoria.editCategory(id, categoria),
                    "Category with id " + id + " updated successfully."
            );
        }
    }

    @GetMapping("/brand")
    public ResponseEntity<Object> getCategoryProductsByBrand(@RequestParam Map<String, String> params) {
        String brand = params.get("brand");

        if(servicioCategoria.getCategoryProductsByBrand(brand) == null){
            return GestorRespuestas.response(
                    HttpStatus.NOT_FOUND,
                    servicioCategoria.getCategoryProductsByBrand(brand),
                    "Products by brand: " + brand + " couldn't be retrieved. Brand may not exist."
            );
        } else {
            return GestorRespuestas.response(
                    HttpStatus.OK,
                    servicioCategoria.getCategoryProductsByBrand(brand),
                    "Category products retrieved successfully by brand: " + brand + "."
            );
        }
    }

    @GetMapping("/order")
    public ResponseEntity<Object> orderCategoryProductsByPrice(@RequestParam Map<String, String> params) {
        String order_price = params.get("order_price");

        if(servicioCategoria.orderCategoryProductsByPrice(order_price) == null){
            return GestorRespuestas.response(
                    HttpStatus.NOT_FOUND,
                    servicioCategoria.orderCategoryProductsByPrice(order_price),
                    "No products found."
            );
        } else {
            return GestorRespuestas.response(
                    HttpStatus.OK,
                    servicioCategoria.orderCategoryProductsByPrice(order_price),
                    "Products ordered by " + order_price + "."
            );
        }
    }

    @GetMapping("/price")
    public ResponseEntity<Object> orderCategoryProductsByPriceRange(@RequestParam Map<String, String> params) {
        String price_min = params.get("price_min");
        String price_max = params.get("price_max");

        if(servicioCategoria.orderCategoryProductsByPriceRange(price_min, price_max) == null) {
            return GestorRespuestas.response(
                    HttpStatus.NOT_FOUND,
                    servicioCategoria.orderCategoryProductsByPriceRange(price_min, price_max),
                    "No products found."
            );
        } else {
            return GestorRespuestas.response(
                    HttpStatus.OK,
                    servicioCategoria.orderCategoryProductsByPriceRange(price_min, price_max),
                    "Products between " + price_min + " and " + price_max + "."
            );
        }
    }

}