# 🧭 Gincana Interactiva Crash Bandicoot

Aplicación Android desarrollada como parte de una actividad práctica, donde se crea una gincana interactiva utilizando Google Maps y elementos del universo de Crash Bandicoot.

El objetivo es visitar distintas localizaciones en Andalucía, completar retos con contraseña, y visualizar la ubicación actual del usuario.

---

## 📱 Capturas de pantalla

### 🗺️ Vista general con todos los marcadores personalizados
![Mapa completo](https://github.com/user-attachments/assets/88305245-6f3a-4715-a6e5-591e33402eef)

### 📍  Permiso de ubicación solicitado por la app 
![InfoWindow activo](https://github.com/user-attachments/assets/9d476c2e-ddb5-4add-b8f5-1f7aa6553d8a)


### 📍 InfoWindow al pulsar un marcador
![Campo desactivado](https://github.com/user-attachments/assets/ec37a10c-40cc-49f4-81ae-cfbb490a5082)


### 🔐 Diálogo con campo de contraseña desactivado
![Error de contraseña](https://github.com/user-attachments/assets/ecde90f8-c087-41bf-824a-f08e387a0dac)

### ❌ Contraseña incorrecta
![Reto completado](https://github.com/user-attachments/assets/0ff59f70-f6a8-4adb-96ec-0b6e79c7d12b)


### ✅Contraseña correcta: reto completado
![Permiso ubicación](https://github.com/user-attachments/assets/c69e8f89-050b-4a30-9829-2e7121c4b71c)


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

- [Google Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/start) — para mostrar mapas y marcadores.
- `FusedLocationProviderClient` — para obtener la ubicación actual del usuario.
- `SupportMapFragment` — para incrustar el mapa en la interfaz.
- `SwitchCompat` — para controlar la visibilidad de la ubicación del usuario.
- `AlertDialog` — para mostrar desafíos interactivos con campo de contraseña.
- `ConstraintLayout` — para un diseño flexible y responsivo.
- **Lenguaje:** Java (versión 11)
- **Gradle con Version Catalogs (`libs.XXX`)** — para gestión moderna de dependencias.
- **Compatibilidad Android:**
  - `compileSdk = 35` (Android 14)
  - `targetSdk = 35`
  - `minSdk = 24` (Android 7.0)

---

## 📁 Estructura del proyecto

```
├── MainActivity.java
├── CrashLocation.java
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   └── dialog_challenge.xml
│   ├── drawable/
│   │   └── crash.png
```

---
## ⚙️ Cómo probar la app

1. Clona este repositorio.
2. Añade tu API Key en el manifest
3. Ejecuta el proyecto en un emulador con servicios de Google.
4. Permite acceso a la ubicación cuando se solicite.
5. Activa el `Switch` para ver tu posición actual.
6. Pulsa sobre un marcador para participar en un reto.

---

## 👤 Autor

**Alejandro Pérez Soto**  
Tarea 06 - DAM  
📆 Mayo 2025  

