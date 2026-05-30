# Prueba Técnica – Gestión de Pólizas

## Descripción

Aplicación desarrollada en Java con Spring Boot para la administración de pólizas de seguros y sus riesgos asociados.

La solución permite crear, consultar, actualizar y eliminar pólizas, así como gestionar los riesgos vinculados a cada una de ellas, aplicando las reglas de negocio definidas en el ejercicio.

---

## Tecnologías Utilizadas

* Java 21
* Spring Boot 4.x
* Maven
* JUnit 5
* Mockito
* Swagger / OpenAPI
* Lombok

---

## Arquitectura

La solución sigue una arquitectura en capas:

```text
Controller
    ↓
Service
    ↓
Repository (Mock)
```

### Componentes

* **Controller:** Exposición de endpoints REST.
* **Service:** Implementación de reglas de negocio.
* **Repository/Core Mock:** Simulación de persistencia en memoria.
* **Exception:** Manejo de errores de negocio.
* **DTO/Model:** Objetos de transferencia y dominio.

---

## Estructura del Proyecto

```text
src
 ├── controller
 │    └── PolizaController
 │
 ├── service
 │    └── PolizaService
 │
 ├── repository
 │    └── CoreMock
 │
 ├── model
 │    ├── Poliza
 │    └── Riesgo
 │
 ├── exception
 │    ├── BusinessException
 │    └── GlobalExceptionHandler
 │
 └── test
      └── PolizaServiceTest
```

---

## Reglas de Negocio

### Póliza

* Una póliza debe tener un número único.
* El estado de la póliza puede ser:

  * ACTIVA
  * INACTIVA
  * CANCELADA

### Riesgos

* Una póliza puede tener uno o varios riesgos asociados.
* Los riesgos pueden ser agregados o eliminados de una póliza existente.

---

## Ejecución del Proyecto

### Clonar repositorio

```bash
git clone <url-repositorio>
```

### Compilar

```bash
mvn clean install
```

### Ejecutar

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:

```text
http://localhost:8080
```

---

## Documentación Swagger

Una vez iniciada la aplicación:

```text
http://localhost:8080/swagger-ui/index.html
```

Documentación OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

---

## Endpoints

### Crear Póliza

```http
POST /polizas
```

Ejemplo:

```json
{
  "numeroPoliza": "POL-001",
  "tipo": "INDIVIDUAL",
  "estado": "ACTIVA",
  "canonMensual": 100000,
  "prima": 200000,
  "ipcAplicado": 0.05
}
```

---

### Consultar Póliza

```http
GET /polizas/{id}
```

---

### Consultar Todas las Pólizas

```http
GET /polizas
```

---

### Actualizar Póliza

```http
PUT /polizas/{id}
```

---

### Eliminar Póliza

```http
DELETE /polizas/{id}
```

---

### Agregar Riesgo

```http
POST /polizas/{id}/riesgos
```

Ejemplo:

```json
{
  "direccion": "Carrera 10 # 20-30",
  "valorAsegurado": 50000000
}
```

---

### Eliminar Riesgo

```http
DELETE /polizas/{id}/riesgos/{riesgoId}
```

---

## Manejo de Excepciones

La aplicación utiliza excepciones de negocio mediante:

```java
BusinessException
```

Ejemplos:

* Póliza no encontrada.
* Riesgo no encontrado.
* Número de póliza duplicado.
* Datos inválidos.

---

## Pruebas Unitarias

Ejecutar:

```bash
mvn test
```

Las pruebas cubren:

* Creación de pólizas.
* Consulta de pólizas.
* Actualización de pólizas.
* Eliminación de pólizas.
* Gestión de riesgos.
* Validaciones de negocio.

---

## Mejoras Futuras

* Persistencia con JPA/Hibernate.
* Base de datos PostgreSQL.
* DTOs de entrada y salida.
* MapStruct para mapeos.
* Dockerización.
* Pruebas de integración.
* Seguridad con Spring Security.
* Versionamiento de API.

---

## Autor

Omar Arnulfo Arguello Moreno

Prueba Técnica – Gestión de Pólizas
