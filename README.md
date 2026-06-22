# Mundo Cigarro - Arquitectura de Microservicios

## Descripción del Proyecto

Mundo Cigarro es un sistema desarrollado en Spring Boot basado en arquitectura de microservicios para la gestión de una tienda dedicada a la venta de cigarros y accesorios para fumar.

El proyecto permite administrar:

* Clientes
* Productos
* Ventas
* Detalle de ventas
* Delivery
* Pago
* Inventario
* ApiGateWay
* Auth
* Ruta
* Vehiculo
* Conductor

Cada módulo funciona como un microservicio independiente conectado mediante comunicación REST utilizando RestTemplate.

---

# Tecnologías Utilizadas
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Cloud Gateway
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok
* JUnit 5
* Mockito
* Postman
* Git & GitHub

---

# Instalación del Proyecto

## Requisitos

Antes de ejecutar el proyecto se debe tener instalado:

* Java JDK 21
* Maven
* MySQL Server
* Git
* Visual Studio Code o IntelliJ IDEA
* Postman

Verificar instalación:

```
powershell
java --version
mvn --version
git --version
```

# Arquitectura de Microservicios

| Microservicio | Puerto | Función                      |
| ------------- | ------ | ---------------------------- |
| cliente       | 8081   | Gestión de clientes          |
| producto      | 8082   | Gestión de productos         |
| venta         | 8083   | Gestión de ventas            |
| detalleventa  | 8084   | Gestión de detalles de venta |
| delivery      | 8085   | Gestión de envíos            |
| pago          | 8086   | Gestión de pagos             |
| inventario    | 8087   | Gestión de inventarios       |
| apigateway    | 9090   | Enrutamiento de solicitudes a microservicios |
| auth          | 8089   | Autenticación y generación de JWT |
| ruta          | 9091   | Gestión de rutas de reparto |
| vehiculo      | 9092   | Gestión de vehículos |
| conductor     | 9093   | Gestión de conductores |

---

# Testing

Se realizaron pruebas unitarias utilizando:

* JUnit 5
* Mockito

Microservicios con pruebas implementadas:

* Producto
* Ruta
* Vehículo
* Conductor

Las pruebas verifican:

* Listado de registros
* Búsqueda por ID
* Creación de registros
* Eliminación de registros
* Validación de dependencias externas


# Base de Datos

El proyecto utiliza MySQL.

Base de datos utilizada:

```sql
mundocigarro
```

Cada microservicio crea automáticamente sus tablas mediante:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

# Microservicios Implementados

# 1. Cliente

Puerto:

```text
8081
```

El microservicio Cliente se encarga de administrar toda la información de los clientes registrados en el sistema.

Permite:

* Registrar clientes
* Consultar clientes
* Buscar clientes específicos
* Eliminar clientes

Este servicio funciona como base para otros microservicios, ya que Venta utiliza la información de clientes para registrar compras.

Atributos:

* idCliente
* nombre
* email
* telefono
* direccion
* fechaRegistro

Endpoint base:

````text
/api/v1/clientes
```text
/api/v1/clientes
````

---

# 2. Producto

Puerto:

```text
8082
```

El microservicio Producto administra el catálogo de productos disponibles en la tienda.

Permite:

* Registrar productos
* Consultar productos
* Buscar productos
* Eliminar productos

La información almacenada es utilizada posteriormente por el microservicio DetalleVenta para calcular subtotales y registrar compras.

Atributos:

* idProducto
* nombre
* marca
* tipo
* precio

Endpoint base:

````text
/api/v1/productos

/api/v1/productos
````

---

# 3. Venta

Puerto:

```text
8083
```

El microservicio Venta se encarga de registrar las ventas realizadas por los clientes.

Permite:

* Registrar ventas
* Consultar ventas
* Buscar ventas
* Eliminar ventas

Antes de registrar una venta, el sistema verifica que el cliente exista mediante comunicación con el microservicio Cliente.

Atributos:

* idVenta
* idCliente
* fechaVenta
* totalVenta

Endpoint base:

````text
/api/v1/ventas

/api/v1/ventas
````

---

# 4. Detalle Venta

Puerto:

```text
8084
```

El microservicio DetalleVenta se encarga de almacenar los productos asociados a cada venta.

Permite:

* Registrar detalles de venta
* Consultar detalles
* Buscar detalles por ID
* Buscar detalles asociados a una venta
* Eliminar detalles

Este servicio se comunica con:

* Venta
* Producto

Además, calcula automáticamente el subtotal según el precio del producto y la cantidad comprada.

Atributos:

* idDetalle
* idVenta
* idProducto
* cantidad
* subtotal

Endpoint base:

````text
/api/v1/detalles

/api/v1/detalles
````

---

# 5. Delivery

Puerto:

```text
8085
```

El microservicio Delivery administra la información relacionada con los envíos de pedidos.

Permite:

* Registrar envíos
* Consultar envíos
* Buscar envíos
* Eliminar envíos

Antes de registrar un delivery, el sistema verifica que la venta exista mediante comunicación con el microservicio Venta.

Atributos:

* idDelivery
* idVenta
* direccion
* comuna
* region
* estado
* fechaEnvio
* fechaEntrega

Endpoint base:

````text
/api/v1/delivery

/api/v1/delivery
````


---

# 6. Pago

Puerto:

```text
8086
```

El microservicio Pago gestiona la información relacionada con los pagos de los pedidos realizados en el sistema.

Permite:

* Registrar pago
* Consultar pago
* Buscar tipo de pago
* Eliminar pago

Antes de registrar un pago, el sistema consulta el microservicio de Venta para validar la orden antes de procesar el pago.

Atributos:

* idPago
* idVenta
* metodoPago
* monto
* estadoPago
* fechaPago

Endpoint base:

````text
/api/v1/pagos

/api/v1/pagos
````
# 7. Inventario

Puerto:

```text
8087
```

El microservicio Inventario gestiona la información relacionada con el stock de productos disponibles en el sistema.

Permite:

* Registrar inventario
* Listar inventario
* Buscar productos
* Eliminar inventario

Este servicio se comunica con el microservicio de Productos para obtener información antes de registrar el inventario.

Atributos:

* id
* producto
* marca
* stock
* precio

Endpoint base:

````text
/api/v1/inventario

/api/v1/inventario
````

# 8. API Gateway

Puerto:

```text
9090
```

El API Gateway gestiona la entrada y redirección de solicitudes hacia los microservicios del sistema, centralizando el acceso a las APIs.

Permite: 

* enrutar solicitudes de clientes
* enrutar solicitudes de productos
* enrutar solicitudes de ventas
* enrutar solicitudes de detalles de venta
* enrutar solicitudes de delivery
* enrutar solicitudes de pagos
* enrutar solicitudes de inventario
* redirigir solicitudes a los microservicios correspondientes

Atributos:

* puerto de ejecución (9090)
* rutas de microservicios
* URIs de servicios
* servicios registrados

# 9. Auth

Puerto:

```text
8089
```

El microservicio Auth se encarga de la autenticación y autorización de usuarios dentro del sistema.

Permite:

* Iniciar sesión
* Validar credenciales
* Generar tokens JWT
* Gestionar roles de usuario
* Proteger el acceso a los microservicios

Este servicio es utilizado por API Gateway para validar los accesos a los distintos microservicios mediante autenticación basada en JWT.

Atributos:

* idUsuario
* username
* password
* rol

Endpoint base:

````text
/api/v1/auth

/api/v1/auth
````

# 10. Ruta

Puerto:

```text
9091
```

El microservicio Ruta administra las rutas utilizadas para la distribución y despacho de pedidos dentro del sistema.

Permite:

* Registrar rutas
* Consultar rutas
* Buscar rutas
* Actualizar rutas
* Eliminar rutas

La información almacenada es utilizada para organizar y gestionar los recorridos de entrega asociados al proceso de distribución.

Atributos:

* idRuta
* origen
* destino
* distancia
* estado

Endpoint base:

````text
/api/v1/rutas

/api/v1/rutas
````

#11. Vehículo

Puerto:

```text
9092
```

El microservicio Vehículo administra la información de los vehículos utilizados en el proceso de distribución y entrega de productos.

Permite:

* Registrar vehículos
* Consultar vehículos
* Buscar vehículos
* Actualizar vehículos
* Eliminar vehículos

La información almacenada permite gestionar los recursos de transporte utilizados durante los procesos logísticos de la empresa.

Atributos:

* idVehiculo
* patente
* marca
* modelo
* capacidadCarga
* estado

Endpoint base:

````text
/api/v1/vehiculos

/api/v1/vehiculos
````

# 12. Conductor

Puerto:

```text
9093
```

El microservicio Conductor administra la información de los conductores responsables de realizar las entregas y despachos de productos.

Permite:

* Registrar conductores
* Consultar conductores
* Buscar conductores
* Actualizar conductores
* Eliminar conductores

La información almacenada permite mantener un control de los conductores disponibles para las operaciones de transporte y distribución.

Atributos:

* idConductor
* nombre
* apellido
* rut
* telefono
* licencia
* estado

Endpoint base:

````text
/api/v1/conductores

/api/v1/conductores
````

# Comunicación Entre Microservicios

Los microservicios se comunican entre sí mediante peticiones HTTP REST.

Esto permite que cada módulo funcione de manera independiente mientras comparte información necesaria con otros servicios del sistema.

Por ejemplo:

* Venta consulta información de Cliente.
* DetalleVenta consulta información de Venta y Producto.
* Delivery consulta información de Venta.

La comunicación entre servicios permite mantener una arquitectura desacoplada y escalable.

---

# Configuración General application.properties

Ejemplo:

```properties
spring.application.name=delivery

server.port=8085

spring.datasource.url=jdbc:mysql://localhost:3306/mundocigarro
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

# Ejecución del Proyecto

Ingresar a cada microservicio:

```powershell
cd nombre_microservicio
```

Ejecutar:

```powershell
.\mvnw spring-boot:run
```

---

# Pruebas en Postman

## Crear Cliente

```http
POST http://localhost:8081/api/v1/clientes
```

```json
{
    "nombre": "Ignacio Lobos",
    "email": "ignacio@gmail.com",
    "telefono": "987654321",
    "direccion": "Santiago Centro",
    "fechaRegistro": "2026-05-14"
}
```

---

## Crear Producto

```http
POST http://localhost:8082/api/v1/productos
```

```json
{
    "nombre": "Marlboro Gold",
    "marca": "Marlboro",
    "tipo": "Cigarro",
    "precio": 4500
}
```

---

## Crear Venta

```http
POST http://localhost:8083/api/v1/ventas
```

```json
{
    "idCliente": 2,
    "fechaVenta": "2026-05-14",
    "totalVenta": 9000
}
```

---

## Crear Detalle Venta

```http
POST http://localhost:8084/api/v1/detalles
```

```json
{
    "idVenta": 2,
    "idProducto": 1,
    "cantidad": 3
}
```

---

## Crear Delivery

```http
POST http://localhost:8085/api/v1/delivery
```

```json
{
    "idVenta": 2,
    "direccion": "Av. Alameda 123",
    "comuna": "Santiago",
    "region": "Metropolitana",
    "estado": "En camino",
    "fechaEnvio": "2026-05-14",
    "fechaEntrega": "2026-05-15"
}
```

---

## Crear Pago

```http
POST http://localhost:8086/api/v1/pagos
```
```json
{
    "idVenta": 2,
    "metodoPago": "TARJETA",
    "monto": 9000,
    "estadoPago": "PAGADO",
    "fechaPago": "2026-05-14"
}
```

---

## Crear Inventario

```http
POST http://localhost:8087/api/v1/inventario
```

```json
{
    "producto": "Marlboro Gold",
    "marca": "Marlboro",
    "stock": 50,
    "precio": 4500
}
```

---

## Crear Usuario (Auth)

```http
POST http://localhost:8089/api/v1/auth/usuarios
```

```json
{
    "username": "admin",
    "password": "1234",
    "rol": "ADMIN"
}
```

---

## Login (Auth)

```http
POST http://localhost:8089/api/v1/auth/login
```

```json
{
    "username": "admin",
    "password": "1234"
}
```

Respuesta esperada:

```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBZG1pbiIsInJvbCI6IkFETUlOIiwiaWF0IjoxNzgxNzgzNzEzLCJleHAiOjE3ODE4NzAxMTN9.2jyucGyZJo_cSBM_SmSPRpMuHIvKuF7Tm1pLQSHFJ2Y"
}
```

---

## Crear Ruta

```http
POST http://localhost:9091/api/v1/rutas
```

```json
{
    "origen": "Santiago Centro",
    "destino": "Maipú",
    "distancia": 18.5,
    "estado": "ACTIVA"
}
```

---

## Crear Vehículo

```http
POST http://localhost:9092/api/v1/vehiculos
```

```json
{
    "patente": "ABCD12",
    "marca": "Chevrolet",
    "modelo": "N300",
    "capacidadCarga": 1200
}
```

---

## Crear Conductor

```http
POST http://localhost:9093/api/v1/conductores
```

```json
{
    "nombre": "Juan Perez",
    "rut": "12331238-9",
    "telefono": "987654231",
    "licencia": "A4"
}
```


## API Gateway

## Crear Cliente (Gateway)
```http
POST http://localhost:9090/api/v1/clientes
```

```http
## Crear Producto (Gateway)
POST http://localhost:9090/api/v1/productos
```

```http
## Crear Venta (Gateway)
POST http://localhost:9090/api/v1/ventas
```

```http
## Crear Pago (Gateway)
POST http://localhost:9090/api/v1/pagos
```

```http
## Crear Inventario (Gateway)
POST http://localhost:9090/api/v1/inventario
```

```http
## Crear Delivery (Gateway)
POST http://localhost:9090/api/v1/delivery
```


```http
## Crear Detalle Venta (Gateway)
POST http://localhost:9090/api/v1/detalles
```

```http
## Crear Ruta
POST http://localhost:9091/api/v1/rutas
```

---


```http
## Crear Vehículo
POST http://localhost:9092/api/v1/vehiculos
```

---


```http
## Crear Conductor
POST http://localhost:9093/api/v1/conductores
```

---


```http
## Login Auth
POST http://localhost:8089/api/v1/auth/login
```


# GitHub

Comandos utilizados:

```powershell
git add .
git commit -m "Actualizacion microservicios"
git push origin main
```

---


---

# Agregar sección: Swagger

Si cada microservicio tiene Swagger configurado:

md
# Swagger

La documentación de cada microservicio puede visualizarse mediante Swagger UI.

## Cliente


http://localhost:8081/swagger-ui.html

## Producto

http://localhost:8082/swagger-ui.html

## Venta

http://localhost:8083/swagger-ui.html

## Detalle Venta

http://localhost:8084/swagger-ui.html

## Delivery

http://localhost:8085/swagger-ui.html

## Pago

http://localhost:8086/swagger-ui.html

## Inventario

http://localhost:8087/swagger-ui.html

## Ruta

http://localhost:9091/swagger-ui.html

## Vehículo

http://localhost:9092/swagger-ui.html

## Conductor

http://localhost:9093/swagger-ui.html

## Auth

http://localhost:8089/swagger-ui.html


# Estado Actual del Proyecto

Actualmente el proyecto cuenta con:

* Arquitectura de microservicios funcional
* Comunicación REST entre servicios
* Persistencia de datos mediante MySQL
* CRUD completo en todos los microservicios
* Integración entre módulos mediante API REST
* API Gateway para centralizar el acceso a los servicios
* Sistema de autenticación y autorización mediante JWT
* Microservicio de autenticación (Auth)
* Microservicio de gestión de clientes
* Microservicio de gestión de productos
* Microservicio de gestión de ventas
* Microservicio de gestión de detalles de venta
* Microservicio de gestión de pagos
* Microservicio de gestión de inventario
* Microservicio de gestión de delivery
* Microservicio de gestión de proveedores
* Microservicio de gestión de rutas
* Microservicio de gestión de vehículos
* Microservicio de gestión de conductores
* Documentación de APIs mediante Swagger/OpenAPI
* Pruebas funcionales mediante Postman
* Pruebas unitarias con JUnit 5 y Mockito
* Diagramas de arquitectura y modelo relacional
* Control de versiones con Git y GitHub
---

# Autor

Ignacio Lobos
Diego Valle

Proyecto académico desarrollado con Spring Boot y MySQL.
