package com.example.Labo3.persistencia.implementacion;

import com.example.Labo3.modelo.Categoria;
import com.example.Labo3.modelo.Producto;
import com.example.Labo3.persistencia.DaoCategoria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ImplementacionDaoCategoria implements DaoCategoria {

    private final List<Categoria> categories = new ArrayList<>();

    @Override
    public List<Categoria> findAllCategory() {
        return categories;
    }

    @Override
    public Categoria createCategory(Categoria newCategoria) {
        for (Categoria categoria : categories) {
            if (newCategoria.getName().equalsIgnoreCase(categoria.getName())) {
                System.out.println("Category " + newCategoria.getName() + " already exists.");
                return categoria;
            }
        }
        newCategoria.setId(generateId());
        categories.add(newCategoria);
        System.out.println("Category created successfully.");
        return newCategoria;
    }

    private int generateId() {
        return categories.isEmpty() ? 0 : categories.get(categories.size() - 1).getId() + 1;
    }

    @Override
    public Categoria getCategoryById(int id) {
        for (Categoria categoria : categories) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        System.out.println("Category not found for category id: " + id + ".");
        return null;
    }

    @Override
    public Boolean deleteCategory(int id) {
        for (Categoria categoria : categories) {
            if (categoria.getId() == id) {
                categories.remove(categoria);
                System.out.println("The Category id " + id + " was successfully deleted.");
                return true;
            }
        }
        System.out.println("Category not found.");
        return false;
    }

    @Override
    public Categoria editCategory(int id, Categoria cat) {
        return null;
    }

    @Override
    public ArrayList<Producto> getCategoryProductsByBrand(String band) {
        return null;
    }

    @Override
    public Categoria updateCategory(int id, Categoria cat) {
        Categoria foundCategory = getCategoryById(id);
        if (foundCategory != null) {
            foundCategory.setName(cat.getName());
            foundCategory.setDescription(cat.getDescription());
            System.out.println("The category was successfully updated.");
        }
        return foundCategory;
    }

    @Override
    public ArrayList<Categoria> orderCategoryProductsByPrice(String order_price) {
        if (categories.isEmpty()) {
            System.out.println("The list of categories is empty, please create new categories before ordering them");
            return new ArrayList<>();
        }

        List<Categoria> orderedList = new ArrayList<>();
        for (Categoria categoria : categories) {
            List<Producto> productList = new ArrayList<>(categoria.getProductList());
            if (productList.isEmpty()) {
                System.out.println("There are no products to order in this category.");
            } else {
                if (order_price.equalsIgnoreCase("asc")) {
                    productList.sort(Comparator.comparing(Producto::getList_price));
                } else if (order_price.equalsIgnoreCase("desc")) {
                    productList.sort(Comparator.comparing(Producto::getList_price).reversed());
                }
                Categoria orderedCategoria = new Categoria(categoria.getId(), categoria.getName(), categoria.getDescription());
                orderedCategoria.setProductList((ArrayList<Producto>) productList);
                orderedList.add(orderedCategoria);
            }
        }
        System.out.println("Successfully ordered categories in " + order_price + "ending order");
        return (ArrayList<Categoria>) orderedList;
    }

    @Override
    public ArrayList<Categoria> orderCategoryProductsByPriceRange(String price_min, String price_max) {
        double min = Double.parseDouble(price_min);
        double max = Double.parseDouble(price_max);

        List<Categoria> results = new ArrayList<>();
        System.out.println("Min: " + min + ". Max: " + max);

        if (categories.isEmpty()) {
            System.out.println("There are no created categories, please create new categories.");
        } else {
            for (Categoria categoria : categories) {
                List<Producto> rangeProductsList = new ArrayList<>();
                for (Producto producto : categoria.getProductList()) {
                    if (producto.getList_price() >= min && producto.getList_price() <= max) {
                        rangeProductsList.add(producto);
                    }
                }
                if (!rangeProductsList.isEmpty()) {
                    Categoria priceRangeCategoria = new Categoria(categoria.getId(), categoria.getName(), categoria.getDescription());
                    priceRangeCategoria.setProductList((ArrayList<Producto>) rangeProductsList);
                    results.add(priceRangeCategoria);
                    System.out.println("This are the products in the range price of " + min + " and " + max + ".");
                }
            }
        }
        return (ArrayList<Categoria>) results;
    }
}
