package com.example.Labo3.modelo;

import java.util.ArrayList;

public class Categoria {
    private int id;
    private String name;
    private String description;
    private ArrayList<Producto> productoList = new ArrayList<>();

    public Categoria(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Producto> getProductList() {
        return productoList;
    }

    public void setProductList(ArrayList<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productList=" + productoList +
                '}';
    }
}
