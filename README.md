🌟 Horóscopo App
Aplicación Android desarrollada con Jetpack y Hilt para mostrar predicciones zodiacales y cartas de la suerte.
Este proyecto forma parte del curso de Android Intermedio de Aristidevs.

🧙‍♀️ Descripción
Horóscopo App permite a los usuarios seleccionar su signo zodiacal y recibir una predicción diaria. Además, cuenta con una función interactiva que revela una "carta de la suerte" con animaciones.
La aplicación utiliza una arquitectura limpia basada en capas (data, domain y ui), e inyección de dependencias con Hilt.

✨ Características Principales
🔮 Lista de signos zodiacales con imágenes e información básica.

📜 Predicción diaria al seleccionar un signo.

🎡 Ruleta animada que muestra una carta de la suerte.

👆 Gestos de swipe para activar animaciones.

🧩 Inyección de dependencias con Hilt (Retrofit, Repositorios, ViewModels, etc.).

🏗️ Arquitectura limpia: separación clara entre capas.

🔄 Uso de Coroutines y StateFlow para manejar estados y flujos de datos.

🗂️ Estructura del Proyecto
java
Copiar
Editar
📦 horoscapp/
├── data/
│   ├── NetworkModule.kt → Configura Retrofit y OkHttp.
│   ├── Repository.kt / RepositoryImpl.kt → Lógica para obtener datos desde API.
│   ├── providers/ → Datos estáticos y aleatorios (como cartas).
│   └── models/ → Modelos de dominio y respuestas.
│
├── domain/
│   └── GetPredictionUseCase.kt → Caso de uso principal para predicción.
│
├── ui/
│   ├── activities/ → MainActivity.
│   ├── fragments/ → HoroscopeFragment, LuckFragment, HoroscopeDetailActivity.
│   ├── viewmodels/ → HoroscopeViewModel, HoroscopeDetailViewModel, LuckViewModel.
│   ├── adapters/ → HoroscopeAdapter, HoroscopeViewHolder.
│   └── listeners/ → OnSwipeTouchListener (para gestos).
│
├── di/
│   └── NetworkModule.kt → Configuración de Hilt para inyección.
│
└── utils/
├── AuthInterceptor.kt
└── TokenManager.kt → Actualmente vacíos, pensados para autenticación.
▶️ Cómo Ejecutar el Proyecto
Clona o descarga este repositorio.

Abre el proyecto en Android Studio.

Asegúrate de tener el SDK y herramientas necesarias instaladas.

Compila y ejecuta en un emulador o dispositivo físico.

📋 Requisitos
Android Studio (versión actual recomendada).

Gradle (según versión configurada en el proyecto).

Conexión a Internet (la app requiere consumir un servicio de predicciones zodiacales).

📝 Notas
Este proyecto representa un avance parcial del curso de Android Intermedio de Aristidevs.

El AuthInterceptor contiene un ejemplo de manejo de tokens, pero no está en uso actualmente.

El endpoint (BASE_URL) de la API del horóscopo debe estar disponible para que la app funcione correctamente.

🙌 Agradecimientos
Gracias por revisar este proyecto.
Si tienes dudas, sugerencias o quieres aportar, ¡no dudes en abrir un issue o contactarme!