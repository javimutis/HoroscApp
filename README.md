# **Horóscopo App**

**Proyecto de aplicación Android para mostrar predicciones de horóscopo y cartas de la suerte.**  
Este proyecto forma parte del **curso de Android Intermedio** de **Aristidevs**.

---

## **Descripción**

La aplicación muestra una lista de signos zodiacales para que el usuario seleccione uno y obtenga su predicción diaria.  
También incluye una función para revelar una “carta de la suerte” mediante animaciones de ruleta.  
El proyecto hace uso de diferentes capas (**Data**, **Domain** y **UI**) e implementa inyección de dependencias con **Hilt**.

---

## **Características Principales**

- Lista de signos zodiacales con imágenes e información básica.
- Pantalla detallada con la predicción diaria según el signo seleccionado.
- Detección de gestos de “swipe” para activar animaciones (por ejemplo, girar una ruleta).
- Inyección de dependencias con **Hilt** para simplificar la creación de objetos (Retrofit, Repositorios, ViewModels, etc.).
- Patrón de arquitectura limpio, separando código de la capa de datos, dominio y presentación.
- Uso de coroutines y StateFlow para la gestión de estados y consumo de datos.

---

## **Estructura del Proyecto**

- **data/**
    - **NetworkModule:** Configura Retrofit y OkHttp con interceptores.
    - **Repository** y **RepositoryImpl:** Se encargan de obtener la información del horóscopo desde la API.
    - **Proveedores** (HoroscopeProvider, RandomCardProvider): Datos estáticos y elementos aleatorios (cartas).
    - **Modelos** de dominio y respuestas (PredictionModel, PredictionResponse, etc.).

- **domain/**
    - **GetPredictionUseCase:** Caso de uso para obtener la predicción del horóscopo según el signo.

- **ui/**
    - **Activities** y **Fragments** (MainActivity, HoroscopeFragment, LuckFragment, HoroscopeDetailActivity):  
      Controlan la navegación y muestran la información en pantalla.
    - **ViewModels** (HoroscopeViewModel, HoroscopeDetailViewModel, LuckViewModel): Manejan la lógica de UI.
    - **Adaptadores** (HoroscopeAdapter, HoroscopeViewHolder): Para poblado de listas en RecyclerView.
    - **Listeners** (OnSwipeTouchListener): Para detectar gestos de swipe.

- **di/**
    - **Hilt** y sus módulos (NetworkModule) para configurar la inyección de dependencias.

- **utils/**
    - **AuthInterceptor, TokenManager:** Manejan, en teoría, la autenticación y token (actualmente vacíos).

---

## **Cómo Ejecutar el Proyecto**

1. **Clonar** o **descargar** este repositorio.
2. **Abrir** el proyecto en **Android Studio**.
3. Asegurarse de tener configurado el **SDK de Android** correspondiente.
4. **Compilar** y **ejecutar** la app en un dispositivo físico o emulador desde Android Studio.

---

## **Requisitos**

- **Android Studio** (versión recomendada o superior).
- **Gradle** según versión configurada en el proyecto.
- Conexión a Internet para consumir el servicio de horóscopo  
  (actualmente requiere endpoint funcional, de momento está referenciado en el *BASE_URL*).

---

## **Notas**

- Este es un avance hasta la parte actual del curso de Android Intermedio con Aristidevs.
- El token en AuthInterceptor es solo un ejemplo, no se está utilizando un token real.

---

¡Gracias por revisar este `README`! Si tienes dudas o sugerencias, siéntete libre de abrir un ticket o contactarme.  
**¡Recuerda que este proyecto está en desarrollo!**  