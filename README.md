## API Reference
# Franquicia

API de prueba para cumplir con los siguientes objetivos.

* El proyecto debe ser desarrollado en Sprint Boot
* Exponer endpoint para agregar una nueva franquicia
* Exponer endpoint para agregar una nueva sucursal a la franquicia
* Exponer endpoint para agregar un nuevo producto a la sucursal
* Exponer endpoint para eliminar un nuevo producto a una sucursal
* Exponer endpoint para modificar un Stock de un nuevo producto
* Exponer endpoint para agregar que permita mostrar cuál es el producto que más stock tiene por sucursal para una
  franquicia puntual. Debe retomar un listado de productos que indiquen a qué sucursal pertenece.
* Utilizar sistemas de persistencia de datos como Redis, MySql, Mongo BD, Dynamo en algún proveedor de nube.
  Queda abierto a libre escogencia.

## Demo

La aplicación se encuentra desarrollada en Spring Boot con java 17, se encuentra dentro de ella la configuración necesaria para crear una imagen Docker. 

Al momento de ejecutar dicha imagen, Docker podemos acceder al [LINK](http://localhost:8080/swagger-ui/index.html#/) donde se encuentra la documentación swagger.

## API Reference

### exponer endpoint para agregar una nueva franquicia
Este endpoint permite agregar una nueva franquicia

```http
  POST localhost:8080/v1/franquicia/guardar
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `string` | **Required**. Nombre de la franquicia |
| `descripcion` | `string` |  Descripcion de la franquicia |

#### Request

```
  curl --location 'localhost:8080/v1/franquicia/guardar' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "Nueva franquicia desde postman",
    "descripcion": "Franquicia creada desde postman esto es una descripcion"
}'
```

#### Response

```
{
    "status": "OK",
    "estadoOperacion": "SUCCESS",
    "message": "Se ha guardado la franquicia",
    "data": {
        "idFranquicia": 1,
        "nombre": "Nueva franquicia desde postman",
        "descripcion": "Franquicia creada desde postman esto es una descripcion"
    }
}
```
### Endpoint que permita actualizar el nombre de la franquicia.
```http
  PUT localhost:8080/v1/franquicia/actualizar/1
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. ID de la franquicia |
| `nombre` | `string` | **Required**. Nombre de la franquicia |
| `descripcion` | `string` |  Descripcion de la franquicia |

#### Request

```
curl --location --request PUT 'localhost:8080/v1/franquicia/actualizar/1' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "nombre": "Actualizar franquicia desde postman",
    "descripcion": "Franquicia actualizada desde postman esto es una descripcion"
}'
```

#### Response

```
{
    "status": "OK",
    "estadoOperacion": "SUCCESS",
    "message": "Se ha actualizado la franquicia",
    "data": {
        "idFranquicia": 1,
        "nombre": "Actualizar franquicia desde postman",
        "descripcion": "Franquicia creada desde postman esto es una descripcion"
    }
}
```

### Exponer endpoint para agregar una nueva sucursal a la franquicia
Este endpoint permite agregar una nueva sucursal a la franquicia

```http
 localhost:8080/v1/sucursal/guardar
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `idFranquicia` | `Long` | **Required**. ID de la franquicia |
| `nombre` | `string` |  Nombre de la sucursal |
| `telefono` | `string` |  telefono de la sucursal |

#### Request

```
curl --location 'localhost:8080/v1/sucursal/guardar' \
--header 'Content-Type: application/json' \
--data '{
    "idFranquicia": 1,
    "nombre": "Guardar sucursal desde postman",
    "telefono": "3223008828"

}'
```

#### Response

```
{
    "status": "OK",
    "estadoOperacion": "SUCCESS",
    "message": "La operacion se realizado de manera correcta",
    "data": {
        "idSucursal": 2,
        "nombre": "Guardar sucursal desde postman",
        "telefono": "3223008828",
        "franquicia": {
            "idFranquicia": 1,
            "nombre": "Actualizar franquicia desde postman",
            "descripcion": "Franquicia creada desde postman esto es una descripcion"
        }
    }
}
```

### Endpoint que permita actualizar el nombre de la sucursal
```http
  PUT localhost:8080/v1/sucursal/actualizar/1
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. ID de la franquicia |
| `nombre` | `string` | **Required**. Nombre de la franquicia |
| `descripcion` | `string` |  Descripcion de la franquicia |

#### Request

```
curl --location --request PUT 'localhost:8080/v1/sucursal/actualizar/1' \
--header 'Content-Type: application/json' \
--data '{
    "idFranquicia": 1,
    "idSucursal": 1,
    "nombre": "Actualizar sucursal desde postman 1232",
    "telefono": "00011133"

}'
```

#### Response

```
{
    "status": "OK",
    "estadoOperacion": "SUCCESS",
    "message": "La operacion se realizado de manera correcta",
    "data": {
        "idSucursal": 1,
        "nombre": "Actualizar sucursal desde postman 1232",
        "telefono": "00011133"
    }
}
```

### Exponer endpoint para agregar un nuevo producto a la sucursal
Este endpoint permite agregar una nueva sucursal a la franquicia

```http
POST localhost:8080/v1/producto/guardar
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `idSucursal` | `Long` | **Required**. ID de la sucursal |
| `nombre` | `string` |  Nombre del producto |
| `cantidadStock` | `int` |  cantidad del producto |

#### Request

```
curl --location 'localhost:8080/v1/producto/guardar' \
--header 'Content-Type: application/json' \
--data '{
    "idSucursal":1,
    "nombre":"Producto desde postman actualizado",
    "cantidadStock": 12
}'
```

#### Response

```
{
    "status": "OK",
    "estadoOperacion": "SUCCESS",
    "message": "La operacion se realizado de manera correcta",
    "data": {
        "productoID": 3,
        "nombre": "Producto desde postman actualizado",
        "cantidadStock": 12,
        "sucursal": {
            "idSucursal": 1,
            "nombre": "Guardar sucursal desde postman",
            "telefono": "3223008828"
        }
    }
}
```

### Endpoint para eliminar un nuevo producto a una sucursal
```http
 DELETE localhost:8080/v1/producto/eliminar/1
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. ID del producto a eliminar |

#### Request

```
curl --location --request DELETE 'localhost:8080/v1/producto/eliminar/1'
```

#### Response

```
{
    "status": "OK",
    "estadoOperacion": "SUCCESS",
    "message": "La operacion se realizado de manera correcta",
    "data": null
}
```

### Endpoint para modificar un Stock de un nuevo producto
```http
 PUT localhost:8080/v1/producto/eliminar/1
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `idSucursal` | `Long` | **Required**. ID de la sucursal |
| `nombre` | `string` |  Nombre del producto |
| `cantidadStock` | `int` |  cantidad del producto |
| `idProducto` | `Long` | **Required**. ID del producto |

#### Request

```
curl --location --request PUT 'localhost:8080/v1/producto/actualizar/2' \
--header 'Content-Type: application/json' \
--data '{
    "idSucursal":1,
    "nombre":"Producto desde postman actualizado",
    "cantidadStock": 12,
    "idProducto": 2
}'
```

#### Response

```
{
    "status": "OK",
    "estadoOperacion": "SUCCESS",
    "message": "La operacion se realizado de manera correcta",
    "data": {
        "productoID": 2,
        "nombre": "Producto desde postman actualizado",
        "cantidadStock": 12,
        "sucursal": {
            "idSucursal": 1,
            "nombre": "Guardar sucursal desde postman",
            "telefono": "3223008828"
        }
    }
}
```