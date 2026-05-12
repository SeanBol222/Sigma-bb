# README: Script de Ejecución Secuencial de Comandos

## ¿Qué es?
Un script PowerShell que lee comandos desde un archivo `.txt` y los ejecuta de forma secuencial, deteniendo la ejecución si algún comando falla.

## Archivos creados

### 1. `execute_commands.ps1`
Script principal que maneja la ejecución secuencial de comandos.

**Parámetros:**
- `-CommandsFile` (obligatorio): Ruta al archivo `.txt` con los comandos
- `-StopOnError` (opcional, por defecto `$true`): Si es `$true`, detiene si hay error

**Ejemplo de uso:**
```powershell
.\execute_commands.ps1 -CommandsFile "comandos_mapeo.txt"
```

### 2. `comandos_mapeo.txt`
Archivo de ejemplo con comandos para mapear todas las estructuras.

**Características del archivo:**
- Líneas vacías se ignoran
- Líneas que comienzan con `#` son comentarios (se ignoran)
- Cada línea es un comando PowerShell que se ejecuta tal cual

**Ejemplo de contenido:**
```
# Esto es un comentario
code C:\ruta\a\proyecto

# Ejecutar comandos PowerShell
Get-Content C:\archivo.txt

Write-Host "Mensaje" -ForegroundColor Green
```

## ¿Cómo usar?

### Paso 1: Preparar archivo de comandos
Crea un archivo `.txt` con los comandos que deseas ejecutar, uno por línea:
```
# Comandos para mapear estructuras
code C:\ruta\hexagon1
code C:\ruta\hexagon2
Get-ChildItem C:\ruta
```

### Paso 2: Ejecutar el script
```powershell
.\execute_commands.ps1 -CommandsFile "tu_archivo_de_comandos.txt"
```

### Paso 3: Interpretar resultados
El script mostrará:
- ✅ Comandos exitosos en **verde**
- ❌ Comandos fallidos en **rojo**
- Un resumen final con cantidad de éxitos y fallos

Si algún comando falla:
- Se detiene la ejecución
- Se muestra el error específico
- Se retorna el código de salida correspondiente

## Ventajas

✅ **Automatización**: Ejecuta múltiples comandos sin intervención  
✅ **Control de errores**: Detiene si algo falla (o continúa, según configuración)  
✅ **Logging visual**: Colores y números de línea para fácil seguimiento  
✅ **Reutilizable**: El mismo script funciona con diferentes archivos de comandos  
✅ **Sin dependencias externas**: Solo PowerShell nativo  

## Casos de uso

- **Mapeo de estructuras hexagonales**: Abrir múltiples carpetas en VSCode
- **Lectura automática de archivos**: Leer pom.xml, application.yaml, etc.
- **Compilación paso a paso**: Ejecutar comandos Maven, Gradle, npm, etc.
- **Auditoría de proyecto**: Verificar archivos, directorios, dependencias

## Ejemplo avanzado

Crea un archivo `build_stack.txt`:
```
# Limpiar y construir proyecto Maven
mvn clean

mvn install

# Ejecutar tests
mvn test

# Mostrar éxito
Write-Host "🎉 Build completado exitosamente" -ForegroundColor Green
```

Luego ejecuta:
```powershell
.\execute_commands.ps1 -CommandsFile "build_stack.txt"
```

Si en algún paso falla Maven, el script se detiene y te muestra exactamente dónde.

