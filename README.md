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