# Mapeo de `equipment_hexagon`

Este documento describe unicamente la carpeta `equipment_hexagon`: su estructura y el estado actual de cada clase.

## Estructura actual

```text
equipment_hexagon/
|-- application/
|   |-- ports/
|   |   |-- input/
|   |   |   `-- ManufacturerServicePort.java
|   |   `-- output/
|   |       `-- ManufacturerPersistencePort.java
|   `-- services/
|       `-- ManufacterService.java
|-- domain/
|   |-- Manufacturer.java
|   `-- Model.java
`-- infrastructure/
    |-- input/
    |   |-- errors/
    |   |   `-- GlobalHandlerError.java
    |   |-- mapper/
    |   |   `-- ManufacturerRestMapper.java
    |   |-- model/
    |   |   |-- request/
    |   |   |   `-- ManufacturerRequest.java
    |   |   `-- response/
    |   |       |-- ErrorResponse.java
    |   |       `-- ManufacturerResponse.java
    |   `-- rest_controllers/
    |       `-- RestControllerManufacturer.java
    `-- output/
        |-- entities/
        |   `-- ManufacturerEntity.java
        |-- errors/
        |   `-- ManufacturerNotFoundException.java
        |-- mapper/
        |   `-- ManufacturerPersistenceMapper.java
        |-- repository/
        |   `-- SpringManufacturerRepository.java
        `-- ManufacturerPersistenceAdapter.java
```

## Mapeo por capa y clases

### 1) `domain`

#### `Manufacturer.java`
- Rol: modelo de dominio principal para fabricante.
- Campos:
  - `UUID id`
  - `String name`
  - `UUID id_manufacter`
- Notas:
  - Usa Lombok (`@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`).
  - El nombre `id_manufacter` esta escrito diferente a `id_pais` usado en otras capas.

#### `Model.java`
- Rol: placeholder de dominio.
- Estado actual:
  - Clase vacia (sin campos ni metodos).
  - Solo anotaciones Lombok.

### 2) `application`

#### `ports/input/ManufacturerServicePort.java`
- Rol: contrato de entrada del caso de uso.
- Metodos:
  - `List<Manufacturer> findAll()`
  - `Manufacturer findById(String id)`
  - `Manufacturer save(Manufacturer manufacturer)`
  - `Manufacturer update(String id, Manufacturer manufacturer)`
  - `void delete(String id)`

#### `ports/output/ManufacturerPersistencePort.java`
- Rol: contrato de salida hacia persistencia.
- Metodos: mismos 5 del puerto de entrada (CRUD basico).

#### `services/ManufacterService.java`
- Rol: servicio de aplicacion que implementa `ManufacturerServicePort`.
- Dependencia: `ManufacturerPersistencePort`.
- Implementacion actual:
  - `findAll()` -> delega al puerto de persistencia.
  - `findById()` -> delega al puerto de persistencia.
  - `save()` -> retorna `null` (pendiente).
  - `update()` -> retorna `null` (pendiente).
  - `delete()` -> vacio (pendiente).
- Nota: el nombre de clase usa `Manufacter` (sin "t" en `Manufacturer`).

### 3) `infrastructure/input`

#### `rest_controllers/RestControllerManufacturer.java`
- Rol: controlador REST para `/v1/api/manufacturers`.
- Endpoints:
  - `GET /` -> lista fabricantes.
  - `GET /{id}` -> trae fabricante por id.
  - `POST /` -> crea fabricante.
  - `PUT /{id}` -> actualiza fabricante.
  - `DELETE /{id}` -> elimina fabricante.
- Flujo:
  - Convierte request DTO -> dominio con `ManufacturerRestMapper`.
  - Llama a `ManufacturerServicePort`.
  - Convierte dominio -> response DTO.

#### `mapper/ManufacturerRestMapper.java`
- Rol: mapper MapStruct entre DTO REST y dominio.
- Conversiones:
  - `ManufacturerRequest` -> `Manufacturer`
  - `Manufacturer` -> `ManufacturerResponse`
  - `List<Manufacturer>` -> `List<ManufacturerResponse>`

#### `model/request/ManufacturerRequest.java`
- Rol: DTO de entrada para crear/actualizar.
- Campos:
  - `String name` (`@NotBlank`)
  - `String id_pais` (`@NotBlank`)

#### `model/response/ManufacturerResponse.java`
- Rol: DTO de salida REST.
- Campos:
  - `String id`
  - `String name`
  - `String id_pais`

#### `model/response/ErrorResponse.java`
- Rol: DTO de error para respuestas HTTP.
- Campos:
  - `String code`
  - `String message`
  - `List<String> details`
  - `LocalDateTime timestamp`

#### `errors/GlobalHandlerError.java`
- Rol: manejador global de excepciones REST (`@RestControllerAdvice`).
- Manejos actuales:
  - `ManufacturerNotFoundException` -> `404 NOT_FOUND`.
  - `MethodArgumentNotValidException` -> `400 BAD_REQUEST`.
  - `Exception` generica -> `500 INTERNAL_SERVER_ERROR`.

### 4) `infrastructure/output`

#### `ManufacturerPersistenceAdapter.java`
- Rol: adaptador de salida que implementa `ManufacturerPersistencePort`.
- Dependencias:
  - `SpringManufacturerRepository`
  - `ManufacturerPersistenceMapper`
- Implementacion:
  - `findAll()` -> consulta JPA y mapea a dominio.
  - `findById()` -> busca por id o lanza `ManufacturerNotFoundException`.
  - `save()` -> mapea dominio, persiste, retorna dominio guardado.
  - `update()` -> valida existencia, actualiza campos via mapper, guarda y retorna.
  - `delete()` -> valida existencia y elimina por id.

#### `entities/ManufacturerEntity.java`
- Rol: entidad JPA para tabla `fabricante`.
- Campos:
  - `UUID id` (`@Id`, `@GeneratedValue`, columna `k_id_fabricante`)
  - `String name` (columna `n_nombre_fabricante`, `nullable = false`)
  - `UUID id_pais` (columna `k_id_pais`)

#### `repository/SpringManufacturerRepository.java`
- Rol: repositorio Spring Data JPA.
- Extiende:
  - `JpaRepository<ManufacturerEntity, String>`
- Nota:
  - El ID del entity es `UUID`, pero el repositorio declara `String`.

#### `mapper/ManufacturerPersistenceMapper.java`
- Rol: mapper MapStruct entre entidad y dominio.
- Metodos:
  - `toManufacturer(ManufacturerEntity)`
  - `toManufacturerEntity(Manufacturer)`
  - `toManufacturerList(List<ManufacturerEntity>)`
  - `updateEntityFromDomain(Manufacturer source, @MappingTarget ManufacturerEntity target)`
- Reglas de update:
  - Ignora `id` y `createdAt`.
  - Ignora propiedades `null` del source.

#### `errors/ManufacturerNotFoundException.java`
- Rol: excepcion de dominio tecnico para persistencia.
- Comportamiento:
  - Extiende `RuntimeException`.
  - Mensaje: `Manufacturer with id <id> not found`.

## Observaciones rapidas del estado actual

- El modulo sigue una estructura hexagonal clara: `domain` -> `application` -> `infrastructure`.
- `ManufacterService` aun no completa `save`, `update` y `delete`.
- Hay diferencias de nombres y tipos entre capas (`id_manufacter`, `id_pais`, `UUID` vs `String`).
- En `ManufacturerPersistenceMapper` se ignora `createdAt`, pero `ManufacturerEntity` no muestra ese campo.

