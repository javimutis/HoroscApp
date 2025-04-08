package com.javimutis.horoscapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// Esta clase se encarga de interceptar las peticiones HTTP para agregar un token de autorización.
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager // Inyectamos la clase que maneja el token
) : Interceptor {

    // Esta función intercepta la petición antes de que se envíe al servidor
    override fun intercept(chain: Interceptor.Chain): Response {
        // Obtenemos la petición original
        val request = chain.request()
            .newBuilder() // Creamos una nueva versión modificable de la petición
            .header("Autorization", tokenManager.getToken()) // Agregamos el header con el token (OJO: está mal escrito "Authorization")
            .build() // Construimos la nueva petición

        // Enviamos la petición modificada al servidor
        return chain.proceed(request)
    }
}

// Esta clase (muy simple por ahora) retorna el token que usaremos para autenticarnos
class TokenManager @Inject constructor() {
    fun getToken(): String = "" // Por ahora está vacío, pero aquí debería ir el token real
}