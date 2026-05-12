### CONTEXT
Proyecto Java con arquitectura hexagonal.

Capas:
- domain: modelos puros (sin dependencias)
- application: puertos (input/output) y servicios
- infrastructure:
    - input: controllers, DTOs, handlers
    - output: JPA entities, repositories, adapters

Reglas:
- No lógica de negocio en controllers
- Services orquestan
- MapStruct para mappers
- UUID en dominio, String en REST
- Exceptions manejadas con @RestControllerAdvice
- No inventar clases no mencionadas
- Código limpio, sin comentarios innecesarios

Convenciones:
- Sufijos:
    - Request / Response (REST)
    - Entity (JPA)
    - Mapper (MapStruct)
    - Service / Port
- Paquetes:
    - domain.model
    - application.ports
    - application.services
    - infrastructure.input.rest
    - infrastructure.output.persistence

### OUTPUT RULES
- Solo código
- Sin explicaciones
- Completo y compilable