# 🧭 Gincana Interactiva Crash Bandicoot

Aplicación Android desarrollada como parte de una actividad práctica, donde se crea una gincana interactiva utilizando Google Maps y elementos del universo de Crash Bandicoot.

El objetivo es visitar distintas localizaciones en Andalucía, completar retos con contraseña, y visualizar la ubicación actual del usuario.

---

## 📱 Capturas de pantalla

### 🗺️ Vista general con todos los marcadores personalizados
![Mapa completo](./capturas/3de5e177-a7ae-419e-9abc-7b8d81fc8036.png)

### 📍 InfoWindow al pulsar un marcador
![InfoWindow activo](./capturas/bc6b84b0-f3c2-4107-9bf3-e5ffbef05473.png)

### 🔐 Diálogo con campo de contraseña desactivado
![Campo desactivado](./capturas/714d65eb-f716-4cb7-872b-37f3618ef03d.png)

### ❌ Contraseña incorrecta
![Error de contraseña](./capturas/ffabdf34-a700-4efa-802a-cd4b44d55052.png)

### ✅ Contraseña correcta: reto completado
![Reto completado](./capturas/67a4c06d-cb10-4950-9aa3-60177c361e19.png)

### 📍 Permiso de ubicación solicitado por la app
![Permiso ubicación](./capturas/1d23f4b0-14e7-4b01-aea9-188d1e7839f6.png)

---

## 🚀 Funcionalidades

- Mapa con marcadores temáticos del mundo de Crash Bandicoot.
- Reto asociado a cada marcador con validación de contraseña.
- `AlertDialog` personalizado por reto.
- Visualización de la ubicación del usuario con un `Switch` en la interfaz.
- Solicitud de permisos de ubicación en tiempo de ejecución.
- Código escrito íntegramente en **Java** usando `ConstraintLayout`.

---

## 📦 Tecnologías y librerías usadas

- [Google Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/start)
- FusedLocationProviderClient (para obtener la ubicación)
- `SwitchCompat`, `AlertDialog`, `SupportMapFragment`
- Android API 33 (mínimo 21 recomendado)

---

## 📚 Estructura del proyecto

├── MainActivity.java
├── CrashLocation.java
├── res/
│ ├── layout/
│ │ ├── activity_main.xml
│ │ └── dialog_challenge.xml
│ ├── drawable/
│ │ └── crash.png
│ └── values/
│ └── google_maps_api.xml


---

## ⚙️ Cómo probar la app

1. Clona este repositorio.
2. Añade tu API Key en `google_maps_api.xml`.
3. Ejecuta el proyecto en un emulador con servicios de Google.
4. Permite acceso a la ubicación cuando se solicite.
5. Activa el `Switch` para ver tu posición actual.
6. Pulsa sobre un marcador para participar en un reto.

---

## 👤 Autor

**Alejandro Pérez Soto**  
Tarea 06 - DAM  
📆 Mayo 2025  

