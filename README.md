## Laboratorio de Computación III

API REST desarrollada IntelliJ IDEA/Java y Spring-Boot.
Se basa en productos que forman parte de categorias.
El orden de creación deberá ser Categoría --> Producto
Se ejecuta en  localhost:8080

*** Categorias ***

· GET /categories --- Todas las categorias.<br />
· POST /categories -- Creacion de una categoria <br />
· GET /categories/id -- Recupera una categoria en particular segun el id que se pase como parametro.<br />
· PUT /categories/id -- Hace un update o una edición de la categoría que se pase como parametro<br />
· DELETE /categories/id -- Elimina la categoria que se pase como parametro. <br />
· GET /categories/brand?brand="brand_name" -- Recupera las categorias que contengan productos de X marca. <br />
· GET /categories/order?order_price="asc" or "desc" -- Recupera las categorias que contengan productos, y  muestra de forma ascendiente (asc) o descendiente (desc)<br />
· GET /categories/price?price_min=10&price_max=20 -- Recupera las categorias con productos filtrados por un precio minimo y un precio maximo que se pasa como parametro<br />

*** Productos ***

· GET /products -- Recupera la lista de todos los productos.<br />
· POST /products -- Crea un producto.<br />
· GET /products/id -- Recupera un producto en particular segun el id que se pase como parametro.<br />
· PUT /products/id -- Hace un update o una edición del producto que se pase como parametro.<br />
· DELETE /products/id -- Elimina el producto que se pase como parametro. <br />
· GET /products/filter?name="producto"&brand="brand"&cat="categoria" -- Recupera los productos filtrados usando los parametros provistos (nombre, marca y categoria)<br />
