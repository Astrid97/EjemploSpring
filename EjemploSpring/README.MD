# API REST

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0%2B-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2-Database-4479A1?style=for-the-badge&logo=database&logoColor=white)

# Trabajo Práctico - Api Rest  **Programación IV** - TUP UTN.

Desarrollar una API REST completa y profesional para la gestión de productos,
aplicando arquitectura en capas, validaciones, manejo de errores, persistencia con
Spring Data JPA y documentación con Swagger.

El proyecto fue desarrollado utilizando el patrón arquitectónico de diseño **MVC (Model-View-Controller)** adaptado para APIs REST, separando las responsabilidades en las siguientes capas:

*   **Controllers:** Manejo de peticiones HTTP y mapeo de endpoints.
*   **Services:** Lógica de negocio, cálculos de subtotales/totales y seguridad transaccional (`@Transactional`).
*   **Repositories:** Acceso a datos utilizando Spring Data JPA.
*   **Entities:** Modelado de base de datos relacional con Hibernate.
*   **DTOs (Data Transfer Objects):** Patrón utilizado rigurosamente para aislar la base de datos y controlar la entrada/salida de datos (Ej: `PedidoCreate`, `CategoriaDto`).

## Configuración y Ejecución

La base de datos utilizada es **H2 en memoria**, lo que garantiza que el proyecto sea 100% portable y no requiera configuraciones externas.

1. Clonar el repositorio.
2. Sincronizar las dependencias de Gradle (build.gradle.kts).
3. Ejecutar la clase principal `EjemploSpringApplication.java`.
4. La API estará disponible para ser consumida a través de Postman (o cualquier cliente HTTP) utilizando la ruta base: `http://localhost:8080/api/`


## Documentación OpenAPI (Swagger)

La API se encuentra completamente documentada. Una vez ejecutada la aplicación, se puede acceder a la interfaz interactiva de Swagger UI en:

**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

![img.png](assets/img_15.png)
![img_1.png](assets/img_16.png)


## 📸 Pruebas de Funcionamiento (Postman)

### Creación de Usuarios y Categorías (Puntos 5a y 5c)

#### 1. Crear 2 Usuarios (POST a http://localhost:8080/api/usuarios)
![img.png](assets/img.png)
![img_1.png](assets/img_1.png)

####  2. Crear 3 categorias (POST a http://localhost:8080/api/categorias)
![img_2.png](assets/img_2.png)
![img_3.png](assets/img_3.png)
![img_4.png](assets/img_4.png)

#### 3. Carga de 10 Productos (POST a http://localhost:8080/api/productos)
![img_5.png](assets/img_5.png) 
![img_6.png](assets/img_6.png)
![img_7.png](assets/img_7.png)

#### 4. Creacion de 3 Pedidos
#### 1er pedido

`{
    "fecha": "2026-09-07",
    "estado": "PENDIENTE",
    "formaPago": "EFECTIVO",
    "detalles": [
        {
            "cantidad": 1,
            "idProducto": 1
        },
        {
            "cantidad": 1,
            "idProducto": 2
        }
    ]
}`

![img_8.png](assets/img_8.png)

#### 2do pedido 

`{
"fecha": "2026-09-08",
"estado": "CONFIRMADO",
"formaPago": "TARJETA",
"detalles": [
{
"cantidad": 2,
"idProducto": 4
},
{
"cantidad": 1,
"idProducto": 8
}
]
}`

![img_9.png](assets/img_9.png)

#### 3er pedido 
`{
  "fecha": "2026-09-08",
  "estado": "TERMINADO",
  "formaPago": "TRANSFERENCIA",
  "detalles": [
    {
      "cantidad": 3,
      "idProducto": 3
    },
    {
      "cantidad": 2,
      "idProducto": 10
    }
  ]
}`

![img_10.png](assets/img_10.png)

### 5. Actualizaciones y Busquedas 

### Actualizar una Categoria 

#### PUT a http://localhost:8080/api/categorias/1

![img_11.png](assets/img_11.png)

### Buscar Usuarios por ID y por mail 

#### Buscar por ID: GET a http://localhost:8080/api/usuarios/1

![img_12.png](assets/img_12.png)

#### Buscar por Mail: GET a http://localhost:8080/api/usuarios/mail/astrid@ejemplo.com

findByMailIgnoreCase, ignora mayusculas al comparar

![img_13.png](assets/img_13.png)

## Feature Extra: Vinculación Dinámica (Categoría-Producto)

Para mantener una alta cohesión, se implementó un endpoint de vinculación `PUT /{idCategoria}/productos/{idProducto}` que permite asociar mercadería a categorías existentes.

Al consultar la categoría, el patrón DTO se encarga de serializar dinámicamente la lista de productos asociados.

![img_14.png](assets/img_14.png)