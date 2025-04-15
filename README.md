# 🔮 **Horóscopo App**

**Aplicación Android para mostrar predicciones del horóscopo, cartas de la suerte y lectura de la palma.**  
Proyecto desarrollado como parte del **curso de Android Intermedio** con **Aristidevs**.

---

## 🧭 **Descripción General**

La app ofrece una experiencia mágica donde puedes:
- Consultar tu horóscopo diario según tu signo zodiacal 🐏♋♎
- Girar una ruleta para descubrir una **carta de la suerte** 🎴
- Usar la cámara del dispositivo para una **lectura simbólica de la palma** ✋✨

El proyecto está construido con una arquitectura limpia y moderna (Clean Architecture), separando la lógica en capas y utilizando **Hilt** para la inyección de dependencias.

---

## 🌟 **Características Principales**

- 📱 Lista de signos zodiacales con sus predicciones diarias.
- 🎰 Animaciones tipo ruleta para descubrir cartas aleatorias.
- 📸 Escaneo simbólico de la palma usando la cámara.
- 👆 Detección de gestos (swipe) para activar acciones.
- 🚀 Arquitectura por capas (Data, Domain, UI).
- 🧠 ViewModels con **StateFlow** y **coroutines**.
- 💉 Inyección de dependencias con **Hilt**.
- ✨ Interfaz visual mágica y misteriosa.

---

## 🧱 **Estructura del Proyecto**

- **data/**
  - **NetworkModule:** Configura Retrofit y OkHttp con interceptores.
  - **Repository** y **RepositoryImpl:** Se encargan de obtener la información del horóscopo desde la API.
  - **Proveedores** (HoroscopeProvider, RandomCardProvider): Datos estáticos y elementos aleatorios (cartas).
  - **Modelos** de dominio y respuestas (PredictionModel, PredictionResponse, etc.).

- **domain/**
  - **GetPredictionUseCase:** Caso de uso para obtener la predicción del horóscopo según el signo.

- **ui/**
  - **Activities** y **Fragments** (MainActivity, HoroscopeFragment, LuckFragment, HoroscopeDetailActivity, PalmistryFragment):  
    Controlan la navegación y muestran la información en pantalla.
  - **ViewModels** (HoroscopeViewModel, HoroscopeDetailViewModel, LuckViewModel): Manejan la lógica de UI.
  - **Adaptadores** (HoroscopeAdapter, HoroscopeViewHolder): Para poblado de listas en RecyclerView.
  - **Listeners** (OnSwipeTouchListener): Para detectar gestos de swipe.

- **di/**
  - **Hilt** y sus módulos (NetworkModule) para configurar la inyección de dependencias.

- **utils/**
  - **AuthInterceptor, TokenManager:** Manejan, en teoría, la autenticación y token (actualmente vacíos).

---

## ▶️ **Cómo Ejecutar el Proyecto**

1. Clona o descarga el repositorio.
2. Abre el proyecto en **Android Studio**.
3. Asegúrate de tener el SDK correspondiente instalado.
4. Ejecuta la app en un emulador o dispositivo real.
5. Concede los permisos necesarios (especialmente cámara).

---

## ⚙️ **Requisitos Técnicos**

- **Android Studio** actualizado (versión recomendada).
- Dependencias compatibles con AndroidX, Hilt, Retrofit, CameraX.
- Dispositivo físico o emulador con soporte para cámara.

---

## 📌 **Notas**

- Este proyecto sigue en desarrollo como parte del curso.
- Algunos archivos de `utils` como `AuthInterceptor` están vacíos o son placeholders.
- La pantalla de lectura de palma es un componente visual decorativo (no utiliza ML aún).

---

## ✅ **Testing Instrumentado (Espresso + Hilt)**

Este proyecto incluye **tests de UI automatizados** usando **Espresso** junto a **Hilt** para pruebas con inyección de dependencias.

Los tests aseguran que las pantallas principales funcionen correctamente al realizar acciones como tocar botones o abrir detalles. Son ideales para aprender sobre testing en Android moderno.

### 🧪 Pruebas Incluidas

#### 📲 `MainActivityTest.kt`

Archivo ubicado en `ui/home/`, contiene pruebas instrumentadas para verificar el comportamiento de la pantalla principal:

- `when_mainactivity_is_created_then_open_luckfragment()`  
  ✅ Simula un clic en la pestaña o fragmento de la **ruleta de la suerte** para asegurarse que se puede abrir correctamente.

- `when_horoscope_is_selected_then_open_detail()`  
  ✅ Simula un clic en el **primer signo del horóscopo** (lista con RecyclerView) y verifica que se abra la **pantalla de detalle del horóscopo**.

### 🧠 Tecnologías usadas en los tests

- **Espresso**: Para simular interacciones del usuario (clics, scroll, navegación).
- **Intents de Espresso**: Para verificar cambios entre pantallas (por ejemplo, que se abre `HoroscopeDetailActivity`).
- **HiltAndroidRule**: Para habilitar la inyección de dependencias durante los tests.
- **ActivityScenarioRule**: Para lanzar y testear una `Activity`.

### 🧪 Runner personalizado

El proyecto incluye un **runner de test personalizado** llamado `CustomTestRunner`, ubicado en la raíz del proyecto:

```kotlin
class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
