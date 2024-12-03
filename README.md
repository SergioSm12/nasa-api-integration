# NASA API Integration

Este proyecto es una aplicación **Spring Boot** diseñada
para integrar y consumir múltiples endpoints de la API de la NASA,
ofreciendo acceso a una variedad de datos astronómicos. Incluye autenticación
mediante JWT y una arquitectura modular para facilitar la escalabilidad
y el mantenimiento.

## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Características](#características)
3. [Estructura del Proyecto](#estructura-del-proyecto)
4. [Requisitos Previos](#requisitos-previos)

---

## Descripción del Proyecto
El proyecto integra la **NASA Open APIs**, permitiendo consumir y exponer
datos astronómicos, como imágenes, información sobre misiones espaciales
y datos planetarios. Proporciona una solución flexible y segura mediante
el uso de tecnologías modernas como **Spring Boot 3** y **JWT** para 
autenticación.

El enfoque modular del proyecto facilita agregar nuevos endpoints de la API
de la NASA según las necesidades del usuario o las funcionalidades requerida.

## Características
- **Consumo de múltiples endpoints de la NASA API**: Incluye servicios como *Astronomy Picture of the Day (APOD)*, *NeoWs (Near Earth Object Web Service)*, *Mars Rover Photos*, entre otros.
- **Autenticación JWT**: Seguridad para el acceso a los endpoints.
- **Estructura modular**: Fácil incorporación de nuevos servicios y funcionalidades.
- **Configuración centralizada**: Manejo de credenciales y parámetros de API desde un único archivo de configuración.
- **Extensible**: Pensado para escalar e integrar con otros servicios o APIs.

## Estructura del Proyecto
```plaintext
src/
├── main/
│   ├── java/com/sergio/nasa_api/app/
│   │   ├── integrations/nasa/
│   │   │   ├── controllers/
│   │   │   ├── services/
│   │   │   ├── repositories/
│   │   │   ├── dtos/
│   │   │   ├── config/
│   │   ├── integrations/genericconfig/
│   │   ├── integrations/genericservices/
│   │   ├── springdoc/
│   │   │   ├── integrations/
│   ├── resources/
│       ├── application.yml
└── test/
```
## Requisitos Previos
1. Java 17 o superior
2. Maven 3.8.1 o superior
3. Spring Boot 3
4. Postman o cualquier cliente HTTP para pruebas
Credenciales de la API de la NASA